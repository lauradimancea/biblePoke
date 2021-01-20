package com.example.biblePoke.service;

import com.example.biblePoke.entity.PokemonData;
import com.example.biblePoke.repository.PokemonDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonDataService {

    private final PokemonDataRepository pokemonDataRepository;

    @Autowired
    public PokemonDataService(PokemonDataRepository pokemonDataRepository) {
        this.pokemonDataRepository = pokemonDataRepository;
    }

    public PokemonData savePokemonData(String name, String description) {
        return pokemonDataRepository.save(new PokemonData(name, description));
    }

    public PokemonData getPokemonData(Integer id) {
        return pokemonDataRepository.getOne(id);
    }

    public List<PokemonData> getPokemonData() {
        return pokemonDataRepository.findAll();
    }

    public void updatePokemon(String ability, Integer id) {
        pokemonDataRepository.updatePokemonData(ability, id);
    }

    public void deletePokemonData(Integer id) {
        pokemonDataRepository.deleteById(id);
    }
}
