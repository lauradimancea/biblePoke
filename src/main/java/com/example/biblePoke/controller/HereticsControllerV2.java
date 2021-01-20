package com.example.biblePoke.controller;

import com.example.biblePoke.entity.PokemonAdvice;
import com.example.biblePoke.entity.PokemonData;
import com.example.biblePoke.model.ReligiousPokemon;
import com.example.biblePoke.service.HereticsService;
import com.example.biblePoke.service.PokemonAdviceService;
import com.example.biblePoke.service.PokemonDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v2")
public class HereticsControllerV2 {
    private final String key;
    private final HereticsService hereticsService;
    private final PokemonAdviceService pokemonAdviceService;
    private final PokemonDataService pokemonDataService;

    public HereticsControllerV2(@Value("${app.key}") String key,
                                HereticsService hereticsService,
                                PokemonAdviceService pokemonAdviceService,
                                PokemonDataService pokemonDataService) {
        this.key = key;
        this.hereticsService = hereticsService;
        this.pokemonAdviceService = pokemonAdviceService;
        this.pokemonDataService = pokemonDataService;
    }

    @PostMapping(path = "/pokemon", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<ReligiousPokemon> getReligiousPokemon(@RequestHeader("key") String requestKey) {

        if (!key.equals(requestKey)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        ReligiousPokemon religiousPokemon = hereticsService.getRandomPokemon();

        PokemonData pokemonData = pokemonDataService.savePokemonData(religiousPokemon.getName(), religiousPokemon.getAbility());
        pokemonAdviceService.savePokemonAdvice(religiousPokemon.getLifeAdvice(), pokemonData);

        return ResponseEntity.ok(religiousPokemon);
    }

    @GetMapping(path = "/pokemonData/id/{id}")
    public HttpEntity<PokemonData> getPokemonData(@PathVariable Integer id) {
        return ResponseEntity.ok(pokemonDataService.getPokemonData(id));
    }

    @GetMapping(path = "/pokemonData/all")
    public HttpEntity<List<PokemonData>> getPokemonData() {
        return ResponseEntity.ok(pokemonDataService.getPokemonData());
    }

    @PostMapping(path = "/pokemonData/ability/{ability}/id/{id}")
    public HttpEntity<String> updatePokemon(@PathVariable String ability,
                                            @PathVariable Integer id) {
        pokemonDataService.updatePokemon(ability, id);
        return ResponseEntity.ok("Pokemon Updated");
    }

    @PostMapping(path = "/pokemonData/obsolete/id/{id}")
    public HttpEntity<String> deletePokemon(@PathVariable Integer id) {
        pokemonDataService.deletePokemonData(id);
        return ResponseEntity.ok("Pokemon Deleted");
    }

    @GetMapping(path = "/pokemonAdvice/id/{id}")
    public HttpEntity<PokemonAdvice> getPokemonAdvice(@PathVariable Integer id) {
        return ResponseEntity.ok(pokemonAdviceService.getPokemonAdvice(id));
    }

    @GetMapping(path = "/pokemonAdvice/all")
    public HttpEntity<List<PokemonAdvice>> getPokemonAdvice() {
        return ResponseEntity.ok(pokemonAdviceService.getPokemonAdvice());
    }
}
