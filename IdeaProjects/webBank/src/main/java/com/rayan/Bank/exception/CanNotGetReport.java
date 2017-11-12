package com.rayan.Bank.exception;

public class CanNotGetReport extends Exception {
    public CanNotGetReport(String message) {
        super(message);
    }

    public CanNotGetReport(String message, Throwable cause) {
        super(message, cause);
    }
}
