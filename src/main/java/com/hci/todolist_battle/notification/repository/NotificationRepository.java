package com.hci.todolist_battle.notification.repository;

import com.hci.todolist_battle.member.entity.Member;
import com.hci.todolist_battle.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findAllByMember(Member member);
}
