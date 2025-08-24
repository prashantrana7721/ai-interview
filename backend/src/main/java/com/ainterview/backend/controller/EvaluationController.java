package com.ainterview.backend.controller;

import com.ainterview.backend.model.EvaluationResult;
import com.ainterview.backend.service.EvaluationService;
import com.ainterview.backend.model.EvaluationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/evaluate")
public class EvaluationController {
    private String question;
    private String candidateAnswer;
    private String idealAnswer;
    private double keywordMatchScore;
    private double semanticScore;
    private String strength;
    private String sentiment;
    private String feedback;

    private final EvaluationService evaluationService;

    @Autowired
    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    public EvaluationResult evaluateAnswer(@RequestBody Map<String, String> request) {
        String question = request.get("question");
        String candidateAnswer = request.get("candidateAnswer");

        if (question == null || candidateAnswer == null) {
            throw new IllegalArgumentException("Both 'question' and 'candidateAnswer' are required.");
        }

        EvaluationResponse mlResponse = evaluationService.evaluate(question, candidateAnswer);

        // Map EvaluationResponse to EvaluationResult (frontend-friendly model)
        EvaluationResult result = new EvaluationResult();
        result.setQuestion(mlResponse.getQuestion());
        result.setIdealAnswer(mlResponse.getIdealAnswer());
        result.setCandidateAnswer(mlResponse.getCandidateAnswer());
        result.setSemanticScore(mlResponse.getSemanticScore());
        result.setKeywordScore(mlResponse.getKeywordScore());
        result.setStrength(mlResponse.getStrength());
        result.setSentiment(mlResponse.getSentiment());

        return result;
    }

}
