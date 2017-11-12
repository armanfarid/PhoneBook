package com.rayan.Bank.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Deposit")
public class DepositEntity  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "depositID")
    private int depositID;
    @Column(name = "type")
    private   String type;
    @Column(name = "depositNumber")
    private   String depositNumber;
    @Column(name = "balance")
    private   String balance;
    @ManyToOne
    @JoinColumn(name = "CustomerID")
    private CustomerEntity customerEntity;

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
