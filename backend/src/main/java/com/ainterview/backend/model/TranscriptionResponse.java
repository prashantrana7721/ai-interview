package com.ainterview.backend.model;

public class TranscriptionResponse {
    private String text;

    public TranscriptionResponse() {}
    public TranscriptionResponse(String text) {
        this.text = text;
    }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
