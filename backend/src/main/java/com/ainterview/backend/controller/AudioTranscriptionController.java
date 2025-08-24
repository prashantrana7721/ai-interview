package com.ainterview.backend.controller;

import com.theokanning.openai.OpenAiHttpException;
import com.theokanning.openai.audio.CreateTranscriptionRequest;
import com.theokanning.openai.audio.TranscriptionResult;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/audio")
public class AudioTranscriptionController {

    private final OpenAiService openAiService;

    public AudioTranscriptionController(@Value("${openai.api.key}") String apiKey) {
        this.openAiService = new OpenAiService(apiKey);
    }

    @PostMapping(value = "/transcribe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> transcribe(@RequestParam("file") MultipartFile file) {
        try {
            // Convert MultipartFile to File
            File audioFile = convertMultiPartToFile(file);

            CreateTranscriptionRequest request = CreateTranscriptionRequest.builder()
                    .model("whisper-1")
                    .responseFormat("json")
                    .build();

            // Get the transcription result object
            TranscriptionResult result = openAiService.createTranscription(request, audioFile);
            return ResponseEntity.ok(result.getText());

        } catch (OpenAiHttpException e) {
            return ResponseEntity.badRequest().body("❌ OpenAI API error: " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("❌ File error: " + e.getMessage());
        }
    }

    // Helper to convert MultipartFile to File
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = File.createTempFile("upload", file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return convFile;
    }
}
