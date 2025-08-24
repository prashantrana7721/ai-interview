package com.ainterview.backend.controller;

import com.ainterview.backend.model.InterviewQuestion;
import com.ainterview.backend.repository.InterviewQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ainterview.backend.service.AiAnswerService;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Random;




@RestController
@RequestMapping("/api")
public class InterviewController {

    @Autowired
    private InterviewQuestionRepository questionRepo;

    @Autowired
    private AiAnswerService aiAnswerService;

    @GetMapping("/ask")
    public InterviewQuestion getQuestion() {
        List<InterviewQuestion> all = questionRepo.findAll();
        if (all.isEmpty()) {
            throw new RuntimeException("❌ No interview questions found in the database.");
        }
        return all.get(new Random().nextInt(all.size()));
    }

    @GetMapping("/ai-answer")
    public ResponseEntity<String> getAiAnswer(@RequestParam String question) {
        String answer = aiAnswerService.getAnswer(question);
        return ResponseEntity.ok(answer);
    }
}
