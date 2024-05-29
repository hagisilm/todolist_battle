package com.hci.todolist_battle.friend.entity;

import com.hci.todolist_battle.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendNo;

    private String memberId;

    @ManyToOne
    @JoinColumn(name = "member_no")
    private Member friendId;
}
