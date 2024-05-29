package com.hci.todolist_battle.task.service;

import com.hci.todolist_battle.task.entity.Task;

import java.util.List;

public interface TaskService {
    void register(String title);

    List<Task> getTasks(String tasks);

    List<Task> getAll();
}
