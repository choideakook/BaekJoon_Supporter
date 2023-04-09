package com.huh.BaekJoonSupporter.solvedApi;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByHandle(String handle);

    Integer findBySolvedCount(User user);
}
