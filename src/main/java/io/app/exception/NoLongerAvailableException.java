package io.app.exception;

public class NoLongerAvailableException extends RuntimeException {
    public NoLongerAvailableException(String message) {
        super(message);
    }
}
