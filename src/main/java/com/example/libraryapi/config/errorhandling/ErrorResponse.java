package com.example.libraryapi.config.errorhandling;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.libraryapi.common.exception.BookQuantityOverException;
import com.example.libraryapi.common.exception.BookUnavailableForBorrowedException;
import com.example.libraryapi.common.exception.EntityNotFoundException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private String message;

    private int status;

    private List<FieldError> errors;

    private String code;

    private ErrorResponse(final ErrorCode code, final List<FieldError> errors) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.errors = errors;
        this.code = code.getCode();
    }

    private ErrorResponse(final ErrorCode code) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.code = code.getCode();
        this.errors = new ArrayList<>();
    }

    private ErrorResponse(final ErrorCode code, String message) {
        this.message = message;
        this.status = code.getStatus();
        this.code = code.getCode();
        this.errors = new ArrayList<>();
    }

    public static ErrorResponse of(final ErrorCode code, final BindingResult bindingResult) {
        return new ErrorResponse(code, FieldError.of(bindingResult));
    }

    public static ErrorResponse of(final ErrorCode code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(final ErrorCode code, final List<FieldError> errors) {
        return new ErrorResponse(code, errors);
    }

    public static ErrorResponse of(MethodArgumentTypeMismatchException e) {
        final String value = e.getValue() == null ? "" : e.getValue().toString();
        final List<FieldError> errors = FieldError.of(e.getName(), value,
                                                      e.getErrorCode());
        return new ErrorResponse(ErrorCode.INVALID_TYPE_VALUE, errors);
    }

    public static ErrorResponse of(BookUnavailableForBorrowedException e) {
        return new ErrorResponse(ErrorCode.BOOK_UNAVAILABLE_FOR_BORROW, e.getMessage());
    }

    public static ErrorResponse of(EntityNotFoundException e) {
        return new ErrorResponse(ErrorCode.COMMON_ENTITY_NOT_FOUND, e.getMessage());
    }

    public static ErrorResponse of(BookQuantityOverException e) {
        return new ErrorResponse(ErrorCode.BOOK_QUANTITY_OVER, e.getMessage());
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {

        private String field;

        private String value;

        private String reason;

        private FieldError(final String field, final String value, final String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldError> of(final String field, final String value, final String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        private static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                              .map(error -> new FieldError(
                                      error.getField(),
                                      error.getRejectedValue() == null ? "" :
                                      error.getRejectedValue().toString(),
                                      error.getDefaultMessage()))
                              .collect(Collectors.toList());
        }
    }
}
