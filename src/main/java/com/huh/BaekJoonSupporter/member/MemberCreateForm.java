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

    @NotEmpty
    private String name;

    @NotEmpty
    private String password1;

    @NotEmpty
    private String password2;

    @NotEmpty
    private String token;
}
