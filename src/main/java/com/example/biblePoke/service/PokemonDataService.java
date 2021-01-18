package com.example.biblePoke.service;

import com.example.biblePoke.entity.PokemonData;
import com.example.biblePoke.repository.PokemonDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonDataService {

    private final PokemonDataRepository pokemonDataRepository;

    @Autowired
    public PokemonDataService(PokemonDataRepository pokemonDataRepository) {
        this.pokemonDataRepository = pokemonDataRepository;
    }

    public void savePokemonData(String name, String description) {
        pokemonDataRepository.save(new PokemonData(name, description));
    }
}
