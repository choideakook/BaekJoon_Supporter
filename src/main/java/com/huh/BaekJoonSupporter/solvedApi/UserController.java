package com.huh.BaekJoonSupporter.solvedApi;

import com.huh.BaekJoonSupporter.member.Member;
import com.huh.BaekJoonSupporter.member.MemberRepository;
import com.huh.BaekJoonSupporter.member.MemberService;
import com.huh.BaekJoonSupporter.teamrule.TeamRule;
import com.huh.BaekJoonSupporter.teamrule.TeamRuleService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    private TeamRuleService teamRuleService;

    @GetMapping("/test")
    public void abc() throws IOException, ParseException, UnsupportedEncodingException {
        Long temp = userService.getTier("Gold");
        System.out.println(temp);
    }

    @GetMapping("/test2")
    public void abc1() throws IOException, ParseException, UnsupportedEncodingException {
        Long temp = userService.getSolvedCount();
        System.out.println(temp);
    }
}
