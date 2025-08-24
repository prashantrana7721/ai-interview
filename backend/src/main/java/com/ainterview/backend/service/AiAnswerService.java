package com.ainterview.backend.service;

import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class AiAnswerService {

    private final OpenAiService openAiService;

    public AiAnswerService(@Value("${openai.api.key}") String apiKey) {
        // Uses OpenAiService constructor with timeout support
        this.openAiService = new OpenAiService(apiKey, Duration.ofSeconds(60));
        System.out.println("✅ OpenAI service initialized with timeout.");
    }

    public String getAnswer(String question) {
        try {
            ChatMessage systemMessage = new ChatMessage("system", "You are a helpful interview assistant.");
            ChatMessage userMessage = new ChatMessage("user", question);

            ChatCompletionRequest request = ChatCompletionRequest.builder()
                    .model("gpt-4") // You can switch to gpt-3.5-turbo if needed
                    .messages(List.of(systemMessage, userMessage))
                    .temperature(0.7)
                    .maxTokens(500)
                    .build();

            return openAiService.createChatCompletion(request)
                    .getChoices()
                    .get(0)
                    .getMessage()
                    .getContent();
        } catch (Exception e) {
            System.err.println("❌ Error in getAnswer: " + e.getMessage());
            return "Sorry, I couldn't generate an answer right now.";
        }
    }
}
