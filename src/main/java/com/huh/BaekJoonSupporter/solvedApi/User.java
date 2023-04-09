package com.huh.BaekJoonSupporter.solvedApi;

import com.huh.BaekJoonSupporter.member.Member;
import com.huh.BaekJoonSupporter.teamrule.TeamRule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id @GeneratedValue
    private Long id;

//    @OneToOne(fetch = FetchType.LAZY)
//    @Column(unique = true)
//    private Member handle;  // member 에 handle(백준 id) 추가

    private Integer solvedCount; // 푼 문제 수

//    @ManyToOne(fetch = FetchType.LAZY)
//    private TeamRule teamRule;
    /**
    *  teamrule 에서
     *  푼문제수, 난이도별 문제 값을 얻어 가야함 teamrule 은 단순 DTO/VO/DAO ?
     */
}
