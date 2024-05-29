package com.hci.todolist_battle.task.controller;

import com.hci.todolist_battle.task.entity.Task;
import com.hci.todolist_battle.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/api/task/register")
    public void taskRegister(@RequestParam String title) {
        taskService.register(title);
    }

    @GetMapping("/api/tasks")
    public List<Task> getTasks(@RequestParam String task) {
        return taskService.getTasks(task);
    }

    @GetMapping("/api/Alltasks")
    public List<Task> getAll() {
        return taskService.getAll();
    }
}
