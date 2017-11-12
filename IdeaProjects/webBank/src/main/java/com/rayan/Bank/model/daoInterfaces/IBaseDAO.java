package com.rayan.Bank.model.daoInterfaces;

import com.rayan.Bank.exception.CantAddData;
import com.rayan.Bank.exception.CantDeleteData;
import com.rayan.Bank.exception.CantFetchData;
import com.rayan.Bank.exception.CantUpdateData;

import java.util.List;

public interface IBaseDAO <T>{

    public void insert(T object) throws CantAddData, CantFetchData;
    public void update(T object) throws  CantUpdateData;
    public void delete(T object) throws CantDeleteData;
    public List<T> getAll() throws CantFetchData;
}
