package com.rayan.Bank.service.interfaces;

import com.rayan.Bank.DTO.ReportDTO;
import com.rayan.Bank.exception.*;
import com.rayan.Bank.model.entity.CustomerEntity;
import com.rayan.Bank.model.entity.DepositEntity;
import net.sf.dynamicreports.report.exception.DRException;

import java.io.IOException;

public interface IDepositService {
    public void insert(DepositEntity depositEntity) throws CantFetchData, CantAddData;
    public void update(DepositEntity depositEntity) throws CantUpdateData, CantFetchData;
    public void delete(DepositEntity depositEntity) throws CantDeleteData, CantFetchData;
    public DepositEntity search(DepositEntity depositEntity) throws CantFetchData;
    public void deposit(DepositEntity depositEntity,long amount) throws CantUpdateData;
    public void withdraw(DepositEntity depositEntity,long amount) throws CantUpdateData, CantFetchData, NotEnoughBalance;
    public void transfer(DepositEntity depositEntity,DepositEntity depositEntity1,long amount) throws CantUpdateData, CantFetchData, NotEnoughBalance;
    public void depositReport(ReportDTO reportDTO,CustomerEntity customerEntity) throws CanNotGetReport, CantFetchData, DRException, IOException, FileNotFound;
    public void depositForACustomer(ReportDTO reportDTO,CustomerEntity customerEntity) throws CantFetchData;
}
