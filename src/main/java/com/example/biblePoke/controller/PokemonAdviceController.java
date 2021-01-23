package com.example.biblePoke.controller;

import com.example.biblePoke.entity.PokemonAdvice;
import com.example.biblePoke.service.PokemonAdviceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/pokemonAdvice")
public class PokemonAdviceController {
    private final String key;
    private final PokemonAdviceService pokemonAdviceService;

    public PokemonAdviceController(@Value("${app.key}") String key,
                                    PokemonAdviceService pokemonAdviceService) {
        this.key = key;
        this.pokemonAdviceService = pokemonAdviceService;
    }

    @GetMapping(path = "/id/{id}")
    public HttpEntity<PokemonAdvice> getPokemonAdvice(@PathVariable Integer id) {
        return ResponseEntity.ok(pokemonAdviceService.getPokemonAdvice(id));
    }

    @GetMapping(path = "/all")
    public HttpEntity<List<PokemonAdvice>> getPokemonAdvice() {
        return ResponseEntity.ok(pokemonAdviceService.getPokemonAdvice());
    }
}
