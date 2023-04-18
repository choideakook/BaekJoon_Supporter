package com.huh.BaekJoonSupporter.line;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class LineMessageForm {

    @NotEmpty(message = "전송할 메세지를 입력해주세요.")
    private String message;

}
