package com.huh.BaekJoonSupporter.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String password;

    private String token;

    @OneToMany(mappedBy = "lecture")
    private List<Lecture> lectures;

    @OneToMany(mappedBy = "comment")
    private List<Comment> comments;
}
