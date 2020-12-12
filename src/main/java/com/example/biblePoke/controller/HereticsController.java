package com.example.biblePoke.controller;

import com.example.biblePoke.model.ReligiousPokemon;
import com.example.biblePoke.service.HereticsService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HereticsController {

    private final HereticsService hereticsService;

    public HereticsController(HereticsService hereticsService) {
        this.hereticsService = hereticsService;
    }

    @GetMapping(path = "/pokemon", produces = "application/json")
    public HttpEntity<ReligiousPokemon> getReligiousPokemon() {
        return ResponseEntity.ok(hereticsService.getRandomPokemon());
    }
}
