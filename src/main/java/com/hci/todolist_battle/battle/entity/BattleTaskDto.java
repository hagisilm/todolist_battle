package com.hci.todolist_battle.battle.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BattleTaskDto {
    private String challengerId;
    private String challengeeId;
    private String tasks;
    private Long battleNo;
}
