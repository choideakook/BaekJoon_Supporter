package com.huh.BaekJoonSupporter.board.form;

import com.huh.BaekJoonSupporter.category.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardCreateForm {

    @NotEmpty(message = "제목을 입력해주세요.")
    @Size(min = 1, max = 150)
    private String title;
    @NotEmpty(message = "내용을 입력해주세요.")
    private String post;

    private String category;
}
