package com.example.biblePoke.service;

import com.example.biblePoke.entity.PokemonAdvice;
import com.example.biblePoke.entity.PokemonData;
import com.example.biblePoke.repository.PokemonAdviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonAdviceService {
    private final PokemonAdviceRepository pokemonAdviceRepository;

    @Autowired
    public PokemonAdviceService(PokemonAdviceRepository pokemonAdviceRepository) {
        this.pokemonAdviceRepository = pokemonAdviceRepository;
    }

    public void savePokemonAdvice(String advice, PokemonData pokemonData) {
        pokemonAdviceRepository.save(new PokemonAdvice(advice, pokemonData));
    }
    public PokemonAdvice getPokemonAdvice(Integer id) {
        return pokemonAdviceRepository.getOne(id);
    }

    public List<PokemonAdvice> getPokemonAdvice() {
        return pokemonAdviceRepository.findAll();
    }
}
