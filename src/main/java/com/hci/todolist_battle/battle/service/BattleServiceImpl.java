package com.hci.todolist_battle.battle.service;

import com.google.firebase.messaging.*;
import com.hci.todolist_battle.FileHandler;
import com.hci.todolist_battle.battle.entity.*;
import com.hci.todolist_battle.battle.repository.BattleRepository;
import com.hci.todolist_battle.battle.repository.BattleTaskRepository;
import com.hci.todolist_battle.image.Image;
import com.hci.todolist_battle.image.repository.ImageRepository;
import com.hci.todolist_battle.member.entity.Member;
import com.hci.todolist_battle.member.repository.MemberRepository;
import com.hci.todolist_battle.notification.service.NotificationService;
import com.hci.todolist_battle.task.entity.Task;
import com.hci.todolist_battle.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BattleServiceImpl implements BattleService{
    private final BattleRepository battleRepository;
    private final MemberRepository memberRepository;
    private final BattleTaskRepository battleTaskRepository;
    private final NotificationService notificationService;
    private final ImageRepository imageRepository;
    private final TaskRepository taskRepository;
    private FileHandler fileHandler = new FileHandler();

    AndroidConfig androidConfig = AndroidConfig.builder()
            .setPriority(AndroidConfig.Priority.HIGH)
            .build();

    FirebaseMessaging firebaseMessaging;
    ZoneId koreaZoneId = ZoneId.of("Asia/Seoul");

    @Override
    public void createBattle(BattleDto battleDto) {

        Optional<Member> challenger = memberRepository.findByMemberId(battleDto.getChallengerId());
        Optional<Member> challengee = memberRepository.findByMemberId(battleDto.getChallengeeId());
        Battle battle = Battle.builder()
                .challenger(battleDto.getChallengerId())
                .challengee(battleDto.getChallengeeId())
                .createAt(LocalDateTime.now())
                .isAccept(false)
                .status("waiting").build();
        Battle battle1 = battleRepository.save(battle);

        notificationService.create(challengee.get(),battleDto.getTasks(),battle1.getBattleNo());
        //fcm
        String body = battle.getChallenger()
                + "님 배틀을 신청하였습니다. 클릭해 습관을 만들어보세요";
        Notification notification = Notification.builder()
                .setTitle(battle.getChallenger())
                .setBody(body)
                .build();

        Optional<Member> member = memberRepository.findByMemberId(battle.getChallengee());
        Message message = Message.builder()
                .setToken(member.get().getDeviceKey())
//                    .setNotification(notification)
                .setAndroidConfig(androidConfig)
                .putData("battleNo", battle.getBattleNo().toString())
                .build();

        try {
            firebaseMessaging.send(message);
            log.info("message send success {}", LocalTime.now(koreaZoneId));
        } catch (FirebaseMessagingException e) {
            log.error("message send fail -> {}", e.getMessage());
        }

    }

    @Override
    public List<Battle> getBattles(String memberId) {
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        if (member.isPresent()) {
            List<Battle> battles = battleRepository.findAllByChallengee(member.get());
            return battles.stream()
                    .filter(Battle::isAccept)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList(); // 멤버를 찾지 못한 경우 빈 리스트 반환
        }
    }

    @Override
    public void acceptBattle(BattleTaskDto battleTaskDto) {
        Optional<Battle> battle = battleRepository.findById(battleTaskDto.getBattleNo());
        battle.get().setAccept(true);
        List<String> tasks = Arrays.asList(battleTaskDto.getTasks().split(","));
////        fcm 보내기
        for (String task : tasks) {
            Optional<Task> task1 = taskRepository.findById(Long.parseLong(task));
            BattleTask battleTask1 = BattleTask.builder()
                    .task(task1.get())
                    .battle(battle.get())
                    .isCheck(false)
                    .memberNo(battleTaskDto.getChallengerId())
                    .isAuth(false)
                    .build();
            battleTaskRepository.save(battleTask1);
            BattleTask battleTask2 = BattleTask.builder()
                    .task(task1.get())
                    .battle(battle.get())
                    .isCheck(false)
                    .memberNo(battleTaskDto.getChallengeeId())
                    .isAuth(false)
                    .build();
            battleTaskRepository.save(battleTask2);
        }
    }

    @Override
    public Battle getBattle(String memberId) {
        Battle battle1 = battleRepository.findByChallengee(memberId);
        Battle battle2 = battleRepository.findByChallenger(memberId);
        if (battle1 != null) {
            return battle1;
        }
        if (battle2 != null) {
            return battle2;
        }
       return null;
    }

    @Override
    public List<BattleTask> getBattleTask(Long battleNo) {
        Optional<BattleTask> battle = battleTaskRepository.findById(battleNo);
        return battleTaskRepository.findAllByBattle(battle.get().getBattle());
    }

    @Override
    public void checkTask(TaskDto taskDto) {
//        BattleTask battleTask = battleTaskRepository.findByMemberNoAndTaskNo(taskDto.getMemberNo(), taskDto.getTaskNo());
        Optional<BattleTask> battleTask = battleTaskRepository.findById(taskDto.getBattleNo());

        Image image = new Image();
        try {
            image = fileHandler.parseFileInfo(taskDto.getFile());
            imageRepository.save(image);
        } catch (Exception e) {
            throw new RuntimeException("file 오류");
        }
        battleTask.get().setImage(image);
        battleTaskRepository.save(battleTask.get());
    }
}
