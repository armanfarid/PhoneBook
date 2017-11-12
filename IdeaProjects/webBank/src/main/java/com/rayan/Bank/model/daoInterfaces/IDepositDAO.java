package com.rayan.Bank.model.daoInterfaces;

import com.rayan.Bank.exception.CantDeposit;
import com.rayan.Bank.exception.CantFetchData;
import com.rayan.Bank.exception.CantWithDraw;
import com.rayan.Bank.model.entity.DepositEntity;

public interface IDepositDAO extends IBaseDAO<DepositEntity> {

    public void Deposit() throws CantDeposit;
    public void withDraw() throws CantWithDraw;
    public void transfer();

    public DepositEntity search(DepositEntity depositEntity) throws CantFetchData;
}
