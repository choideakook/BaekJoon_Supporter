package com.huh.BaekJoonSupporter.board;

import com.huh.BaekJoonSupporter.member.Member;
import com.huh.BaekJoonSupporter.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberService memberService;

    //-- create board--//
    @Transactional
    public Long create(String title, String post, Member member) {
        Board board = Board.create(title, post, member);
        boardRepository.save(board);
        return board.getId();
    }

    //-- find by id --//
    public Board getBoard(Long id) {
        Optional<Board> board = boardRepository.findById(id);

        if (board.isPresent())
            return board.get();

        return null;
    }

    //-- find all --//
    public List<Board> getBoardAll() {
        return boardRepository.findAll();
    }

    //-- modify --//
    @Transactional
    public Long modify(Board board, String title, String post) {
        Board modifiedBoard = board.modify(title, post);
        boardRepository.save(modifiedBoard);
        return board.getId();
    }

    //-- delete --//
    @Transactional
    public void delete(Long boardId) {
        Board board = this.getBoard(boardId);
        Member member = memberService.getMember(board.getMember().getName());
        member.getBoards().remove(board);
        boardRepository.delete(board);

    }
}