package com.huh.BaekJoonSupporter.member;

import com.huh.BaekJoonSupporter.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void create(String name, String password, String token) {
        Member member = Member.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .token(token)
                .build();
        memberRepository.save(member);
    }

    public Member getMember(String name) {
        Optional<Member> member = memberRepository.findByMemberName(name);
        if(member.isPresent()) {
            return member.get();
        } else {
            throw new DataNotFoundException("member not found");
        }
    }

    public void modify(String name, String password, String token) {
        Member member = Member.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .token(token)
                .build();
        memberRepository.save(member);
    }

    public void delete(Member member) {
        memberRepository.delete(member);
    }
}
