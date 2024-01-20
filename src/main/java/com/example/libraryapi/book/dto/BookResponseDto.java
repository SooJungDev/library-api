package com.example.libraryapi.book.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BookResponseDto {
    private Long id;

    private String title;

    private String author;

    private String publisher;

    private String isbn; // 국제 표준 도서번호

    private int price;

    private int quantity; // 책 수량

    private int availableQuantity; // 대여 가능한 수량

    private String bookStatus;
    private String bookStatusDescription;
}
