package com.hci.todolist_battle.battle.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hci.todolist_battle.image.Image;
import com.hci.todolist_battle.task.entity.Task;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BattleTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long battleTaskNo;
    @ManyToOne
    @JoinColumn(name = "battle_no")
    @JsonBackReference
    private Battle battle;
    private boolean isCheck;
    @OneToOne
    @JoinColumn(name="image_no")
    private Image image;
    @ManyToOne
    @JoinColumn(name = "task_no")
    private Task task;
    private boolean isAuth;
    private String memberNo;
}
