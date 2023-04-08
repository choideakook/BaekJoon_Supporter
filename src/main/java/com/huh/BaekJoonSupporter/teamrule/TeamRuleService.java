package com.huh.BaekJoonSupporter.teamrule;

import com.huh.BaekJoonSupporter.DataNotFoundException;
import com.huh.BaekJoonSupporter.team.Team;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TeamRuleService {
    private final TeamRuleRepository teamRuleRepository;

    // 규칙 생성
    public TeamRule create(Team team, String target, String difficulty, Long targetNumber) {

        // 객체 생성
        TeamRule teamRule = TeamRule.builder()
                .target(target)
                .difficulty(difficulty)
                .targetNumber(targetNumber)
                .team(team)
                .build();

        teamRuleRepository.save(teamRule);
        return teamRule;
    }

    // 규칙 조회
    public TeamRule getTeamRule(String target) {
        Optional<TeamRule> teamRule = teamRuleRepository.findByTarget(target);
        if (teamRule.isPresent()) {
            return teamRule.get();
        } else {
            throw new DataNotFoundException("팀 규칙을 입력해주세요");
        }
    }

//    // 규칙 수정
//    public void modify(TeamRule teamRule, String modifyRuleName) {
//        TeamRule teamRule1 = teamRule.toBuilder()
//                .ruleName(modifyRuleName)
//                .build();
//        teamRuleRepository.save(teamRule1);
//    }
//    // 규칙 삭제
    public void delete(TeamRule teamRule) {
        teamRuleRepository.delete(teamRule);
    }

}
