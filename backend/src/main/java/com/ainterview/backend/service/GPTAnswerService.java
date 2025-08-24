package com.ainterview.backend.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GPTAnswerService {
    private final OpenAiService openAiService;

    public GPTAnswerService(@Value("${openai.api.key}") String apiKey) {
        this.openAiService = new OpenAiService(apiKey);
    }

    public String generateIdealAnswer(String question) {
        String prompt = "What is a perfect answer to this interview question: " + question;

        CompletionRequest request = CompletionRequest.builder()
                .prompt(prompt)
                .model("gpt-3.5-turbo-instruct")
                .maxTokens(200)
                .temperature(0.5)
                .build();

        CompletionChoice choice = openAiService.createCompletion(request).getChoices().get(0);
        return choice.getText().trim();
    }
}
