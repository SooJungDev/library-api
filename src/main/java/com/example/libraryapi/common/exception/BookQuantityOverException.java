package com.example.libraryapi.common.exception;

import java.io.Serial;

public class BookQuantityOverException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 891645850592056445L;

    public BookQuantityOverException() {
        super();
    }

    public BookQuantityOverException(String message) {
        super(message);
    }

    public BookQuantityOverException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookQuantityOverException(Throwable cause) {
        super(cause);
    }

    protected BookQuantityOverException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
