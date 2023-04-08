package com.huh.BaekJoonSupporter.solvedApi;

import com.huh.BaekJoonSupporter.member.MemberRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Component
public class SolvedApiManager {

    private final String BASE_URL = "https://solved.ac/api/v3/user";
    private final String api_user = "/show";
    private final String api_problem = "/problem_stats";
    private final String api_handle = "?handle=";

    private User user;

    private String getUserInformation() throws UnsupportedEncodingException {
        return BASE_URL +
                api_user +
                api_handle;
    }

    private String getProblemStats() throws UnsupportedEncodingException{
        RestTemplate restTemplate = new RestTemplate();
        return BASE_URL +
                api_problem +
                api_handle;
    }

    public ResponseEntity<?> fetch() throws UnsupportedEncodingException{
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Map> resultMap = restTemplate.exchange(getUserInformation() + "wy9295", HttpMethod.GET, entity, Map.class);
        System.out.println(resultMap.getBody());
        return resultMap;
    }

    public List<Map<String, Object>> problemfech() throws UnsupportedEncodingException{
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<List<Map<String, Object>>> resultMap = restTemplate.exchange(getProblemStats() + "wy9295", HttpMethod.GET, entity, new ParameterizedTypeReference<List<Map<String, Object>>>(){});
        System.out.println(resultMap.getBody());
        return resultMap.getBody();
    }
}
