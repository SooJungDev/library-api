package com.example.libraryapi.book.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequestDto {
    @NotNull(message = "title 는 필수값입니다.")
    private String title;

    @NotNull(message = "author 는 필수값입니다.")
    private String author;

    @NotNull(message = "publisher 는 필수값입니다.")
    private String publisher;

    @NotNull(message = "isbn (국제 표준도서번호)를 는 필수값입니다.")
    private String isbn; // 국제 표준 도서번호

    @Min(value = 1, message = "quantity 는 1 이상이어야 합니다.")
    private int quantity;

    @Min(value = 0, message = "price은 0 이상이어야 합니다.")
    private int price;
}
