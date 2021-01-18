package com.example.biblePoke.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bible")
@NoArgsConstructor
public class PokemonAdvice {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String bibleVerse;

    @Column
    private Integer pokemonId;

    public PokemonAdvice(String bibleVerse) {
        this.bibleVerse = bibleVerse;
    }
}
