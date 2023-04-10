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
    
    @Enumerated(EnumType.STRING)
    private SolvedStatus solvedStatus;  // 성공 , 실패 여부

}
