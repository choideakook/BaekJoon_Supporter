package com.huh.BaekJoonSupporter.line;

import com.huh.BaekJoonSupporter.member.MemberCreateForm;
import jakarta.validation.Valid;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/line")
public class LineApiManager {

    private final String my_token = "Bearer 032bJyZzg1uq56SKQvAOJ4WDXjL8YcqWJsbkYlE0TSm";
    private final static String api_url = "https://notify-api.line.me/api/notify?message=";
    private final static String HEADER_AUTH = "Authorization";

    @PreAuthorize("isAnonymous()")
    @PostMapping("/callTest1")
    public String callAPI(@Valid LineMessageForm lineMessageForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/member/login";
        }

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HEADER_AUTH, my_token);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        String apiContext = api_url + lineMessageForm.getMessage();

        ResponseEntity<String> jsonObject = restTemplate.exchange(apiContext, HttpMethod.POST, httpEntity, String.class);

        return "redirect:/line/message_form";
    }
    
}
