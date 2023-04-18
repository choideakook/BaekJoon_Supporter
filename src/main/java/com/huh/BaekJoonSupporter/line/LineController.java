package com.huh.BaekJoonSupporter.line;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/line")
public class LineController {

//    @PreAuthorize("isAnonymous()")
    @GetMapping("/send")
    public String sendMsg() {
        return "/line/message_form";
    }

}
