package com.ainterview.backend.service;

import com.ainterview.backend.model.EvaluationRequest;
import com.ainterview.backend.model.EvaluationResponse;
import com.ainterview.backend.model.InterviewLog;
import com.ainterview.backend.repository.InterviewLogRepository;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EvaluationService {

    private final OpenAiService openAiService;
    private final MLService mlService;

    private String getIdealAnswerWithRetry(String question, int maxAttempts) {
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                CompletionRequest completionRequest = CompletionRequest.builder()
                        .prompt("Provide a concise, ideal technical answer to this interview question:\n" + question)
                        .model("gpt-3.5-turbo-instruct")
                        .maxTokens(200)
                        .temperature(0.7)
                        .build();

                String answer = openAiService.createCompletion(completionRequest)
                        .getChoices().get(0).getText().trim();

                if (answer != null && !answer.isBlank()) {
                    System.out.println("✅ GPT Answer generated on attempt " + attempt);
                    return answer;
                }

            } catch (Exception e) {
                System.err.println("❌ GPT call failed on attempt " + attempt + ": " + e.getMessage());
            }

            try {
                Thread.sleep(1000); // wait 1 second before retrying
            } catch (InterruptedException ignored) {}
        }

        return "Not available";
    }


    @Autowired
    private InterviewLogRepository interviewLogRepository;

    public EvaluationService(@Value("${openai.api.key}") String apiKey, MLService mlService) {
        this.openAiService = new OpenAiService(apiKey);
        this.mlService = mlService;
    }

    public EvaluationResponse evaluate(String question, String candidateAnswer) {
        // Step 1: Generate ideal answer from GPT
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Provide a concise, ideal technical answer to this interview question:\n" + question)
                .model("gpt-3.5-turbo-instruct")
                .maxTokens(200)
                .temperature(0.7)
                .build();

        String idealAnswer = getIdealAnswerWithRetry(question, 3);


        // Step 2: Compute keyword match
        double keywordScore = computeKeywordMatch(candidateAnswer, idealAnswer);

        // Step 3: Call ML microservice for semantic/sentiment/strength
        EvaluationRequest mlRequest = new EvaluationRequest();
        mlRequest.setIdealAnswer(idealAnswer);
        mlRequest.setCandidateAnswer(candidateAnswer);

        EvaluationResponse mlResponse = mlService.evaluateAnswer(mlRequest);

        // Step 4: Merge everything into response
        mlResponse.setIdealAnswer(idealAnswer);
        mlResponse.setCandidateAnswer(candidateAnswer);
        mlResponse.setQuestion(question);
        mlResponse.setKeywordScore(keywordScore);

        // Step 5: Save into DB
        InterviewLog log = new InterviewLog();
        log.setQuestion(question);
        log.setIdealAnswer(idealAnswer);
        log.setCandidateAnswer(candidateAnswer);
        log.setSemanticScore(mlResponse.getSemanticScore());
        log.setKeywordScore(keywordScore);
        log.setStrength(mlResponse.getStrength());
        log.setSentiment(mlResponse.getSentiment());

        interviewLogRepository.save(log);

        return mlResponse;
    }

    private double computeKeywordMatch(String candidate, String ideal) {
        List<String> candidateWords = Arrays.asList(candidate.toLowerCase().split("\\W+"));
        List<String> idealWords = Arrays.asList(ideal.toLowerCase().split("\\W+"));

        long matchCount = idealWords.stream().filter(candidateWords::contains).distinct().count();
        return (double) matchCount / idealWords.size();
    }
}
