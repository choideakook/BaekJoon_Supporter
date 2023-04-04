package com.huh.BaekJoonSupporter.category.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryForm {

    @NotEmpty(message = "제목을 입력해주세요")
    @Size(min = 1,max = 50)
    private String name;

    @NotEmpty(message = "카테고리 소개를 입력해주세요.")
    @Size(max = 100)
    private String about;
}
