package com.huh.BaekJoonSupporter.solvedApi;

import com.huh.BaekJoonSupporter.member.MemberRepository;
import com.huh.BaekJoonSupporter.teamrule.TeamRule;
import com.huh.BaekJoonSupporter.teamrule.TeamRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private MemberRepository memberRepository;

    private SolvedApiManager solvedApiManager;

    private TeamRuleRepository teamRuleRepository;


    // 개인 별 체크 팀 체크 로직 필요 + 배치프로그램 사용하기//
//    public void ruleCheck(User user) {
//        Optional<User> checkuser = this.userRepository.findById(user.getId());
//        if (checkuser.isPresent()) {
//            User updateUser = checkuser.get();
//            if (this.teamRuleRepository.findBySolvedCount() >  // 문제를 못풀었을 경우
//                    updateUser.getSolvedCount() - this.userRepository.findBySolvedCount(updateUser)) {
//                //라인 발송
//            }
//            else {
//                save(updateUser);
//            }
//        }
//    }

//    public User getUser(String handle) {
//        Optional<User> checkuser = this.userRepository.findByHandle(handle);
//        if (!checkuser.isPresent()) {
//            throw new IllegalStateException("없는 회원 입니다.");
//        }
//
//        return checkuser.get();
//    }
}
