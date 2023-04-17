package com.huh.BaekJoonSupporter.line;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/line")
public class LineApiManager {

    private final String my_token = "Bearer 032bJyZzg1uq56SKQvAOJ4WDXjL8YcqWJsbkYlE0TSm";
    private final String api_url = "https://notify-api.line.me/api/notify?message=";
    private final static String HEADER_AUTH = "Authorization";

    @GetMapping("/callTest1")
    public String callAPI() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HEADER_AUTH, my_token);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        String apiContext = api_url + "aslkdnalsknd";

        System.out.println(restTemplate.exchange(apiContext, HttpMethod.POST, httpEntity, String.class));

        return "";
    }
    
}
