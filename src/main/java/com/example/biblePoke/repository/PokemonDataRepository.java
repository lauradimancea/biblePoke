package com.example.biblePoke.repository;

import com.example.biblePoke.entity.PokemonData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonDataRepository extends JpaRepository<PokemonData, Integer> {
}
