package com.rayan.Bank.exception;

public class CantConnectToDB extends Exception{
    public CantConnectToDB(String message) {
        super(message);
    }

    public CantConnectToDB(String message, Throwable cause) {
        super(message, cause);
    }
}
