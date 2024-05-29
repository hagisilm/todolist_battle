package com.hci.todolist_battle.friend.service;

import com.hci.todolist_battle.friend.entity.Friend;
import com.hci.todolist_battle.friend.repository.FriendRepository;
import com.hci.todolist_battle.member.entity.Member;
import com.hci.todolist_battle.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService{
    private final FriendRepository friendRepository;
    private final MemberRepository memberRepository;

    @Override
    public void register(String memberId,String friendId) {

        Member optionalMember = memberRepository.findByMemberId(friendId).orElseThrow(()->new RuntimeException(""));

        if (optionalMember!=null) {
            Friend friend = Friend.builder()
                    .memberId(memberId)
                    .friendId(optionalMember)
                    .build();
            friendRepository.save(friend);
        } else {
            throw new RuntimeException("해당 id 가 없습니다");
        }

    }

    @Override
    public List<Friend> getFriends(String memberId) {
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        List<Friend> friends = friendRepository.findAllByMemberId(memberId);
        return friends;
    }
}
