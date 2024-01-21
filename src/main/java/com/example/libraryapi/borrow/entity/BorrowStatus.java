package com.example.libraryapi.borrow.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BorrowStatus {
    BORROW("대여 중"),
    RETURN("반납 완료");

    private final String description;
}
