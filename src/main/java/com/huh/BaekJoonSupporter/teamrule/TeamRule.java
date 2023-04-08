package com.huh.BaekJoonSupporter.teamrule;

import com.huh.BaekJoonSupporter.team.Team;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TeamRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 해시 사용 대비 남겨두기
//    @ElementCollection // 해시맵 필드 선언
//    @CollectionTable(name="team_rule_table") //DB에서 사용할 테이블 이름
//    @MapKeyColumn(name="attractiveTargetTypeCode") // 해시맵의 키로 사용할 필드
//    @Column(name="targetSize") // 해시맵의 값으로 사용될 필드명
//    @Builder.Default
//    private Map<String, Long> ruleName = new HashMap<>();


    private String target;

    private String difficulty;

    private Long targetNumber;

    @ManyToOne
    @ToString.Exclude
    private Team team;


    }