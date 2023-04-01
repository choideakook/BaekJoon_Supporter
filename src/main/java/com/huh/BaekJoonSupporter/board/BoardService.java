package com.huh.BaekJoonSupporter.board;

import com.huh.BaekJoonSupporter.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;

    //-- create board--//
    @Transactional
    public Long create(String title, String post, Member member) {
        Board board = Board.create(title, post, member);
        repository.save(board);
        return board.getId();
    }

    //-- find by id --//
    public Board getBoard(Long id) {
        Optional<Board> board = repository.findById(id);

        if (board.isPresent())
            return board.get();

        return null;
    }

    //-- find all --//
    public List<Board> getBoardAll() {
        return repository.findAll();
    }

    //-- modify --//
    @Transactional
    public void modify(Board board, String title, String post) {
        Board modifiedBoard = board.modify(title, post);
        repository.save(modifiedBoard);
    }

    //-- delete --//
    @Transactional
    public void delete(Long boardId) {
        Board board = this.getBoard(boardId);
        repository.delete(board);
    }
}