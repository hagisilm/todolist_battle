package com.hci.todolist_battle.task.service;

import com.hci.todolist_battle.task.entity.Task;
import com.hci.todolist_battle.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;
    @Override
    public void register(String title) {
        Task task = Task.builder()
                        .taskName(title)
                                .build();
        taskRepository.save(task);
    }

    @Override
    public List<Task> getTasks(String tasks) {
        List<Task> tasks1 = new ArrayList<>();;
        String[] list = tasks.split(",");
        System.out.println(list[0]);
        for (String taskNum : list) {
            Optional<Task> task = taskRepository.findById(Long.valueOf(taskNum));
            if (task.isPresent()) { // Optional이 값을 가지고 있는지 확인
                tasks1.add(task.get());
            } else {
                System.out.println("Task not found for ID: " + taskNum);
            }
            System.out.println(tasks1);
        }
        return tasks1;
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }
}
