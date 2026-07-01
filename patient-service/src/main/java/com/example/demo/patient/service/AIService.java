package com.example.demo.patient.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class AIService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String AI_SERVICE_URL = "http://localhost:5000";

    // check symptoms
    public String checkSymptoms(String symptoms) {
        String url = AI_SERVICE_URL + "/ai/symptom-checker";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("symptoms", symptoms);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
            url, request, Map.class);

        return (String) response.getBody().get("recommendation");
    }

    // patient Q&A
    public String askQuestion(String question) {
        String url = AI_SERVICE_URL + "/ai/patient-qa";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("question", question);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
            url, request, Map.class);

        return (String) response.getBody().get("answer");
    }
}