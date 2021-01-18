package com.example.biblePoke.service;

import com.example.biblePoke.entity.PokemonAdvice;
import com.example.biblePoke.repository.PokemonAdviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonAdviceService {
    private final PokemonAdviceRepository pokemonAdviceRepository;

    @Autowired
    public PokemonAdviceService(PokemonAdviceRepository pokemonAdviceRepository) {
        this.pokemonAdviceRepository = pokemonAdviceRepository;
    }

    public void savePokemonAdvice(String advice) {
        pokemonAdviceRepository.save(new PokemonAdvice(advice));
    }
}
