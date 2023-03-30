package com.huh.BaekJoonSupporter.comment;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentForm {
    @NotEmpty(message = "댓글을 입력해주세요")
    private String comment;
}
