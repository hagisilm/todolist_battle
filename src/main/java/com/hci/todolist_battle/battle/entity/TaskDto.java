package com.hci.todolist_battle.battle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long battleNo;
    private String memberNo;
    private String taskNo;
    private MultipartFile file;
}
