package com.example.libraryapi.book.service;

import java.util.List;

import com.example.libraryapi.book.entity.OrderBook;

public interface OrderBookService {

    List<OrderBook> getReservationList();
    OrderBook order(OrderBook orderBook);
}
