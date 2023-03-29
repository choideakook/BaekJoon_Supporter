package com.huh.BaekJoonSupporter.comment;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String desc;

    private LocalDateTime createDate; // 생성 날짜

    private LocalDateTime modifyDate; // 수정 날짜

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Lecture lecture;

    @Builder
    public Comment(Long id, String desc, LocalDateTime createDate, LocalDateTime modifyDate, Member member, Lecture lecture) {
        this.id = id;
        this.desc = desc;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.member = member;
        this.lecture = lecture;
    }
}
