package com.hci.todolist_battle.member.controller;

import com.hci.todolist_battle.member.entity.LoginDto;
import com.hci.todolist_battle.member.entity.Member;
import com.hci.todolist_battle.member.entity.MemberDto;
import com.hci.todolist_battle.member.service.MemberService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/api/member/register")
    public void registerMember(@RequestBody MemberDto memberDto) {
        memberService.register(memberDto);

    }

    @PostMapping("/api/member/login")
    public ResponseEntity<Member> login(@RequestBody LoginDto loginDto) {
        Member member = memberService.login(loginDto);
        return ResponseEntity.ok().body(member);
    }
}
