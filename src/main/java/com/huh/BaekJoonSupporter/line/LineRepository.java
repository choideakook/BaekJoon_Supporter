package com.huh.BaekJoonSupporter.line;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LineRepository extends JpaRepository<Line, Long> {

    Optional<Line> findByToken(String token);
}
