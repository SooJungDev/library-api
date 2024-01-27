package com.example.libraryapi.book.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.libraryapi.book.BookMapper;
import com.example.libraryapi.book.dto.OrderBookRequestDto;
import com.example.libraryapi.book.dto.OrderBookResponseDto;
import com.example.libraryapi.book.entity.Book;
import com.example.libraryapi.book.entity.OrderBook;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookFacade {
    private final BookService bookService;
    private final OrderBookService orderBookService;
    private final RedissonClient redissonClient;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final BookMapper bookMapper;

    @Value("${book.key-prefix}")
    private String bookKeyPrefix;

    @Value("${book.reserve-topic}")
    public String bookReserveTopic;

    public OrderBook order(OrderBookRequestDto request) {
        final RLock lock = redissonClient.getLock(bookKeyPrefix);

        try {
            if (!lock.tryLock(3, 3, TimeUnit.SECONDS)) {
                log.error("lock 회득 실패하였습니다.");
                throw new RuntimeException("lock 회득 실패하였습니다.");
            }
            Book book = bookService.findById(request.getId());
            OrderBook orderBook = OrderBook.createOrderBook(book, request.getQuantity(), request.getOrderType(),
                                                            request.getOrderAt());
            return orderBookService.order(orderBook);
        } catch (InterruptedException e) {
            log.error("InterruptedException 에러가 발생했습니다.", e);
            Thread.currentThread().interrupt();
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
        return OrderBook.builder().build();
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

    @Transactional
    public void reserveOrder() {
        final List<OrderBook> reservationList = orderBookService.getReservationList();
        for (OrderBook orderBook : reservationList) {
            orderBook.reserveBook();
            final OrderBookResponseDto bookResponseDto = bookMapper.of(orderBook);
            try {
                kafkaTemplate.send(bookReserveTopic, objectMapper.writeValueAsString(bookResponseDto));
            } catch (JsonProcessingException e) {
                log.error("JsonProcessingException 에러가 발생했습니다.", e);
            }

        }
    }
}
