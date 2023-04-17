package com.huh.BaekJoonSupporter.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@TestPropertySource(properties = { "spring.config.location=classpath:application.yml" })
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void memberTests(){
        // create
        Member createMember = memberService.create("user1", "1234", "aaaa");
        assertThat(memberRepository.findByName("user1")).isNotEmpty();

        // get
        Member member = memberService.getMember("user1");
        assertThat(member).isEqualTo(memberRepository.findByName("user1").get());

        // modify - 패스워드, 토큰 둘 다 변경
        memberService.modify(member, "1111", "bbbb");

        assertThat(passwordEncoder.matches("1111", member.getPassword())).isTrue();
        assertThat(member.getToken()).isEqualTo("bbbb");

        // delete
        memberService.delete(member);
        Member member1 = memberRepository.findByName("user1").orElse(null);
        assertThat(member1).isNull();;
    }
}
