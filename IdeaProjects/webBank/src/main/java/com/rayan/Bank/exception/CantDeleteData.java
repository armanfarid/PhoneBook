package com.rayan.Bank.exception;

public class CantDeleteData extends Exception {

    public CantDeleteData(String message) {
        super(message);
    }

    public CantDeleteData(String message, Throwable cause) {
        super(message, cause);
    }
}
