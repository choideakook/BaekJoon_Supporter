package com.huh.BaekJoonSupporter.teamrule;

import com.huh.BaekJoonSupporter.DataNotFoundException;
import com.huh.BaekJoonSupporter.team.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    // 팀 ID로 조회하는거 추후 변경 필요
    public TeamRule getTeamRule(Long teamRuleId) {
        Optional<TeamRule> teamRule = teamRuleRepository.findById(teamRuleId);
        if (teamRule.isPresent()) {
            return teamRule.get();
        } else {
            throw new DataNotFoundException("찾는 팀의 규칙이 없습니다.");
        }
    }

  // 규칙 수정
    public void modify(TeamRule teamRule, String changeTarget, String changeDifficulty, Long changeTargetNumber) {
        TeamRule teamRule1 = teamRule.toBuilder()
                .target(changeTarget)
                .difficulty(changeDifficulty)
                .targetNumber(changeTargetNumber)
                .build();
        teamRuleRepository.save(teamRule1);
    }

//    // 규칙 삭제
    public void delete(TeamRule teamRule) {
        teamRuleRepository.delete(teamRule);
    }

}
