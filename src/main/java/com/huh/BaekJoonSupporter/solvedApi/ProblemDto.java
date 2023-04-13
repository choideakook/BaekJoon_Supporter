package com.huh.BaekJoonSupporter.solvedApi;

import lombok.Builder;

@Builder(toBuilder = true)
public class ProblemDto {
    private String level;
    private Integer solved;
}
