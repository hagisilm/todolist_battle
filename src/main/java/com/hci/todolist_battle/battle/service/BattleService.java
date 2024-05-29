package com.hci.todolist_battle.battle.service;

import com.hci.todolist_battle.battle.entity.*;

import java.util.List;

public interface BattleService {
    void createBattle(BattleDto battleDto);

    List<Battle> getBattles(String memberId);

    void acceptBattle(BattleTaskDto battleTaskDto);

    Battle getBattle(String memberId);

    List<BattleTask> getBattleTask(Long battleNo);

    void checkTask(TaskDto taskDto);
}
