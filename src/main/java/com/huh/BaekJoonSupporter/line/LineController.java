package com.huh.BaekJoonSupporter.line;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LineController {

    @GetMapping("/line")
    public String lineTest() {
        return "layout";
    }

}
