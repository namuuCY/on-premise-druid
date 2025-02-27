package com.example.demo.service;

import java.util.Map;
import java.util.function.Consumer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class WebClientService {

    private WebClient webClient;

    public String post(String url, Consumer<HttpHeaders> headers, Object requestBody) throws WebClientResponseException {
        String response;

        webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        response = webClient.post()
                .bodyValue(requestBody)
                .headers(headers)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;
    }

}
