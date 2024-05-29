package com.hci.todolist_battle.battle.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BattleDto {
    private String challengerId;
    private String challengeeId;
    private String tasks;
}
