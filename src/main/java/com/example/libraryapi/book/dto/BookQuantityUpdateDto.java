package com.example.libraryapi.book.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookQuantityUpdateDto {

    @NotNull(message = "책 ID값은 필수값입니다.")
    @Min(value = 1, message = "책 ID값은 1 이상이어야 합니다.")
    private Long id;

    @Min(value = 1, message = "quantity 는 1 이상이어야 합니다.")
    private int quantity;
}
