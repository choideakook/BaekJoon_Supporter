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
public class CommentService {

    private final CommentRepository commentRepository;

    // 댓글 생성 //
    public void create(String content, Board board, Member member) { // Member 추가
        Comment comment = Comment.builder()
                .content(content) // 내용
                .createDate(LocalDateTime.now()) // 등록일 (최초 게시)
                .board(board)   // 게시글
                .member(member) // 작성자
                .build(); // 마무리
        this.commentRepository.save(comment); // 댓글 저장
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
    public void modify(Comment comment, String content) {
        Comment comment1 = Comment.builder()
                .id(comment.getId()) // 댓글 id 변경 되는거 수정
                .content(content) // 댓글 수정
                .modifyDate(LocalDateTime.now()) // 수정 날짜
                .build();
        this.commentRepository.save(comment1); // 댓글 저장
    }

    // 댓글 삭제 //
    public void delete(Comment comment) {
        this.commentRepository.delete(comment);
    }
}