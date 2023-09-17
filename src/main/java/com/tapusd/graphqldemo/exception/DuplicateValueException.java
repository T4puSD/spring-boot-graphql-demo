package com.tapusd.graphqldemo.exception;

public class DuplicateValueException extends RuntimeException {

    public DuplicateValueException() {
        super("Duplicate Key Exception");
    }

    public DuplicateValueException(String message) {
        super(message);
    }

    public DuplicateValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
