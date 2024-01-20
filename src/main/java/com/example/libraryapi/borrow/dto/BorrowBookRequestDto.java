package com.example.libraryapi.borrow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorrowBookRequestDto {

    private Long bookId;
    private Long customerId;


}
