package com.huh.BaekJoonSupporter.Lecture;

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
public class Lecture {

    //-- field --//
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;
    private String desc;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @ManyToOne
    private Member member;

    @OneToOne(mappedBy = "lecture", fetch = LAZY)
    private List<Comment> comments = new ArrayList<>();


    //-- 편의 method --//
    private void addMember(Member member) {
        this.member = member;
        member.getLecture.add(this);
    }

    //-- create method --//
    public static Lecture createLecture(String title, String desc, Member member) {
        Lecture lecture = new Lecture();
        lecture.title = title;
        lecture.desc = desc;
        lecture.createDate = LocalDateTime.now();
        lecture.addMember(member);
        return lecture;
    }

    //-- business method --//

    // update - title , desc
    public void updateLecture(String title, String desc) {
        this.title = title;
        this.desc = desc;
        this.modifyDate = LocalDateTime.now();
    }
}
