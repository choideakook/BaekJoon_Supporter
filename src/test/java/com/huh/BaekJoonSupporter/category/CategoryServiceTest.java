package com.huh.BaekJoonSupporter.category;

import com.huh.BaekJoonSupporter.board.Board;
import com.huh.BaekJoonSupporter.board.BoardService;
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
class CategoryServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    BoardService boardService;
    @Autowired
    CategoryService categoryService;

    //-- create method --//
    private Board createBoard(String title, Category category) {
        memberService.create("A", "", "");
        Member member = memberService.getMember("A");
        Long boardId = boardService.create(title, "", member, category);
        return boardService.getBoard(boardId);
    }

    @Test
    void 카테고리_생성과_카테고리_조회() {
        Long categoryId = categoryService.create("categoryA", "aboutA");
        Category category = categoryService.getCategory(categoryId);

        // 저장, 조회 test
        assertThat(category.getName()).isEqualTo("categoryA");
        assertThat(category.getAbout()).isEqualTo("aboutA");

        // 게시물 저장 test
        Board board = createBoard("board", category);
        assertThat(category.getBoards().get(0)).isSameAs(board);

        // 수정 test
        categoryService.modify(category, "ca", "ab");
        assertThat(category.getName()).isEqualTo("ca");
        assertThat(category.getAbout()).isEqualTo("ab");
    }

    @Test
    void 모든_카테고리_조회와_카테고리_삭제() {
        Long categoryId1 = categoryService.create("categoryA", "aboutA");
        Long categoryId2 = categoryService.create("categoryA", "aboutA");
        categoryService.getCategory(categoryId1);
        categoryService.getCategory(categoryId2);

        List<Category> categories1 = categoryService.getCategoryAll();
        assertThat(categories1.size()).isEqualTo(2);

        categoryService.delete(categoryId2);
        List<Category> categories2 = categoryService.getCategoryAll();
        assertThat(categories2.size()).isEqualTo(1);
    }
}