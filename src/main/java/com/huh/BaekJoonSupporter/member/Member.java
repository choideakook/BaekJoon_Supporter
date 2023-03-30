package com.huh.BaekJoonSupporter.member;

import com.huh.BaekJoonSupporter.board.Board;
import com.huh.BaekJoonSupporter.comment.Comment;
import com.huh.BaekJoonSupporter.team.Team;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String password;

    private String token;

    @OneToMany(mappedBy = "member")
    private List<Board> boards;

    @ManyToOne
    private Team team;

    @OneToMany(mappedBy = "member")
    private List<Comment> comments;
}
