package com.hci.todolist_battle.member.service;

import com.hci.todolist_battle.member.entity.LoginDto;
import com.hci.todolist_battle.member.entity.Member;
import com.hci.todolist_battle.member.entity.MemberDto;
import org.springframework.stereotype.Service;

public interface MemberService {
    void register(MemberDto memberDto);

    Member login(LoginDto loginDto);
}
