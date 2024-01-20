package com.example.libraryapi.common.exception;

import java.io.Serial;

public class BookUnavailableForBorrowedException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 5676511926149586025L;

    public BookUnavailableForBorrowedException() {
        super();
    }

    public BookUnavailableForBorrowedException(String message) {
        super(message);
    }

    public BookUnavailableForBorrowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookUnavailableForBorrowedException(Throwable cause) {
        super(cause);
    }

    protected BookUnavailableForBorrowedException(String message, Throwable cause, boolean enableSuppression,
                                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
