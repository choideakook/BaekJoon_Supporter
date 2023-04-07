package com.huh.BaekJoonSupporter.board;

import com.huh.BaekJoonSupporter.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findAll(Pageable pageable);

    Page<Board> findByCategory(Pageable pageable, Category category);
}
