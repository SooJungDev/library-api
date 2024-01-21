package com.example.libraryapi.borrow.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowRequestDto {

    @NotNull(message = "customerId 는 필수값입니다.")
    private Long customerId;

    @NotNull.List(@NotNull)
    @Size(min = 1, message = "borrowBooks 에 1개 이상은 추가해야 합니다.")
    private List<BorrowBookRequestDto> borrowBooks;
}
