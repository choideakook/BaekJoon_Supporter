package com.huh.BaekJoonSupporter.category;

import com.huh.BaekJoonSupporter.board.Board;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder(toBuilder = true)
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Category {

    //-- field --//
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String about;

    @CreatedDate
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @Builder.Default
    @OneToMany(mappedBy = "category")
    private List<Board> boards = new ArrayList<>();

    //-- create method --//
    protected static Category createCategory(String name, String about) {
        return Category.builder()
                .name(name)
                .about(about)
                .build();
    }

    //-- modify Category --//
    protected Category modifyCategory(String name, String about) {
        return this.toBuilder()
                .name(name)
                .about(about)
                .build();
    }
}
