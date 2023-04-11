package com.huh.BaekJoonSupporter.solvedApi;

import com.huh.BaekJoonSupporter.DataNotFoundException;
import com.huh.BaekJoonSupporter.member.MemberRepository;
import com.huh.BaekJoonSupporter.teamrule.TeamRule;
import com.huh.BaekJoonSupporter.teamrule.TeamRuleRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    private final MemberRepository memberRepository;

    private final SolvedApiManager solvedApiManager;

    private final TeamRuleRepository teamRuleRepository;



    public User getUser(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new DataNotFoundException("user not found");
        }
    }

    //== 티어확인 및 문제풀이 리턴==//
//    public Long getTier(String tier) throws IOException, ParseException, UnsupportedEncodingException{
//        JSONArray test = this.solvedApiManager.getProblemCount();
//        Long temp = 0L;
//        if (test.size() > 0) {
//            for (int i = 0; i < test.size(); i++) {
//                JSONObject jsonObj = (JSONObject) test.get(i);
//                if (tier.equals("Bronze")) {
//                    if (jsonObj.get("level").equals(1L) || jsonObj.get("level").equals(2L) ||jsonObj.get("level").equals(3L) ||jsonObj.get("level").equals(4L) ||jsonObj.get("level").equals(5L)) {
//                        temp += (Long) jsonObj.get("solved");
//                    }
//                } else if (tier.equals("Silver")) {
//                    if (jsonObj.get("level").equals(6L) || jsonObj.get("level").equals(7L) ||jsonObj.get("level").equals(8L) ||jsonObj.get("level").equals(9L) ||jsonObj.get("level").equals(10L)) {
//                        temp += (Long) jsonObj.get("solved");
//                    }
//                }else if (tier.equals("Gold")) {
//                    if (jsonObj.get("level").equals(11L) || jsonObj.get("level").equals(12L) ||jsonObj.get("level").equals(13L) ||jsonObj.get("level").equals(14L) ||jsonObj.get("level").equals(15L)) {
//                        temp += (Long) jsonObj.get("solved");
//                    }
//                }else if (tier.equals("Platinum")) {
//                    if (jsonObj.get("level").equals(16L) || jsonObj.get("level").equals(17L) ||jsonObj.get("level").equals(18L) ||jsonObj.get("level").equals(19L) ||jsonObj.get("level").equals(20L)) {
//                        temp += (Long) jsonObj.get("solved");
//                    }
//                }else if (tier.equals("Diamond")) {
//                    if (jsonObj.get("level").equals(21L) || jsonObj.get("level").equals(22L) ||jsonObj.get("level").equals(23L) ||jsonObj.get("level").equals(24L) ||jsonObj.get("level").equals(25L)) {
//                        temp += (Long) jsonObj.get("solved");
//                    }
//                }else if (tier.equals("Ruby")) {
//                    if (jsonObj.get("level").equals(26L) || jsonObj.get("level").equals(27L) ||jsonObj.get("level").equals(28L) ||jsonObj.get("level").equals(29L) ||jsonObj.get("level").equals(30L)) {
//                        temp += (Long) jsonObj.get("solved");
//                    }
//                }
//            }
//        }
//        return temp;
//    }
    public Long getTier(String tier) throws IOException, ParseException, UnsupportedEncodingException{
        JSONArray test = this.solvedApiManager.getProblemCount();
        Long temp = 0L;
        if (test.size() > 0) {
            for (Object o : test) {
                JSONObject jsonObj = (JSONObject) o;
                switch (tier) {
                    case "Bronze" -> {
                        if (jsonObj.get("level").equals(1L) || jsonObj.get("level").equals(2L) || jsonObj.get("level").equals(3L) || jsonObj.get("level").equals(4L) || jsonObj.get("level").equals(5L)) {
                            temp += (Long) jsonObj.get("solved");
                        }
                    }
                    case "Silver" -> {
                        if (jsonObj.get("level").equals(6L) || jsonObj.get("level").equals(7L) || jsonObj.get("level").equals(8L) || jsonObj.get("level").equals(9L) || jsonObj.get("level").equals(10L)) {
                            temp += (Long) jsonObj.get("solved");
                        }
                    }
                    case "Gold" -> {
                        if (jsonObj.get("level").equals(11L) || jsonObj.get("level").equals(12L) || jsonObj.get("level").equals(13L) || jsonObj.get("level").equals(14L) || jsonObj.get("level").equals(15L)) {
                            temp += (Long) jsonObj.get("solved");
                        }
                    }
                    case "Platinum" -> {
                        if (jsonObj.get("level").equals(16L) || jsonObj.get("level").equals(17L) || jsonObj.get("level").equals(18L) || jsonObj.get("level").equals(19L) || jsonObj.get("level").equals(20L)) {
                            temp += (Long) jsonObj.get("solved");
                        }
                    }
                    case "Diamond" -> {
                        if (jsonObj.get("level").equals(21L) || jsonObj.get("level").equals(22L) || jsonObj.get("level").equals(23L) || jsonObj.get("level").equals(24L) || jsonObj.get("level").equals(25L)) {
                            temp += (Long) jsonObj.get("solved");
                        }
                    }
                    case "Ruby" -> {
                        if (jsonObj.get("level").equals(26L) || jsonObj.get("level").equals(27L) || jsonObj.get("level").equals(28L) || jsonObj.get("level").equals(29L) || jsonObj.get("level").equals(30L)) {
                            temp += (Long) jsonObj.get("solved");
                        }
                    }
                }
            }
        }
        return temp;
    }

    public Long getSolvedCount() throws IOException, ParseException, UnsupportedEncodingException{
        Long re = Long.parseLong(this.solvedApiManager.getSolvedCount());
        return re;
    }
}
