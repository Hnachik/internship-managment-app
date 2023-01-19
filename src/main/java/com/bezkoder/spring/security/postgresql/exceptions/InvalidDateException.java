package com.bezkoder.spring.security.postgresql.exceptions;

public class InvalidDateException extends RuntimeException {

    public InvalidDateException(final String message) {
        super(message);
    }

    public InvalidDateException(final String message, Exception ex) {
        super(message, ex);
    }
}
