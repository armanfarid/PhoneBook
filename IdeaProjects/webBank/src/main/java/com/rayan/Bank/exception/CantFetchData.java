package com.rayan.Bank.exception;

public class CantFetchData extends Exception {
    public CantFetchData(String message) {
        super(message);
    }

    public CantFetchData(String message, Throwable cause) {
        super(message, cause);
    }
}
