package com.huh.BaekJoonSupporter.team;

import com.huh.BaekJoonSupporter.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;
    private final MemberService memberService;

    // 팀 정보 //
    @GetMapping("/info")
    public String showInfo() {
        return "/team/team_info";
    }

    // 팀 생성 //
    @GetMapping("/create")
    public String create() {
        return "/team/create";
    }

    // 팀 멤버 추가 //
    @GetMapping("/add")
    public String add() {
        return "/team/add";
    }

    // 팀 멤버 추가 //
    @GetMapping("/modify")
    public String modify() {
        return "/team/modify";
    }
}
