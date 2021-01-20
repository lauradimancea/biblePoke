package com.example.biblePoke.repository;

import com.example.biblePoke.entity.PokemonData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface PokemonDataRepository extends JpaRepository<PokemonData, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE PokemonData p SET p.pokemonAbility = :pokemonAbility WHERE p.id = :id")
    void updatePokemonData(@Param("pokemonAbility") String pokemonAbility,
                           @Param("id")Integer id);
}
