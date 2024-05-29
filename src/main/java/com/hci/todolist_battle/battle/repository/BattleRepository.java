package com.hci.todolist_battle.battle.repository;

import com.hci.todolist_battle.battle.entity.Battle;
import com.hci.todolist_battle.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BattleRepository extends JpaRepository<Battle, Long> {
    List<Battle> findAllByChallengee(Member member);

    Battle findByChallenger(String memberId);

    Battle findByChallengee(String memberId);
}
