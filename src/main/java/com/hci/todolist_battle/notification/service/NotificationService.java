package com.hci.todolist_battle.notification.service;

import com.hci.todolist_battle.member.entity.Member;
import com.hci.todolist_battle.notification.entity.Notification;

import java.util.List;

public interface NotificationService {
    void create(Member member, String tasks,Long battleNo);

    List<Notification> getNotification(String memberId);
}
