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


    //-- 편의 method --//
    private void addMember(Member member) {
        this.member = member;
        member.getBoards().add(this);
    }

    //-- create method --//
    public static Board createLecture(String title, String post, Member member) {
        Board lecture = new Board();
        lecture.title = title;
        lecture.post = post;
        lecture.createDate = LocalDateTime.now();
        lecture.addMember(member);
        return lecture;
    }

    //-- business method --//

    // update - title , desc
    public void updateLecture(String title, String post) {
        this.title = title;
        this.post = post;
        this.modifyDate = LocalDateTime.now();
    }
}
