package com.example.biblePoke.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class BibleClient {

    private static final String BIBLE_URL_VERSE = "https://api.scripture.api.bible/v1/bibles/65eec8e0b60e656b-01/verses/REV.";

    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;

    @Autowired
    public BibleClient(RestTemplate restTemplate, HttpHeaders httpHeaders) {
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
    }

    public String getVerse() {
        ObjectMapper mapper = new ObjectMapper();
        ResponseEntity<String> responseEntity;

        try {
             responseEntity = getBibleResponse();
        } catch (Exception e) {
            return "The requested verse does not exist";
        }

        try {
            JsonNode root = mapper.readTree(responseEntity.getBody());
            JsonNode name = root.path("data").get("content");
            return name.toString();
        } catch (Exception e) {
            return "Could not parse response";
        }
    }

    public ResponseEntity<String> getBibleResponse() {

        return restTemplate.exchange(BIBLE_URL_VERSE + generateRandomVerse(),
                HttpMethod.GET, new HttpEntity<String>(httpHeaders), String.class);
    }

    private String generateRandomVerse() {

        Random random = new Random();
        int first = random.nextInt(14 - 1) + 1;
        int second = random.nextInt(11 - 1) + 1;

        return first + "." + second;
    }
}
