package com.example.biblePoke.controller;

import com.example.biblePoke.entity.PokemonData;
import com.example.biblePoke.service.PokemonDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/pokemonData")
public class PokemonDataController {
    private final String key;
    private final PokemonDataService pokemonDataService;

    public PokemonDataController(@Value("${app.key}") String key,
                                   PokemonDataService pokemonDataService) {
        this.key = key;
        this.pokemonDataService = pokemonDataService;
    }

    @GetMapping(path = "/id/{id}")
    public HttpEntity<PokemonData> getPokemonData(@PathVariable Integer id) {
        return ResponseEntity.ok(pokemonDataService.getPokemonData(id));
    }

    @GetMapping(path = "/all")
    public HttpEntity<List<PokemonData>> getPokemonData() {
        return ResponseEntity.ok(pokemonDataService.getPokemonData());
    }

    @PostMapping(path = "/ability/{ability}/id/{id}")
    public HttpEntity<String> updatePokemon(@PathVariable String ability,
                                            @PathVariable Integer id) {
        pokemonDataService.updatePokemon(ability, id);
        return ResponseEntity.ok("Pokemon Updated");
    }

    @PostMapping(path = "/obsolete/id/{id}")
    public HttpEntity<String> deletePokemon(@PathVariable Integer id) {
        pokemonDataService.deletePokemonData(id);
        return ResponseEntity.ok("Pokemon Deleted");
    }
}
