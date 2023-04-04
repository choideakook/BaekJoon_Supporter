package com.huh.BaekJoonSupporter.board;

import com.huh.BaekJoonSupporter.board.form.BoardCreateForm;
import com.huh.BaekJoonSupporter.category.Category;
import com.huh.BaekJoonSupporter.category.CategoryService;
import com.huh.BaekJoonSupporter.member.Member;
import com.huh.BaekJoonSupporter.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final CategoryService categoryService;

    //-- 게시판 전체 목록 --//
    @GetMapping("/list")
    public String showList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "-1") Long id,
            Principal principal,
            Model model
    ) {
        Page<Board> paging;

        if (id == -1) {
            paging = boardService.getBoardAll(page);
            model.addAttribute("id", "-1");
        } else {
            Category category = categoryService.getCategory(id);
            paging = boardService.getBoard(page, category);
            model.addAttribute("category", category.getName());
            model.addAttribute("id", category.getId());
        }
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
//    @PreAuthorize("isAuthenticated()") // 로그인 기능 완성 후 활성화 해야됨
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

    //-- 게시물 상세 --//
    @GetMapping("/detail/{id}")
    public String showDetail(
            @PathVariable Long id,
            Principal principal,
            Model model
    ) {
        Board board = boardService.getBoard(id);
        boardService.viewAdder(board);

        model.addAttribute("board", board);
        return "/board/detail";
    }

    //-- 게시물 수정 폼 --//
    @GetMapping("/update/{id}")
//    @PreAuthorize("isAuthenticated()")  // 지금은 권한 없이 아무나 삭제 가능함
    public String showUpdate(
            @PathVariable Long id,
            BoardCreateForm boardCreateForm,
            Principal principal
    ) {
        Board board = boardService.getBoard(id);
        boardCreateForm.setTitle(board.getTitle());
        boardCreateForm.setPost(board.getPost());
        return "/board/create";
    }

    //-- 게시물 수정 처리 --//
    @PostMapping("/update/{id}")
//    @PreAuthorize("isAuthenticated()")  // 지금은 권한 없이 아무나 삭제 가능함
    public String showUpdate(
            @PathVariable Long id,
            Principal principal,
            BoardCreateForm boardCreateForm
    ) {
        Board board = boardService.getBoard(id);
        boardService.modify(board, boardCreateForm.getTitle(), boardCreateForm.getPost());
        return String.format("redirect:/board/detail/%s", id);
    }


    //-- 게시물 삭제 --//
    @GetMapping("/delete/{id}")
//    @PreAuthorize("isAuthenticated()")  // 지금은 권한 없이 아무나 삭제 가능함
    public String showDelete(
            @PathVariable Long id,
            Principal principal
    ) {
        Board board = boardService.getBoard(id);
        Category category = categoryService.getCategory(board.getCategory().getId());
        boardService.delete(id);
        return "redirect:/board/list?id=" + category.getId();
    }
}