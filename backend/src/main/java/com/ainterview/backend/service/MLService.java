package com.ainterview.backend.service;

import com.ainterview.backend.model.EvaluationRequest;
import com.ainterview.backend.model.EvaluationResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MLService {

    private final String ML_API_URL = "http://localhost:8001/evaluate"; // Python ML API

    private final RestTemplate restTemplate = new RestTemplate();

    public EvaluationResponse evaluateAnswer(EvaluationRequest request) {
        return restTemplate.postForObject(ML_API_URL, request, EvaluationResponse.class);
    }
}
