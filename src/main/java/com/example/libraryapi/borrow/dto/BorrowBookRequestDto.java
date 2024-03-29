package com.example.libraryapi.borrow.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowBookRequestDto {

    @NotNull(message = "bookId 필수값입니다.")
    private Long bookId;

    @Min(value = 1, message = "quantity 는 1 이상이어야 합니다.")
    private int quantity;
}
