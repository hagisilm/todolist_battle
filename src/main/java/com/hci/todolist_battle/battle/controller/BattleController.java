package com.hci.todolist_battle.battle.controller;

import com.hci.todolist_battle.battle.entity.*;
import com.hci.todolist_battle.battle.repository.BattleTaskRepository;
import com.hci.todolist_battle.battle.service.BattleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BattleController {
    private final BattleService battleService;

    @PostMapping("/api/battle/register")
    public void applyBattle(@RequestBody BattleDto battleDto) {
        battleService.createBattle(battleDto);
    }

    @PostMapping("/api/battle/accept")
    public void acceptBattle(@RequestBody BattleTaskDto battleTaskDto) {
        battleService.acceptBattle(battleTaskDto);
    }

    @GetMapping("/api/battle/{memberId}")
    public Battle getBattle(@PathVariable String memberId) {
        return battleService.getBattle(memberId);
    }

    @GetMapping("/api/battle/tasks/{battleNo}")
    public List<BattleTask> getTasks(@PathVariable Long battleNo) {
        return battleService.getBattleTask(battleNo);
    }

    @PostMapping("/api/battle/task")
    public void checkTask(@RequestBody TaskDto taskDto) {
        battleService.checkTask(taskDto);
    }
}
