package com.hci.todolist_battle.battle.repository;

import com.hci.todolist_battle.battle.entity.Battle;
import com.hci.todolist_battle.battle.entity.BattleTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BattleTaskRepository extends JpaRepository<BattleTask,Long> {
    List<BattleTask> findAllByBattle(Battle battle);
//    BattleTask findByMemberNoAndTaskNo(String memberNo,String taskNo);
}
