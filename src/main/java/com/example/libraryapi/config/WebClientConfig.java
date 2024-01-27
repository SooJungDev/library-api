package com.example.libraryapi.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.netty.http.client.HttpClient;

@Slf4j
@Configuration
public class WebClientConfig {

    @Value("${book-url}")
    private String bookUrl;

    @Bean
    public WebClient webClient() {
        final HttpClient httpClient = HttpClient.create()
                                                .responseTimeout(Duration.ofSeconds(10));

        return WebClient.builder()
                        .clientConnector(new ReactorClientHttpConnector(httpClient))
                        .baseUrl(bookUrl)
                        .build();
    }
}

