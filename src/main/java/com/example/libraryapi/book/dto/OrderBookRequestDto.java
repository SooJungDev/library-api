package com.example.libraryapi.book.dto;

import java.time.ZonedDateTime;

import com.example.libraryapi.book.entity.OrderType;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderBookRequestDto {

    @NotNull(message = "책 ID값은 필수값입니다.")
    @Min(value = 1, message = "책 ID값은 1 이상이어야 합니다.")
    private Long id;

    @Min(value = 1, message = "quantity 는 1 이상이어야 합니다.")
    private int quantity;

    @NotNull(message = "orderType 은 필수값입니다.")
    private OrderType orderType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime orderAt;

}
