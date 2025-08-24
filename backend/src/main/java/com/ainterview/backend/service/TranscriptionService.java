package com.ainterview.backend.service;

import com.ainterview.backend.model.TranscriptionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;

@Service
public class TranscriptionService {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    public TranscriptionResponse transcribe(File audioFile) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.openai.com/v1/audio/transcriptions";

        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("model", "whisper-1");
        body.add("file", new FileSystemResource(audioFile));
        body.add("response_format", "json");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setBearerAuth(openaiApiKey);

        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<TranscriptionResponse> response = restTemplate.postForEntity(url, requestEntity, TranscriptionResponse.class);

        return response.getBody();
    }
}
