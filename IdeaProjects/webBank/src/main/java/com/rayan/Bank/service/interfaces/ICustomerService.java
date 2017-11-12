package com.rayan.Bank.service.interfaces;

import com.rayan.Bank.DTO.ReportDTO;
import com.rayan.Bank.exception.*;
import com.rayan.Bank.model.entity.CustomerEntity;
import net.sf.dynamicreports.report.exception.DRException;

import java.io.FileNotFoundException;
import java.util.List;

public interface ICustomerService {
    public void insert(CustomerEntity customerEntity) throws CantFetchData, CantAddData, CantCastToOtherClass;
    public void Update(CustomerEntity customerEntity) throws CantUpdateData, CantCastToOtherClass, CantFetchData;
    public void delete(CustomerEntity customerEntity) throws CantDeleteData, CantCastToOtherClass, CantFetchData;
    public CustomerEntity search(CustomerEntity customerEntity) throws CantCastToOtherClass, CantFetchData;
    public void customerReport(CustomerEntity customerEntity, ReportDTO reportDTO) throws CantFetchData, DRException, FileNotFoundException, FileNotFound;

    public List<CustomerEntity> customerSearchByName(CustomerEntity customerEntity) throws CantFetchData;
}

