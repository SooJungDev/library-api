package com.example.libraryapi.config.errorhandling;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    INVALID_INPUT_VALUE(400, "LIBRARY01", "유효하지 않은 input 값입니다."),
    METHOD_NOT_ALLOWED(405, "LIBRARY02", "지원하지 않은 HTTP method 호출하였습니다."),
    INTERNAL_SERVER_ERROR(500, "LIBRARY03", "서버 내부에서 알 수 없는 에러가 발생했습니다."),
    INVALID_TYPE_VALUE(400, "LIBRARY04", "유효하지 않은 Type 값입니다."),
    BOOK_UNAVAILABLE_FOR_BORROW(400, "LIBRARY05", "대여할수 있는 수량이 없습니다."),
    BOOK_NOT_FOUND(404, "LIBRARY06", "존재하지 않는 책입니다.");

    private int status;

    private final String code;
    private final String message;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
