package org.example.exception;

public class AppBadException extends RuntimeException {
    public AppBadException(String message) {
        super(message);
    }
}
