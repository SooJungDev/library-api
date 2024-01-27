package com.example.libraryapi.book.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderType {

    RESERVATION("예약 주문"),
    NOW("즉시 주문");

    private final String description;
}
