package com.huh.BaekJoonSupporter.team;

import com.huh.BaekJoonSupporter.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne
//    private Long teamId; // leaderId

    private String teamName;

//    @OneToOne
//    private Line line;

//    @OneToMany
//    private List<TeamRule> teamRules;

    @OneToMany(mappedBy = "team")
    private List<Member> members;
}
