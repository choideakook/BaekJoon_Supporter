package com.huh.BaekJoonSupporter.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void create_modify(){
        //create and getMember
        Member createMember = memberService.create("user1", "1234", "aaaa");
        Member member = memberService.getMember("user1");

        assertThat(createMember).isEqualTo(member);

        // modify
        memberService.modify(member, "1234", "bbbb");
        member = memberService.getMember("user1");

        assertThat(member.getToken()).isEqualTo("bbbb");

        //delete
        memberService.delete(member);
        Member member1 = memberRepository.findByName("user1").orElse(null);
        assertThat(member1).isNull();;
    }
}
