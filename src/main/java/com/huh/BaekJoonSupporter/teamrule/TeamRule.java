package com.huh.BaekJoonSupporter.teamrule;

import com.huh.BaekJoonSupporter.team.Team;
import jakarta.persistence.*;
import lombok.*;

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

// 목표 설정 : 난이도 무관 문제풀이 / 난이도별 문제풀이
    private String target;
// 난이도 설정
    private String difficulty;
// 목표 문제 풀이 개수
    private Long targetNumber;

    @ManyToOne
    @ToString.Exclude
    private Team team;
}


/**
 *  o 문제 푼수 o 난이도별 문제수
 *  input => Integer type 입력 받고
 *  String , Object
 */
