package com.example.libraryapi.common.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookScheduler {

    @Scheduled(cron = "30 * * * * *")
    public void preorder() {
        log.info("preorder");
    }
}
