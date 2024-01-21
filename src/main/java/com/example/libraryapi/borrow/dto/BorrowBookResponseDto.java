package com.example.libraryapi.borrow.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BorrowBookResponseDto {
    private Long id;

    private Long bookId;

    private int quantity;

    private String status;

    private String statusDescription;
}
