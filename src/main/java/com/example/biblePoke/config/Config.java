package com.example.biblePoke.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders createHeaders(@Value("${bible.api.key}") String apiKey){
        return new HttpHeaders() {{
            set("api-key", apiKey);
        }};
    }
}
