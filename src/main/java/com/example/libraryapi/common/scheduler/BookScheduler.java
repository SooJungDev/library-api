package com.example.libraryapi.common.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookScheduler {

    private final WebClient webClient;

    @Scheduled(cron = "30 * * * * *")
    public void reserveOrder() {
        log.info("##### BookScheduler reserveOrder #####");
        webClient.post()
                 .uri("/order/reserve")
                 .retrieve()
                 .bodyToMono(String.class)
                 .block();
    }
}
