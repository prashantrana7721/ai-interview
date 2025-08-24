package com.ainterview.backend.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/audio")
public class AudioStreamController {

    @PostMapping(value = "/stream", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String handleStreamChunk(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("Received audio chunk of size: " + file.getSize());
        // TODO: Save file temporarily and send it to Whisper+PyAnnote microservice
        return "Chunk received";
    }
}
