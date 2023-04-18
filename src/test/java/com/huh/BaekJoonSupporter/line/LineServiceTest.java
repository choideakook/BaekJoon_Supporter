package com.huh.BaekJoonSupporter.line;

import com.huh.BaekJoonSupporter.member.Member;
import com.huh.BaekJoonSupporter.team.Team;
import com.huh.BaekJoonSupporter.team.TeamService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(properties = {"spring.config.location=classpath:application.yml"})
public class LineServiceTest {
    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private LineService lineService;

    @Autowired
    private TeamService teamService;


    @Test
    @DisplayName("LineService Create Test")
    void lineTest1() {
        ////////////////////////
        // Create 테스트
        ////////////////////////
        this.lineService.create("token1", null);
        this.lineService.create("token2", null);

        Line line = this.lineRepository.findByToken("token1").get();
        assertThat(line.getId()).isEqualTo(1L);
        assertThat(line.getTeam()).isNull();
    }

    @Test
    @DisplayName("Line Service 생성 테스트 2 ")
    void lineTest2() {
        // 팀 더미데이터 생성
        Team team1 = this.teamService.create(111L, "gold2", null, new Member());
        this.lineService.create("token3", team1);
        this.lineService.create("token4", null);

        Line line = this.lineRepository.findByToken("token3").get();
        assertThat(line.getId()).isEqualTo(3L);
        assertThat(line.getTeam()).isNotNull();
    }

    @Test
    @DisplayName("Line Service 수정 테스트 ")
    void lineTest3() {
        //team이 없는 라인 생성
        this.lineService.create("modify1", null);
        Line line = this.lineService.getLine(5L);
        assertThat(line.getTeam()).isNull();

        //팀을 만들고 라인 수정
        Team team2 = this.teamService.create(222L, "silver2", null, new Member());
        this.lineService.modify(line, "modify1", team2);

        //team이 더이상 null 이 아니고, 생성한 team2와 같은지 확인
        Line m_line = this.lineService.getLine(5L);
        assertThat(m_line.getTeam()).isNotNull();
        assertThat(team2).isSameAs(m_line.getTeam());
    }

    @Test
    @DisplayName("Line Service 삭제 테스트 ")
    void lineTest4() {
        //team이 없는 라인 생성
        Team team = this.teamService.create(1L, "delete", null, new Member());
        Line line = this.lineService.create("delete1", team);

        this.lineService.delete(line);

        Optional<Line> ol = this.lineRepository.findByToken("delete1");

        assertThat(ol.isEmpty()).isTrue();
        assertThat(this.lineRepository.findAll().size()).isEqualTo(0);
    }
}