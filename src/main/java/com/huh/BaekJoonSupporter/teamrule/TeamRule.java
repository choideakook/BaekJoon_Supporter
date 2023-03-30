package com.huh.BaekJoonSupporter.teamrule;

import com.huh.BaekJoonSupporter.Team.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TeamRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ruleName;
    @ManyToOne
    private Team team;
}
