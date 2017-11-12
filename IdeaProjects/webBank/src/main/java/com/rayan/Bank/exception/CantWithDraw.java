package com.rayan.Bank.exception;

public class CantWithDraw extends Exception {

    public CantWithDraw(String message) {
        super(message);
    }

    public CantWithDraw(String message, Throwable cause) {
        super(message, cause);
    }
}
