package com.manicure.app.domain.exceptions;

public class EmptyPropertyException extends RuntimeException {
    public EmptyPropertyException(String message) {
        super(message);
    }
}
