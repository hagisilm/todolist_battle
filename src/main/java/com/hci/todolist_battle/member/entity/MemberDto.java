package com.hci.todolist_battle.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private String name;
    private String memberId;
    private String password;
    private String deviceKey;


    public Member toMember() {
        return Member.builder()
                .memberId(memberId)
                .name(name)
                .password(password)
                .deviceKey(deviceKey).build();
    }

}
