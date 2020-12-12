package com.example.biblePoke.controller;

import com.example.biblePoke.service.BibleClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HereticsController {

    private final BibleClient bibleClient;

    public HereticsController(BibleClient bibleClient) {
        this.bibleClient = bibleClient;
    }

    @GetMapping(path = "/test")
    public HttpEntity<String> test() throws JsonProcessingException {
        return ResponseEntity.ok(bibleClient.getVerse());
    }
}
