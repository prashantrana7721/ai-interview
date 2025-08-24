package com.ainterview.backend.controller;

import com.ainterview.backend.model.TranscriptionResponse;
import com.ainterview.backend.service.TranscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/api/transcribe")
public class TranscriptionController {

    @Autowired
    private TranscriptionService transcriptionService;

    @PostMapping
    public ResponseEntity<TranscriptionResponse> transcribeAudio(@RequestParam("audio") MultipartFile audio) throws Exception {
        File tempFile = File.createTempFile("audio", ".mp3");
        audio.transferTo(tempFile);
        TranscriptionResponse result = transcriptionService.transcribe(tempFile);
        tempFile.delete(); // cleanup
        return ResponseEntity.ok(result);
    }
}
