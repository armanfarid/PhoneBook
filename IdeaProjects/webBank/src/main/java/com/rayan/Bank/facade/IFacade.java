package com.rayan.Bank.facade;

import com.rayan.Bank.DTO.CustomerDTO;
import com.rayan.Bank.DTO.DepositDTO;
import com.rayan.Bank.DTO.ReportDTO;
import com.rayan.Bank.exception.*;
import net.sf.dynamicreports.report.exception.DRException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IFacade {

    void customerInsert(CustomerDTO customerDTO) throws CantFetchData, CantAddData, CantCastToOtherClass;
    void customerUpdate(CustomerDTO customerDTO) throws CantCastToOtherClass, CantFetchData, CantUpdateData;
    CustomerDTO customerSearch(String ID) throws CantCastToOtherClass, CantFetchData;
    void customerDelete(CustomerDTO customerDTO) throws CantCastToOtherClass, CantFetchData, CantDeleteData;
    void depositInsert(DepositDTO depositDTO) throws CantFetchData, CantAddData;
    DepositDTO depositSearch(String ID) throws CantFetchData;
    void depositUpdate(DepositDTO depositDTO) throws CantUpdateData, CantFetchData;
    void depositDelete(DepositDTO depositDTO) throws CantDeleteData, CantFetchData;
    void depositDeposit(DepositDTO depositDTO,long amount) throws CantUpdateData;
    void depositWithDaraw(DepositDTO depositDTO,long amount) throws CantUpdateData, CantFetchData, NotEnoughBalance;
    void depositTransfer(DepositDTO depositDTO1,DepositDTO depositDTO2,long amount) throws CantUpdateData, CantFetchData, NotEnoughBalance;
    void depositReport(ReportDTO reportDTO) throws CanNotGetReport, CantFetchData, DRException, IOException, FileNotFound;
    void customerReport(ReportDTO reportDTO) throws CantFetchData, DRException, FileNotFoundException, FileNotFound;
    void depositForACustomer(ReportDTO reportDTO,CustomerDTO customerDTO) throws CantFetchData;

    List<CustomerDTO> customerSearchByName(CustomerDTO customerDTO) throws CantFetchData;
    void importToDB (String path) throws ClassNotFoundException, CantCastToOtherClass, CantFetchData, CantAddData, IOException;
    void exportFromDB(String path) throws CantFetchData, IO, FileNotFound;

    List<DepositDTO> depositSearchByCustomerID(DepositDTO depositDTO,CustomerDTO customerDTO);




}
