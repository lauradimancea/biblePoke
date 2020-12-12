package com.example.biblePoke.controller;

import com.example.biblePoke.model.ReligiousPokemon;
import com.example.biblePoke.service.HereticsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HereticsController {

    private final String key;
    private final HereticsService hereticsService;

    public HereticsController(@Value("${app.key}") String key,
                              HereticsService hereticsService) {
        this.key = key;
        this.hereticsService = hereticsService;
    }

    @GetMapping(path = "/pokemon", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<ReligiousPokemon> getReligiousPokemon(@RequestHeader("key") String requestKey) {

        if (!key.equals(requestKey)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(hereticsService.getRandomPokemon());
    }
}
