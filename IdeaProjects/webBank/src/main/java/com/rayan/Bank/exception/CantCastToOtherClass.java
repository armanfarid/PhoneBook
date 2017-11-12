package com.rayan.Bank.exception;

public class CantCastToOtherClass extends Exception {
    public CantCastToOtherClass(String message) {
        super(message);
    }

    public CantCastToOtherClass(String message, Throwable cause) {
        super(message, cause);
    }
}
