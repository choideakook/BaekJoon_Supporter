package com.huh.BaekJoonSupporter.comment;

import com.huh.BaekJoonSupporter.board.Board;
import com.huh.BaekJoonSupporter.board.BoardService;
import com.huh.BaekJoonSupporter.member.Member;
import com.huh.BaekJoonSupporter.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final BoardService boardService;
    private final MemberService memberService;
    private final CommentService commentService;

    // 댓글 생성 메서드 //
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String create(
            Model model,
            @PathVariable("id") Long id,
            @Valid CommentForm commentForm,
            BindingResult bindingResult,
            Principal principal
    ) {
        Board board = this.boardService.getBoard(id); // 입력받은 id로 게시글 번호 확인 board 변수에 저장
        Member member = this.memberService.getMember(principal.getName());
        if (bindingResult.hasErrors()) {  // 오류 있으면 다시 작성
            model.addAttribute("board", board); //
            return "board/detail";// 게시글 html 리턴
        }
        Comment comment = this.commentService.create(commentForm.getComment(), board, board.getMember()); // 답변 저장
        return String.format("redirect:/board/detail/%s#comment_%s", comment.getBoard().getId(), comment.getId()); // 게시글 번호 입력 받아서 게사글로 이동
    }

    // 댓글 수정 GET 메서드 // 확인 필요!!
    @PreAuthorize("isAuthenticated()") // 권한이 있는 사용자만보임
    @GetMapping("/modify/{id}")
    public String modify(CommentForm commentForm, @PathVariable("id") Long id, Principal principal) {
        Comment comment = this.commentService.getComment(id);
        if (!comment.getMember().getName().equals(principal.getName())) { // 댓글 작성한 회원정보와 일치 여부 확인
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다");
        }
        commentForm.setComment(comment.getContent());
        return "수정";// 댓글 폼 html
    }

    // 댓글 수정 POST 메서드 //
    @PreAuthorize("isAuthenticated()") // 권한이 있는 사용자만보임
    @PostMapping("/modify/{id}")
    public String modify(@Valid CommentForm commentForm, BindingResult bindingResult, @PathVariable("id") Long id, Principal principal) {
        if (bindingResult.hasErrors()) { //에러 발생시
            return "수정"; // 댓글 폼 html 리턴
        }
        Comment comment = this.commentService.getComment(id);
        if (!comment.getMember().getName().equals(principal.getName())) {// 댓글 작성한 회원정보와 일치 여부 확인
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다");
        }
        this.commentService.modify(comment, commentForm.getComment());
        return "comment/comment";
//        return String.format("redirect:/게시글 html/%s", comment.getBoard()); // 게시글 번호 확인해서 해당 게시글 html 리턴
    }

    // 댓글 삭제 메서드 //
    @PreAuthorize("isAuthenticated()") // 권한이 있는 사용자만보임
    @GetMapping("/delete/{id}")
    public String delete(Principal principal, @PathVariable("id") Long id) {
        Comment comment = this.commentService.getComment(id);
        if (!comment.getMember().getName().equals(principal.getName())) {// 댓글 작성한 회원정보와 일치 여부 확인
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다");
        }
        this.commentService.delete(comment);
        return "comment/comment";
//        return String.format("redirect:/게시글 html 작성/%s", comment.getBoard()); // 게시글 html, 게시글 번호 리턴
    }
}
