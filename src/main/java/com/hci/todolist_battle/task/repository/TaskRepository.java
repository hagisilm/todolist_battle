package com.hci.todolist_battle.task.repository;

import com.hci.todolist_battle.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
