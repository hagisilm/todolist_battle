package com.hci.todolist_battle.member.service;

import com.hci.todolist_battle.member.entity.LoginDto;
import com.hci.todolist_battle.member.entity.Member;
import com.hci.todolist_battle.member.entity.MemberDto;
import com.hci.todolist_battle.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Member login(LoginDto loginDto) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(loginDto.getMemberId());
        if (optionalMember.isPresent()) {
            if (optionalMember.get().getPassword().equals(loginDto.getPassword())) {
                return optionalMember.get();
            } else {
                throw new RuntimeException("비번오류");
            }
        } else {
            throw new RuntimeException("해당 id가 존재하지 않습니따");
        }
    }

    @Override
    public void register(MemberDto memberDto) {
        Member member = Member.builder()
                .memberId(memberDto.getMemberId())
                .password(memberDto.getPassword())
                .name(memberDto.getName())
                .deviceKey(memberDto.getDeviceKey())
                .build();
        memberRepository.save(member);
//        return memberRepository.save(memberDto.toMember());
    }

}
