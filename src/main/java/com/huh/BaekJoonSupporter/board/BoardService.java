package com.huh.BaekJoonSupporter.board;

import com.huh.BaekJoonSupporter.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;

    //-- create board --//
    @Transactional
    public Long create(String title, String post, Member member) {
        Board board =
        return saveBoard.getId();
    }

    //-- find by id --//
    public Board findOne(Long id) {
        Optional<Board> board = repository.findById(id);

        if (board.isPresent())
            return board.get();

        return null;
    }
}
