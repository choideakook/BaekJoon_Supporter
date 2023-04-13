package com.huh.BaekJoonSupporter.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreateForm {

    @NotEmpty(message = "ID를 입력해주세요.")
    private String name;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password1;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password2;

    @NotEmpty(message = "토큰을 입력해주세요.")
    private String token;
}
