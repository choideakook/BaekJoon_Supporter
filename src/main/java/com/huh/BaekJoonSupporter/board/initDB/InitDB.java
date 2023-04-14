package com.huh.BaekJoonSupporter.board.initDB;

import com.huh.BaekJoonSupporter.board.BoardService;
import com.huh.BaekJoonSupporter.category.Category;
import com.huh.BaekJoonSupporter.category.CategoryService;
import com.huh.BaekJoonSupporter.member.Member;
import com.huh.BaekJoonSupporter.member.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//@Component // db 자동 init 하려면 활성화
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init1() {
        initService.initCategory();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final BoardService boardService;
        private final MemberService memberService;
        private final CategoryService categoryService;

        public void initCategory() {
            memberService.create("init 글쓴이", "1234", "1234");
            Member member = memberService.getMember("init 글쓴이");

            for (int i = 1; i <= 3; i++) {
                Long categoryId = categoryService.create("카테고리 " + i, "설명 " + i);
                Category category = categoryService.getCategory(categoryId);

                for (int j = 1; j <= 30; j++)
                    boardService.create("제목" + i + j, "내용" + i + j, member, category);
            }
        }
    }
}
