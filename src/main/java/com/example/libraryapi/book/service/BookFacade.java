package com.example.libraryapi.book.service;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.libraryapi.book.entity.Book;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookFacade {
    private final BookService bookService;
    private final RedissonClient redissonClient;

    @Value("${book.key-prefix}")
    private String bookKeyPrefix;

    public Book order(Long id, int quantity) {
        final RLock lock = redissonClient.getLock(bookKeyPrefix);

        try {
            if (!lock.tryLock(3, 3, TimeUnit.SECONDS)) {
                log.error("lock 회득 실패하였습니다.");
                throw new RuntimeException("lock 회득 실패하였습니다.");
            }
            return bookService.order(id, quantity);
        } catch (InterruptedException e) {
            log.error("InterruptedException 에러가 발생했습니다.", e);
            Thread.currentThread().interrupt();
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
        return Book.builder().build();
    }

    public Book discard(Long id, int quantity) {
        final RLock lock = redissonClient.getLock(bookKeyPrefix);

        try {
            if (!lock.tryLock(3, 3, TimeUnit.SECONDS)) {
                log.error("lock 회득 실패하였습니다.");
                throw new RuntimeException("lock 회득 실패하였습니다.");
            }
            return bookService.discard(id, quantity);
        } catch (InterruptedException e) {
            log.error("InterruptedException 에러가 발생했습니다.", e);
            Thread.currentThread().interrupt();
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
        return Book.builder().build();
    }
}
