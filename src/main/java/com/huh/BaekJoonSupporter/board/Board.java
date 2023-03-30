package com.huh.BaekJoonSupporter.board;

import com.huh.BaekJoonSupporter.comment.Comment;
import com.huh.BaekJoonSupporter.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Board {

    //-- field --//
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;


    private String title;
    private String post;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @ManyToOne(fetch = LAZY)
    private Member member;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();
}