package com.huh.BaekJoonSupporter.solvedApi;

import com.huh.BaekJoonSupporter.member.Member;
import com.huh.BaekJoonSupporter.member.MemberRepository;
import com.huh.BaekJoonSupporter.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private UserService userService;

    @Autowired
    private SolvedApiManager solvedApiManager;


    @GetMapping("/test")
    public ResponseEntity<?> fetch() throws UnsupportedEncodingException{
        return ResponseEntity.ok(solvedApiManager.fetch().getBody());
    }

    @GetMapping("/test2")
    public ResponseEntity<List<Map<String, Object>>> fetch2() throws UnsupportedEncodingException{
        return ResponseEntity.ok(solvedApiManager.problemfech());
    }

//    @GetMapping("/{handle}")
//    public ResponseEntity<User> getUser(@PathVariable String handle) {
//        User user = userService.getUser(handle);
//        if (user != null) {
//            return ResponseEntity.ok(user);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

}
