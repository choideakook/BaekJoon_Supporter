package com.huh.BaekJoonSupporter.teamrule;

import com.huh.BaekJoonSupporter.team.TeamService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teamrule")
public class TeamRuleController {

    private final TeamRuleService teamRuleService;

    private final TeamService teamService;

    @GetMapping("/list")
    public String showList()  {
        return "layout";
    }

    @GetMapping("/create")
    public String showCreate() {
        return "teamrule/create";
    }



//    @PostMapping("/create")
//    public String ProcessCreate(@Valid CreateForm createForm) {
//        Team team = teamService.create(5L,"테스트팀", null, null);
//        TeamRule teamRule = teamRuleService.create(team, createForm.targetTypeCode, createForm.targetNumber);
//
//        // 실패시 메인
//        if(teamRule ==null)
//            return "redirect:/test";
//
//        // 규칙 생성 시 목록으로
//        return "redirect:teamrule/list";
//
//    }

}
