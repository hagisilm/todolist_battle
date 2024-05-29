package com.hci.todolist_battle.battle.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hci.todolist_battle.friend.entity.Friend;
import com.hci.todolist_battle.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long battleNo;

    private String challenger;

    private String challengee;

    private boolean isAccept;
    private LocalDateTime createAt;
    private String status;
    private String reward;

    @OneToMany(mappedBy = "battle", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BattleTask> battleTasks = new ArrayList<>();
}
