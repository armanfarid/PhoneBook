package com.rayan.Bank.service.implemention;
import com.rayan.Bank.DTO.ReportDTO;
import com.rayan.Bank.exception.*;
import com.rayan.Bank.model.daoInterfaces.ICustomerDAO;
import com.rayan.Bank.model.daoInterfaces.IDepositDAO;
import com.rayan.Bank.model.entity.CustomerEntity;
import com.rayan.Bank.model.entity.DepositEntity;
//import com.rayan.Bank.service.Report.BankReports;
import com.rayan.Bank.service.Report.DepositForACustomer;
import com.rayan.Bank.service.Report.DepositsReport;
import com.rayan.Bank.service.interfaces.IDepositService;
import net.sf.dynamicreports.report.exception.DRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component("DepositService")
public class DepositService implements IDepositService {
    @Autowired
    @Qualifier("DepositDAO")
    private IDepositDAO iDepositDAO;
//    @Autowired
//    @Qualifier("DepositsReport")
//    private DepositsReport depositsReport;
    @Autowired
    @Qualifier("CustomerDAO")
    private ICustomerDAO iCustomerDAO;
    @Override
    public void insert(DepositEntity depositEntity) throws CantFetchData, CantAddData {
        if(depositEntity.getDepositNumber().length()==10&& search(depositEntity)==null){

            iDepositDAO.insert(depositEntity);

        }


    }

    @Override
    public void update(DepositEntity depositEntity) throws CantUpdateData, CantFetchData {
        if(depositEntity.getDepositNumber().length()==10&& search(depositEntity)!=null){

            iDepositDAO.update(depositEntity);

        }


    }

    @Override
    public void delete(DepositEntity depositEntity) throws CantDeleteData, CantFetchData {
        if(depositEntity.getDepositNumber().length()==10&& search(depositEntity)!=null){

            iDepositDAO.delete(depositEntity);

        }


    }

    @Override
    public DepositEntity search(DepositEntity depositEntity) throws CantFetchData {

        DepositEntity depositEntity1 = new DepositEntity();
        if(depositEntity.getDepositNumber().length()==10){
            depositEntity1=iDepositDAO.search(depositEntity);
        }
        return depositEntity1;



    }

    @Override
    public synchronized void deposit(DepositEntity depositEntity, long amount) throws CantUpdateData {

        long balance=Long.valueOf(depositEntity.getBalance());
        long result=balance+amount;
        depositEntity.setBalance(Long.toString(result));
        iDepositDAO.update(depositEntity);

    }

    @Override
    public synchronized void withdraw(DepositEntity depositEntity, long amount) throws CantUpdateData, CantFetchData, NotEnoughBalance {
        long balance = Long.valueOf(depositEntity.getBalance());
        if(amount<balance){
            long result = balance-amount;
            depositEntity.setBalance(Long.toString(result));
            update(depositEntity);
        }
        else {
            throw new NotEnoughBalance("mojudi az mablaghe darkhasti kamtar ast");
        }
    }

    @Override
    public  void transfer(DepositEntity depositEntity, DepositEntity depositEntity1, long amount) throws CantUpdateData, CantFetchData, NotEnoughBalance {
        long sourceBalance =Long.valueOf(depositEntity.getBalance());
        long targetBalance = Long.valueOf(depositEntity1.getBalance());
        if (amount<sourceBalance){
            synchronized (depositEntity){
                synchronized (depositEntity1){
                    withdraw(depositEntity,amount);
                    deposit(depositEntity1,amount);
                }

            }
        }
    }
    public void depositReport(ReportDTO reportDTO,CustomerEntity customerEntity) throws CanNotGetReport, CantFetchData, DRException, IOException, FileNotFound {


        List<CustomerEntity> customerEntities=iCustomerDAO.getAll();
        new Thread( () -> {
//        BankReports bankReports=null;


            try {
             new DepositsReport(customerEntities,reportDTO);
//                bankReports.bankReport(reportDTO,customerEntities);
            } catch (DRException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            new DepositsReport(customerEntities);

        }  ).start();


    }

    @Override
    public void depositForACustomer(ReportDTO reportDTO,CustomerEntity customerEntity) throws CantFetchData {
       List<CustomerEntity>customerEntities;
        customerEntities=iCustomerDAO.searchByName(customerEntity);
        new Thread( () -> {
            try {
                new DepositForACustomer(reportDTO,customerEntities);
//                bankReports.bankReport(reportDTO,customerEntities);
            } catch (DRException e) {
                e.printStackTrace();
            }  catch (IO io) {
                io.printStackTrace();
            }
//            new DepositsReport(customerEntities);

        }  ).start();
        }



    }


