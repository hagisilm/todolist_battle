package com.hci.todolist_battle.friend.repository;

import com.hci.todolist_battle.friend.entity.Friend;
import com.hci.todolist_battle.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend,Long> {
//    List<Friend> findAllByMember(Member member);
    List<Friend> findAllByMemberId(String memberId);
}
