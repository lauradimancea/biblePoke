package com.example.biblePoke.model;

import lombok.Getter;

import java.util.List;

@Getter
public class ResponseBodyPokemon {
    private String count;
    private String next;
    private String previous;
    private List<Pokemon> results;



}
