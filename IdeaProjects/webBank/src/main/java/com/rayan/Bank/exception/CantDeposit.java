package com.rayan.Bank.exception;

public class CantDeposit extends  Exception {
    public CantDeposit(String message) {
        super(message);
    }

    public CantDeposit(String message, Throwable cause) {
        super(message, cause);
    }
}
