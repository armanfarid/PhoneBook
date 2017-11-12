package com.rayan.Bank.exception;

public class NotEnoughBalance extends Exception{
    public NotEnoughBalance(String message) {
        super(message);
    }

    public NotEnoughBalance(String message, Throwable cause) {
        super(message, cause);
    }
}
