package com.huh.BaekJoonSupporter.teamrule;

import com.huh.BaekJoonSupporter.line.Line;
import com.huh.BaekJoonSupporter.member.Member;
import com.huh.BaekJoonSupporter.member.MemberService;
import com.huh.BaekJoonSupporter.team.Team;
import com.huh.BaekJoonSupporter.team.TeamRepository;
import com.huh.BaekJoonSupporter.team.TeamService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class TeamRuleServiceTest {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamRuleService teamRuleService;

    @Autowired
    private TeamRepository teamRuleRepository;

    @Autowired
    private MemberService memberService;

    private final String NAME1 = "user1";
    private final String NAME2 = "user2";
    private final String PASSWORD = "1234";
    private final String TOKEN1 = "ASDF";
    private final String TOKEN2 = "QWER";
    private final String TEAM_NAME = "teamName";

    @Test
    @DisplayName("TeamRule 생성 테스트")
    void test001() {
        memberService.create(NAME1, PASSWORD, TOKEN1);
        memberService.create(NAME2, PASSWORD, TOKEN2);
        Member leader = memberService.getMember(NAME1);
        Line line = null;
        Team team = teamService.create(leader.getId(), TEAM_NAME, line, leader);

        TeamRule teamRule = teamRuleService.create(team, "aaa", "mnmm", 5L);
        TeamRule temp = teamRuleService.getTeamRule("aaa");

        assertThat(teamRule).isEqualTo(temp);
    }
}

