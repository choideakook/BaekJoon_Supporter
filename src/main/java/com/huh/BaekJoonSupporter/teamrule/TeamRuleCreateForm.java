package com.huh.BaekJoonSupporter.teamrule;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamRuleCreateForm {
    @NotBlank(message = "목표 선택은 필수입니다.")
    private String target;  // 목표 구분 라디오 박스 얻어올 값
    @NotBlank(message = "난이도 선택은 필수입니다.")
    private String difficulty;  // 목표 구분 라디오 박스 얻어올 값
    @NotBlank(message = "목표 수 설정은 필수입니다.")
    private Long targetNumber;  // 목표 수
}