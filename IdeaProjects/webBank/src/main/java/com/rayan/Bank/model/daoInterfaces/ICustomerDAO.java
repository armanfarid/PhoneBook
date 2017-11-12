package com.rayan.Bank.model.daoInterfaces;

import com.rayan.Bank.exception.CantCastToOtherClass;
import com.rayan.Bank.exception.CantFetchData;
import com.rayan.Bank.model.entity.CustomerEntity;

import java.util.List;

public interface ICustomerDAO extends IBaseDAO<CustomerEntity>{

    public CustomerEntity search(CustomerEntity customerEntity) throws CantCastToOtherClass, CantFetchData;
    public List<CustomerEntity> searchByName(CustomerEntity customerEntity) throws CantFetchData;

    }
