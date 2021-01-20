package com.example.biblePoke.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "pokemon")
@Getter
@Setter
@NoArgsConstructor
public class PokemonData {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String pokemonName;

    @Column
    private String pokemonAbility;

    @OneToOne(mappedBy = "pokemonData")
    private PokemonAdvice pokemonAdvice;

    public PokemonData(String pokemonName, String pokemonAbility) {
        this.pokemonName = pokemonName;
        this.pokemonAbility = pokemonAbility;
    }
}
