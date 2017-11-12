package com.rayan.Bank.exception;

public class CantAddData extends Exception{
    public CantAddData(String message) {
        super(message);
    }

    public CantAddData(String message, Throwable cause) {
        super(message, cause);
    }
}
