package com.example.libraryapi.book.dto;

import java.time.ZonedDateTime;

import com.example.libraryapi.book.entity.OrderType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderBookResponseDto {
    private Long bookId;
    private String title;
    private int quantity;
    private int totalStockQuantity;
    private ZonedDateTime orderAt;
}
