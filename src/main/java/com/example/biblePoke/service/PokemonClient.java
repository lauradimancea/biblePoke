package com.example.biblePoke.service;

import com.example.biblePoke.model.ResponseBodyPokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class PokemonClient {

    private static final String POKEMON_URL = "https://pokeapi.co/api/v2/pokemon/?offset=%s&limit=1";


    private final RestTemplate restTemplate;

    @Autowired
    public PokemonClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getPokemon() {
        ResponseEntity<ResponseBodyPokemon> responseEntity;

        try {
            responseEntity = getPokemonResponse();
          return  responseEntity.getBody().getResults().get(0).getName();
        } catch (Exception e) {
            return "The requested pokemon does not exist";
        }

    }
    private ResponseEntity<ResponseBodyPokemon> getPokemonResponse() {

        return restTemplate.getForEntity(String.format(POKEMON_URL,generateRandomNumber()), ResponseBodyPokemon.class);
    }
    private int generateRandomNumber(){
        Random random = new Random();

        return random.nextInt(1118-1) + 1;
    }
}
