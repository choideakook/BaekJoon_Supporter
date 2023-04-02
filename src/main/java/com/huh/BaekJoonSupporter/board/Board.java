package com.huh.BaekJoonSupporter.board;

import com.huh.BaekJoonSupporter.comment.Comment;
import com.huh.BaekJoonSupporter.member.Member;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity @Builder(toBuilder = true)
@Getter @ToString
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Board {

    //-- field --//
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;


    private String title;
    private String post;
    private Integer view;

    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modifyDate;

    @ManyToOne(fetch = LAZY)
    private Member member;

    @OneToMany(mappedBy = "board")
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    //-- create method --//
    protected static Board create(String title, String post, Member member) {
        Board board = Board
                .builder()
                .title(title)
                .post(post)
                .member(member)
                .view(0)
                .build();

        member.getBoards().add(board);
        return board;
    }

    //-- business method --//

    //- modify -//
    protected Board modify(String title, String post) {
        return this.toBuilder()
                .title(title)
                .post(post)
                .build();
    }

    //- view adder -//
    protected void addView() {
        this.view++;
    }
}