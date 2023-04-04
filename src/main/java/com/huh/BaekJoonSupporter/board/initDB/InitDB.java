package com.huh.BaekJoonSupporter.board.initDB;

import com.huh.BaekJoonSupporter.board.BoardService;
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
    public void init() {
        initService.dbInit1();
    }

//    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final BoardService boardService;
        private final MemberService memberService;

        public void dbInit1() {
            memberService.create("init 글쓴이", "", "토큰");

            for (int i = 1; i < 30; i++) {
                Member member = memberService.getMember("init 글쓴이");
                boardService.create("제목" + i, "내용" + i, member);
            }

        }
    }
}
