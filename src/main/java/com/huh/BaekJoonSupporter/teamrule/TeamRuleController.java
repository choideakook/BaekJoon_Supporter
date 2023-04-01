package com.huh.BaekJoonSupporter.teamrule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teamrule")
public class TeamRuleController {

    private final TeamRuleService teamRuleService;

    @GetMapping("/list")
    public String showList()  {
        return "layout";
    }


}
