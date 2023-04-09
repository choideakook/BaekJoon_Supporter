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
    private String ruleName; // Map < k 문제 푼수면 : v 문제수  > 1. 문제 푼수(solvcedCount), 2. 난이도별 문제 푼 수
    @ManyToOne
    private Team team;
}


/**
*  o 문제 푼수 o 난이도별 문제수
 *  input => Integer type 입력 받고
 *  String , Object
 */