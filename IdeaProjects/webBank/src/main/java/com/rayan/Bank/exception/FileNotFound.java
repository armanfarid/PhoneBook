package com.rayan.Bank.exception;

public class FileNotFound  extends Exception{
    public FileNotFound(String message) {
        super(message);
    }

    public FileNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
