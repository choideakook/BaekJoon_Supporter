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

    public Member create(String name, String password, String token) {
        Member member = Member.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .token(token)
                .build();
        return memberRepository.save(member);
    }

    public Member getMember(String name) {
        Optional<Member> member = memberRepository.findByName(name);
        if(member.isPresent()) {
            return member.get();
        } else {
            throw new DataNotFoundException("member not found");
        }
    }

    public void modify(Member member, String password, String token) {
        Member member1 = member.toBuilder()
                .password(passwordEncoder.encode(password))
                .token(token)
                .build();
        memberRepository.save(member1);
    }

    public void delete(Member member) {
        memberRepository.delete(member);
    }
}