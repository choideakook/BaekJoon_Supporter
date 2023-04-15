package com.huh.BaekJoonSupporter.board;

import com.huh.BaekJoonSupporter.member.Member;
import com.huh.BaekJoonSupporter.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@TestPropertySource(properties = { "spring.config.location=classpath:application.yml" })
class BoardServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    BoardService boardService;

    //-- create method --//
    private Member createMember() {
        memberService.create("A", "", "");
        return memberService.getMember("A");
    }

    private Long createBoard(String title, String post, Member member) {
        return boardService.create(title, post, member);
    }

    @Test
    void 게시물_생성과_게시물_조회() {
        Member member = createMember();
        Long boardId = createBoard("titleA", "postA", member);

        Board board = boardService.getBoard(boardId);

        // board 객체 검증
        assertThat(board.getTitle()).isEqualTo("titleA");
        assertThat(board.getPost()).isEqualTo("postA");
        assertThat(board.getMember()).isSameAs(member);

        Board memberBoards = member.getBoards().get(0);

        // member 객체 검증
        assertThat(memberBoards).isSameAs(board);
    }

    @Test
    void 모든_게시물_조회와_게시물_삭제() {
        Member member = createMember();
        Long board1 = createBoard("A", "A", member);
        Long board2 = createBoard("B", "B", member);
        Long board3 = createBoard("C", "C", member);
        Board findBoard1 = boardService.getBoard(board1);

        // get board all 검증
        List<Board> memberBoards = member.getBoards();
        List<Board> boardAll = boardService.getBoardAll();

        assertThat(memberBoards.size()).isEqualTo(boardAll.size());
        assertThat(memberBoards.get(2)).isEqualTo(boardAll.get(2));

        // delete 검증
        boardService.delete(board1);
        List<Board> boardAll2 = boardService.getBoardAll();
        List<Board> boardList = member.getBoards();

        assertThat(boardList.size()).isEqualTo(boardAll2.size());
        assertThat(boardAll2).isEqualTo(boardList);

        // delete 정밀 점증
        Board findBoard2 = boardService.getBoard(board2);
        assertThat(boardList.contains(findBoard2)).isTrue();
        assertThat(boardAll2.contains(findBoard2)).isTrue();
        assertThat(boardList.contains(findBoard1)).isFalse();
    }


    @Test
    void 게시물_수정() {
        Member member = createMember();
        String titleA = "titleA", postA = "postA";
        Long boardId = createBoard(titleA, postA, member);

        // 중간 확인
        Board board = boardService.getBoard(boardId);
        assertThat(board.getTitle()).isEqualTo(titleA);

        // 수정 검증
        String titleB = "titleB", postB = "postB";
        Long modifiedBoardId = boardService.modify(board, titleB, postB);
        Board modifiedBoard = boardService.getBoard(modifiedBoardId);

        assertThat(modifiedBoard.getTitle()).isEqualTo(titleB);
        assertThat(modifiedBoard.getPost()).isEqualTo(postB);

        // member.getBoards 변환 확인
        Board memberBoards = member.getBoards().get(0);
        assertThat(memberBoards.getTitle()).isEqualTo(titleB);
    }
}