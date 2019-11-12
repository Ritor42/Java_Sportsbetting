package com.example.sportsbetting.exception;

public class NotEnoughBalanceException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 7101425943484347045L;

    public NotEnoughBalanceException() {
    }

    public NotEnoughBalanceException(String message) {
        super(message);
    }

    public NotEnoughBalanceException(Throwable cause) {
        super(cause);
    }

    public NotEnoughBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
}
