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
@Builder(toBuilder = true)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long leaderId;

    @Column(unique = true)
    private String teamName;

    // 팀 설명
    private String details;

    @OneToOne
    private Line line;

    @OneToMany(mappedBy = "team")
    @Builder.Default
    @ToString.Exclude
    private List<TeamRule> teamRules = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    @Builder.Default
    @ToString.Exclude
    private List<Member> members = new ArrayList<>();

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}