package com.example.libraryapi.borrow.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BorrowResponseDto {
    private Long id;

    private List<BorrowBookResponseDto> borrowBooks;

    private int totalBorrowBookQuantity;

    private String status;

    private String statusDescription;
}
