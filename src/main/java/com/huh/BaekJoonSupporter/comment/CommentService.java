package com.huh.BaekJoonSupporter.comment;

import com.huh.BaekJoonSupporter.board.Board;
import com.huh.BaekJoonSupporter.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.huh.BaekJoonSupporter.DataNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    // 댓글 생성 //
    public Comment create(String content, Board board, Member member) {
        Comment comment = Comment.builder()
                .content(content) // 내용
                .createDate(LocalDateTime.now()) // 등록일 (최초 게시)
                .board(board)   // 게시글
                .member(member) // 작성자
                .build(); // 마무리
        board.getComments().add(comment); // board 에 추가
        member.getComments().add(comment); // member 에 추가
        this.commentRepository.save(comment); // 댓글 저장
        return comment;
    }

    // 댓글 조회 //
    public Comment getComment(Long id) {
        Optional<Comment> comment = this.commentRepository.findById(id);
        if (comment.isPresent()) {  // 댓글이 있다면 댓글 정보 리턴
            return comment.get();
        } else {
            throw new DataNotFoundException("comment not found");  //
        }
    }

    // 댓글 수정 //
    public void modify(Comment comment, String contents) {
        Comment comment1 = comment.toBuilder()   // toBuilder 로 수정!!!
                .content(contents)
                .modifyDate(LocalDateTime.now())
                .build();
        this.commentRepository.save(comment1);
    }

    // 댓글 삭제 //
    public void delete(Comment comment) {
        Board board = comment.getBoard();
        board.getComments().remove(comment);
        this.commentRepository.delete(comment);
    }
}
