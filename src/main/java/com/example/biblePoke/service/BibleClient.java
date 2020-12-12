package com.example.biblePoke.service;

import com.example.biblePoke.model.ResponseBodyBible;
import org.jsoup.Jsoup;
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

        ResponseEntity<ResponseBodyBible> responseEntity;
        try {
             responseEntity = getBibleResponse();
            String response = responseEntity.getBody().getData().getContent().replaceAll("[\\d.]", "");
            return html2text(response);
        } catch (Exception e) {
            return "The requested verse does not exist";
        }
    }

    public ResponseEntity<ResponseBodyBible> getBibleResponse() {

        return restTemplate.exchange(BIBLE_URL_VERSE + generateRandomVerse(),
                HttpMethod.GET, new HttpEntity<String>(httpHeaders), ResponseBodyBible.class);
    }

    private String generateRandomVerse() {

        Random random = new Random();
        int first = random.nextInt(14 - 1) + 1;
        int second = random.nextInt(11 - 1) + 1;

        return first + "." + second;
    }

    private static String html2text(String html) {
        return Jsoup.parse(html).text();
    }
}
