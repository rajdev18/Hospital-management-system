package com.example.demo.medical.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class AIService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String AI_SERVICE_URL = "http://localhost:5000";

    public String generateDischargeSummary(String patientName, String diagnosis,
                                            String prescription, String symptoms,
                                            String notes, String doctorName) {
        String url = AI_SERVICE_URL + "/ai/discharge-summary";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("patient_name", patientName);
        body.put("diagnosis", diagnosis);
        body.put("prescription", prescription);
        body.put("symptoms", symptoms);
        body.put("notes", notes);
        body.put("doctor_name", doctorName);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
            url, request, Map.class);

        return (String) response.getBody().get("discharge_summary");
    }
}
