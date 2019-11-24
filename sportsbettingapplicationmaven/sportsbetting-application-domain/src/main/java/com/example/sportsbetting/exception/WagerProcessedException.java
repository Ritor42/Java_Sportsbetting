package com.example.sportsbetting.exception;

public class WagerProcessedException extends Exception {
    private static final long serialVersionUID = 7101425943784347045L;

    public WagerProcessedException() {
    }

    public WagerProcessedException(String message) {
        super(message);
    }

    public WagerProcessedException(Throwable cause) {
        super(cause);
    }

    public WagerProcessedException(String message, Throwable cause) {
        super(message, cause);
    }
}
