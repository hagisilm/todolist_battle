package com.hci.todolist_battle.notification.service;

import com.hci.todolist_battle.member.entity.Member;
import com.hci.todolist_battle.member.repository.MemberRepository;
import com.hci.todolist_battle.notification.entity.Notification;
import com.hci.todolist_battle.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{
    private final NotificationRepository notificationRepository;
    private final MemberRepository memberRepository;

    @Override
    public void create(Member member, String tasks,Long battleNo) {
        Notification notification = Notification.builder()
                .member(member)
                .tasks(tasks)
                .battleNo(battleNo)
                .build();
        notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotification(String memberId) {
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        List<Notification> notifications = notificationRepository.findAllByMember(member.get());
        return notifications;
    }
}
