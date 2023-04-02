package com.huh.BaekJoonSupporter.board;

import com.huh.BaekJoonSupporter.board.form.BoardCreateForm;
import com.huh.BaekJoonSupporter.member.Member;
import com.huh.BaekJoonSupporter.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
<<<<<<< HEAD
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
>>>>>>> DeaKuk40

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    //-- 게시판 전체 목록 --//
    @GetMapping("/list")
    public String showList(
            @RequestParam(defaultValue = "0") int pate,
            Principal principal,
            Model model
    ) {
        Page<Board> paging = boardService.getBoardAll(pate);
        model.addAttribute("paging", paging);
        return "/board/boardList";
    }

    //-- 게시물 생성 폼 --//
    @GetMapping("/create")
//    @PreAuthorize("isAuthenticated()")
    public String showCreateForm(BoardCreateForm boardCreateForm) {
        return "/board/create";
    }

    //-- 게시물 생성 처리 --//
    @PostMapping("/create")
//    @PreAuthorize("isAuthenticated()")
    public String showCreateForm(
            BoardCreateForm boardCreateForm,
            BindingResult bindingResult,
            Principal principal
    ) {
        // 로그인 기능 구현 되면 principal.getName 을 바꿔야 함
        Member member = memberService.getMember("init 글쓴이");
        boardService.create(boardCreateForm.getTitle(), boardCreateForm.getPost(), member);
        return "redirect:/board/list";
    }

    //-- 게시물 디테일 --//
    @GetMapping("/detail/{id}")
    public String showDetail(
            @PathVariable Long id,
            Model model
    ) {
        Board board = boardService.getBoard(id);
        boardService.viewAdder(board);

        model.addAttribute("board", board);
        return "/board/detail";
    }
}