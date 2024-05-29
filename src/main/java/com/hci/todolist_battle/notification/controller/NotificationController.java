package com.hci.todolist_battle.notification.controller;

import com.hci.todolist_battle.notification.entity.Notification;
import com.hci.todolist_battle.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/api/notification/{memberId}")
    public List<Notification> getNotifications(@PathVariable String memberId) {
        return notificationService.getNotification(memberId);
    }
}
