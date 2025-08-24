package com.ainterview.backend.model;

public class EvaluationResponse {
    private String question;
    private String idealAnswer;
    private String candidateAnswer;
    private double semanticScore;
    private double keywordScore;
    private String strength;
    private String sentiment;

    // Getters and Setters
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String getIdealAnswer() { return idealAnswer; }
    public void setIdealAnswer(String idealAnswer) { this.idealAnswer = idealAnswer; }

    public String getCandidateAnswer() { return candidateAnswer; }
    public void setCandidateAnswer(String candidateAnswer) { this.candidateAnswer = candidateAnswer; }

    public double getSemanticScore() { return semanticScore; }
    public void setSemanticScore(double semanticScore) { this.semanticScore = semanticScore; }

    public double getKeywordScore() { return keywordScore; }
    public void setKeywordScore(double keywordScore) { this.keywordScore = keywordScore; }

    public String getStrength() { return strength; }
    public void setStrength(String strength) { this.strength = strength; }

    public String getSentiment() { return sentiment; }
    public void setSentiment(String sentiment) { this.sentiment = sentiment; }
}
