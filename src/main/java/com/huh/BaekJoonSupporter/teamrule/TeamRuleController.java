package com.huh.BaekJoonSupporter.teamrule;


import com.huh.BaekJoonSupporter.member.MemberService;
import com.huh.BaekJoonSupporter.team.Team;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teamrule")
public class TeamRuleController {

    private final TeamRuleService teamRuleService;

    private final MemberService memberService;

    @GetMapping("/list")
    public String showList()  {
        return "layout";
    }

    @GetMapping("/create")
    public String Create() {
        return "teamrule/create";
    }

    @PostMapping("/create")
    @ResponseBody
    //테스트 못해봤슴다
    public String Create(@Valid TeamRuleCreateForm createForm, Principal principal) {
        Team team = memberService.getMember(principal.getName()).getTeam();
        TeamRule teamRule = null;
        // 방식(난이도 무관/유관) 선택 전에 난이도 먼저 고른 상태에서, 무관을 할 경우 난이도 default로 변경
        if(createForm.getTarget().equals("무관") && !createForm.getDifficulty().equals("default")) {
            teamRule = teamRuleService.create(team, createForm.getTarget(), "default", createForm.getTargetNumber());
        }
        else {
            teamRule = teamRuleService.create(team, createForm.getTarget(), createForm.getDifficulty(), createForm.getTargetNumber());
        }
        // 실패시 메인
        if(teamRule ==null)
            return "실패";

        // 규칙 생성 시 목록으로
        return "성공";

    }

}
