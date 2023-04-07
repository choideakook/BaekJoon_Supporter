package com.huh.BaekJoonSupporter.board;

import com.huh.BaekJoonSupporter.DataNotFoundException;
import com.huh.BaekJoonSupporter.category.Category;
import com.huh.BaekJoonSupporter.member.Member;
import com.huh.BaekJoonSupporter.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberService memberService;

    //-- create board--//
    // without category //
    @Transactional
    public Long create(String title, String post, Member member) {
        Board board = Board.create(title, post, member);
        boardRepository.save(board);
        return board.getId();
    }

    // create in category //
    @Transactional
    public Long create(String title, String post, Member member, Category category) {
        Board board = Board.create(title, post, member, category);
        boardRepository.save(board);
        return board.getId();
    }

    //-- find by id --//
    public Board getBoard(Long id) {
        Optional<Board> board = boardRepository.findById(id);

        if (board.isPresent())
            return board.get();

        throw new DataNotFoundException("게시물을 찾을 수 없습니다.");
    }

    //-- find all --//
    public List<Board> getBoardAll() {
        return boardRepository.findAll();
    }


    //-- find all + paging --//
    public Page<Board> getBoardAll(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return boardRepository.findAll(pageable);
    }

    //-- find by category --//
    public Page<Board> getBoard(int page, Category category) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return boardRepository.findByCategory(pageable, category);
    }


    //-- modify --//
    @Transactional
    public Long modify(Board board, String title, String post) {
        Board modifiedBoard = board.modify(title, post);
        boardRepository.save(modifiedBoard);
        return modifiedBoard.getId();
    }

    //-- delete --//
    @Transactional
    public void delete(Long boardId) {
        Board board = this.getBoard(boardId);
        Member member = memberService.getMember(board.getMember().getName());
        member.getBoards().remove(board);
        boardRepository.delete(board);

    }

    //-- view counter --//
    @Transactional
    public void addView(Board board) {
        board.addView();
    }

    //-- recommend counter --//
    @Transactional
    public void addRecommend(Member member, Board board){

    }
}