package com.example.libraryapi.book.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.libraryapi.book.entity.OrderBook;
import com.example.libraryapi.book.respository.OrderBookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderBookServiceImpl implements OrderBookService {

    private final OrderBookRepository orderBookRepository;

    @Transactional(readOnly = true)
    @Override
    public List<OrderBook> getReservationList() {
        return orderBookRepository.getReservationList();
    }

    @Transactional
    @Override
    public OrderBook order(OrderBook orderBook) {
        return orderBookRepository.save(orderBook);
    }
}
