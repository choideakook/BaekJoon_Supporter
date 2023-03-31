package com.huh.BaekJoonSupporter.team;

import com.huh.BaekJoonSupporter.line.Line;
import com.huh.BaekJoonSupporter.member.Member;
import com.huh.BaekJoonSupporter.teamrule.TeamRule;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long leaderId;

    private String teamName;

    @OneToOne
    private Line line;

    @OneToMany
    private List<TeamRule> teamRules = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @Builder
    public Team(Long id, Long leaderId, String teamName, Line line, LocalDateTime createDate) {
        this.leaderId = leaderId;
        this.teamName = teamName;
        this.line = line;
        this.createDate = createDate;
    }
}