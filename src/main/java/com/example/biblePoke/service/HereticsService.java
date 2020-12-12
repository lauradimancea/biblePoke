package com.example.biblePoke.service;

import com.example.biblePoke.model.ReligiousPokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HereticsService {

    private final BibleClient bibleClient;
    private final PokemonClient pokemonClient;

    @Autowired
    public HereticsService(BibleClient bibleClient, PokemonClient pokemonClient) {
        this.bibleClient = bibleClient;
        this.pokemonClient = pokemonClient;
    }

    public ReligiousPokemon getRandomPokemon() {

        String verse = bibleClient.getVerse();
        String pokemon = pokemonClient.getPokemon();
        return new ReligiousPokemon(pokemon, verse);
    }
}
