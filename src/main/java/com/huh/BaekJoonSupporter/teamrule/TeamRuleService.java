package com.huh.BaekJoonSupporter.teamrule;

import com.huh.BaekJoonSupporter.DataNotFoundException;
import com.huh.BaekJoonSupporter.line.Line;
import com.huh.BaekJoonSupporter.member.Member;
import com.huh.BaekJoonSupporter.team.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamRuleService {
    private final TeamRuleRepository teamRuleRepository;

    // 규칙 생성
    public TeamRule create(String rule, Team team) {
        TeamRule teamRule = TeamRule.builder()
                .ruleName(rule)
                .team(team)
                .build();
        teamRuleRepository.save(teamRule);
        return teamRule;
    }

    // 규칙 조회
    public TeamRule getTeamRule(Long id) {
        Optional<TeamRule> teamRule = teamRuleRepository.findById(id);
        if (teamRule.isPresent()) {
            return teamRule.get();
        } else {
            throw new DataNotFoundException("팀 규칙 번호 확인해주세요");
        }
    }

    // 규칙 수정
    public void modify(TeamRule teamRule, String modifyRuleName) {
        TeamRule teamRule1 = teamRule.toBuilder()
                .ruleName(modifyRuleName)
                .build();
        teamRuleRepository.save(teamRule1);
    }
    // 규칙 삭제
    public void delete(TeamRule teamRule) {
        teamRuleRepository.delete(teamRule);
    }

}
