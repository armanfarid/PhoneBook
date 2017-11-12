package com.rayan.Bank.exception;

public class CantUpdateData extends Exception {
    public CantUpdateData(String message) {
        super(message);
    }

    public CantUpdateData(String message, Throwable cause) {
        super(message, cause);
    }
}
