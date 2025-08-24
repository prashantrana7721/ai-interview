package com.ainterview.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InterviewLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String question;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String idealAnswer;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String candidateAnswer;

    private double keywordScore;
    private double semanticScore;
    private String strength;
    private String sentiment;

    // Getters and Setters
}

