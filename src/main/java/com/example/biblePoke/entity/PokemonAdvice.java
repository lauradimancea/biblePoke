package com.example.biblePoke.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "bible")
@Getter
@Setter
@NoArgsConstructor
public class PokemonAdvice {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String bibleVerse;

    @OneToOne
    @JoinColumn(name = "pokemon_id")
    @JsonIgnore
    private PokemonData pokemonData;

    public PokemonAdvice(String bibleVerse, PokemonData pokemonData) {
        this.bibleVerse = bibleVerse;
        this.pokemonData = pokemonData;
    }
}
