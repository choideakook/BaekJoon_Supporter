package com.huh.BaekJoonSupporter.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/signup")
    public String signup() {
        return "/member/signup_form";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/signup")
    public String signup(@Valid MemberCreateForm memberCreateForm) {
        memberService.create(memberCreateForm.getName(), memberCreateForm.getPassword1(), memberCreateForm.getToken());
        return "redirect:/";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String login() {
        return "/member/login_form";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public String showMe() {
        return "/member/me";
    }
}
