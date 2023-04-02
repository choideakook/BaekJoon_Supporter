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
    private String ruleName;
    @ManyToOne
    private Team team;
}