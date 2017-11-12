package com.rayan.Bank.model.entity;

import javax.persistence.*;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.List;
@Entity
@Table(name = "Customer")
public class CustomerEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerID")
    private int customerID;
    @Column(name = "customerNumber")
    private String customerNumber;
    @Column(name = "name")
    private String name;
    @Column(name = "family")
    private String family;
    @Column(name = "eMail")
    private String eMail;

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinColumn(name = "CustomerID")
     List<DepositEntity> deposits;

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public List<DepositEntity> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<DepositEntity> deposits) {
        this.deposits = deposits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }



    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }


}
