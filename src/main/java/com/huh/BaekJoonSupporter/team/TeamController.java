package com.huh.BaekJoonSupporter.team;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeamController {
    @GetMapping("/test")
    public String urlTest() {
        return "layout";
    }
}
