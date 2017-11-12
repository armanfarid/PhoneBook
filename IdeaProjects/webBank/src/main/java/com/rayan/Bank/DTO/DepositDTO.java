package com.rayan.Bank.DTO;

public class DepositDTO {

    private int depositID;
    private String Type;
    private String depositNumber;
    private String balance;



    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getDepositID() {
        return depositID;
    }

    public void setDepositID(int depositID) {
        this.depositID = depositID;
    }

    public String getDepositNumber() {
        return depositNumber;
    }

    public void setDepositNumber(String depositNumber) {
        this.depositNumber = depositNumber;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance1) {
        balance = balance1;
    }
}
