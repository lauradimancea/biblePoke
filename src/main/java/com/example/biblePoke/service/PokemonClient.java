package com.example.biblePoke.service;

import com.example.biblePoke.model.Pokemon;
import com.example.biblePoke.model.PokemonAndAbility;
import com.example.biblePoke.model.ResponseBodyPokemon;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public PokemonAndAbility getPokemon() {

        ResponseEntity<ResponseBodyPokemon> responseEntity;

        String namePokemon;
        String ability;
        String url;
        Pokemon pokemon;

        try {
            responseEntity = getPokemonResponse();
            pokemon = responseEntity.getBody().getResults().get(0);
            namePokemon = pokemon.getName();
            url = pokemon.getUrl();
        } catch (Exception e) {
            return new PokemonAndAbility("namePokemon", "ability");
        }

        ability = getAbility(url);
        return new PokemonAndAbility(namePokemon, ability);
    }

    private String getAbility(String url) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode root = mapper.readTree(getPokemonAbility(url).getBody());
            JsonNode name = root.path("abilities").get(0).get("ability").get("name");
            return name.toString();
        } catch (Exception e) {
            return "no ability";
        }
    }

    private  ResponseEntity<String> getPokemonAbility(String url) {
        return restTemplate.getForEntity(url, String.class);
    }

    private ResponseEntity<ResponseBodyPokemon> getPokemonResponse() {
        return restTemplate.getForEntity(String.format(POKEMON_URL,generateRandomNumber()), ResponseBodyPokemon.class);
    }

    private int generateRandomNumber(){

        Random random = new Random();
        return random.nextInt(1118-1) + 1;
    }
}
