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

@Entity @Builder
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

    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modifyDate;

    @ManyToOne(fetch = LAZY)
    private Member member;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    //-- create method --//
    public static Board create(String title, String post, Member member) {
        Board board = Board
                .builder()
                .title(title)
                .post(post)
                .member(member)
                .build();

        member.getBoards().add(board);
        return board;
    }

    //-- business method --//

    //- modify -//
    public void modify(String title, String post) {
        this.title = title;
        this.post = post;
    }
}