package com.rayan.Bank.facade;

import com.rayan.Bank.DTO.CustomerDTO;
import com.rayan.Bank.DTO.DepositDTO;
import com.rayan.Bank.DTO.ReportDTO;
import com.rayan.Bank.exception.*;
import com.rayan.Bank.model.entity.CustomerEntity;
import com.rayan.Bank.model.entity.DepositEntity;
import com.rayan.Bank.service.interfaces.IBackupService;
import com.rayan.Bank.service.interfaces.ICustomerService;
import com.rayan.Bank.service.interfaces.IDepositService;
import net.sf.dynamicreports.report.exception.DRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component("Facade")
public class Facade implements IFacade{

    @Autowired
    @Qualifier("CustomerService")
    private ICustomerService customerService;
    @Autowired
    @Qualifier("DepositService")
    private IDepositService depositService;
    @Autowired
    private IBackupService backupService;

    CustomerEntity customerEntity = new CustomerEntity();
    DepositEntity depositEntity  =new DepositEntity();
    DepositEntity depositEntity1=new DepositEntity();
    ArrayList<DepositDTO>depositDTOArrayList=new ArrayList<>();
    ArrayList<DepositEntity>depositEntityArrayList=new ArrayList<>();



    @Override
    public void customerInsert(CustomerDTO customerDTO) throws CantFetchData, CantAddData, CantCastToOtherClass {


        customerEntity.setName(customerDTO.getName());
        customerEntity.setFamily(customerDTO.getFamily());
        customerEntity.setCustomerNumber(customerDTO.getCustomerNumber());
        customerEntity.seteMail(customerDTO.geteMail());
        customerEntity.setDeposits(new ArrayList<>());
        if(customerDTO.getDeposits()!=null) {
            for (int i = 0; i < customerDTO.getDeposits().size(); ++i) {
                depositEntity.setDepositNumber(customerDTO.getDeposits().get(i).getDepositNumber());
                depositEntity.setType(customerDTO.getDeposits().get(i).getType());
                depositEntity.setBalance(customerDTO.getDeposits().get(i).getBalance());
                depositEntityArrayList.add(depositEntity);

            }
        }
        customerEntity.setDeposits(depositEntityArrayList);
        customerService.insert(customerEntity);




    }

    @Override
    public void customerUpdate(CustomerDTO customerDTO) throws CantCastToOtherClass, CantFetchData, CantUpdateData {
        customerEntity.setName(customerDTO.getName());
        customerEntity.setFamily(customerDTO.getFamily());
        customerEntity.setCustomerNumber(customerDTO.getCustomerNumber());
        customerEntity.seteMail(customerDTO.geteMail());
        customerEntity.setCustomerID(customerDTO.getCustomerID());
        customerService.Update(customerEntity);

    }

    @Override
    public CustomerDTO customerSearch(String ID) throws CantCastToOtherClass, CantFetchData {

        CustomerDTO customerDTO1=null;
        CustomerEntity customerEntity1;
        customerEntity.setCustomerNumber(ID);
        customerEntity1=customerService.search(customerEntity);
        if(customerEntity1!=null) {
            customerDTO1=new CustomerDTO();
            customerDTO1.setCustomerID(customerEntity1.getCustomerID());
            customerDTO1.setName(customerEntity1.getName());
            customerDTO1.setFamily(customerEntity1.getFamily());
            customerDTO1.seteMail(customerEntity1.geteMail());
            customerDTO1.setCustomerNumber(customerEntity1.getCustomerNumber());
        }
        return customerDTO1 ;
    }


    @Override
    public void customerDelete(CustomerDTO customerDTO) throws CantCastToOtherClass, CantFetchData, CantDeleteData {
        customerEntity.setName(customerDTO.getName());
        customerEntity.setFamily(customerDTO.getFamily());
        customerEntity.setCustomerNumber(customerDTO.getCustomerNumber());
        customerEntity.seteMail(customerDTO.geteMail());
        customerService.delete(customerEntity);


    }

    @Override
    public void depositInsert(DepositDTO depositDTO) throws CantFetchData, CantAddData {
        depositEntity.setDepositNumber(depositDTO.getDepositNumber());
        depositEntity.setType(depositDTO.getType());
        depositEntity.setBalance(depositDTO.getBalance());
        depositService.insert(depositEntity);




    }

    @Override
    public DepositDTO depositSearch(String ID) throws CantFetchData {

        DepositDTO depositDTO =new DepositDTO();
        DepositEntity depositEntity1;


        depositEntity.setDepositNumber(ID);
        depositEntity1=depositService.search(depositEntity);
        if(depositEntity1!=null){
            depositDTO.setDepositNumber(depositEntity1.getDepositNumber());
            depositDTO.setType(depositEntity1.getType());
            depositDTO.setBalance(depositEntity1.getBalance());



        }
        return depositDTO;
    }

    @Override
    public void depositUpdate(DepositDTO depositDTO) throws CantUpdateData, CantFetchData {
        depositEntity.setDepositNumber(depositDTO.getDepositNumber());
        depositEntity.setType(depositDTO.getType());
        depositEntity.setBalance(depositDTO.getBalance());
        depositService.update(depositEntity);


    }

    @Override
    public void depositDelete(DepositDTO depositDTO) throws CantDeleteData, CantFetchData {
        depositEntity.setDepositNumber(depositDTO.getDepositNumber());
        depositEntity.setType(depositDTO.getType());
        depositEntity.setBalance(depositDTO.getBalance());
        depositService.delete(depositEntity);

    }

    @Override
    public void depositDeposit(DepositDTO depositDTO,long amount) throws CantUpdateData {
        depositEntity.setDepositID(depositDTO.getDepositID());
        depositEntity.setType(depositDTO.getType());
        depositEntity.setDepositNumber(depositDTO.getType());
        depositEntity.setBalance(depositDTO.getBalance());
        depositService.deposit(depositEntity,amount);










    }

    @Override

    public void depositWithDaraw(DepositDTO depositDTO,long amount) throws CantUpdateData, CantFetchData, NotEnoughBalance {
        depositEntity.setDepositID(depositDTO.getDepositID());
        depositEntity.setType(depositDTO.getType());
        depositEntity.setDepositNumber(depositDTO.getDepositNumber());
        depositEntity.setBalance(depositDTO.getBalance());
        depositService.withdraw(depositEntity, amount);

    }

    @Override
    public void depositTransfer(DepositDTO depositDTO1, DepositDTO depositDTO2, long amount) throws CantUpdateData, CantFetchData, NotEnoughBalance {

        depositEntity.setDepositID(depositDTO1.getDepositID());
        depositEntity.setType(depositDTO1.getType());
        depositEntity.setDepositNumber(depositDTO1.getDepositNumber());
        depositEntity.setBalance(depositDTO1.getBalance());
        depositEntity1.setDepositID(depositDTO2.getDepositID());
        depositEntity1.setType(depositDTO2.getType());
        depositEntity1.setDepositNumber(depositDTO2.getDepositNumber());
        depositEntity1.setBalance(depositDTO2.getBalance());
        depositService.transfer(depositEntity,depositEntity1,amount);


    }

    @Override
    public void depositReport(ReportDTO reportDTO) throws CanNotGetReport, CantFetchData, DRException, IOException, FileNotFound {

        depositService.depositReport(reportDTO,customerEntity);


    }

    @Override
    public void customerReport(ReportDTO reportDTO) throws CantFetchData, DRException, FileNotFound, FileNotFoundException {
        customerService.customerReport(customerEntity,reportDTO);
    }

    @Override
    public void depositForACustomer(ReportDTO reportDTO,CustomerDTO customerDTO) throws CantFetchData {

        depositService.depositForACustomer(reportDTO,customerEntity);
        //
//        customerDTOS=customerSearchByName(customerDTO);
//        for()


    }

    @Override
    public List<CustomerDTO> customerSearchByName(CustomerDTO customerDTO) throws CantFetchData {
        List<CustomerEntity> customerEntities;
        List <CustomerDTO> customerDTOS=null;
        customerEntity.setName(customerDTO.getName());
        customerEntities=customerService.customerSearchByName(customerEntity);
        for(int i=0;i<customerEntities.size();++i) {
            customerDTOS.get(i).setName(customerEntities.get(i).getName());
            customerDTOS.get(i).setFamily(customerEntities.get(i).getFamily());
            customerDTOS.get(i).setCustomerNumber(customerEntities.get(i).getCustomerNumber());
            customerDTOS.get(i).setCustomerID(customerEntities.get(i).getCustomerID());
            customerDTOS.get(i).seteMail(customerEntities.get(i).geteMail());
        }

        return customerDTOS;
    }

    @Override
    public void importToDB(String path) throws ClassNotFoundException, CantCastToOtherClass, CantFetchData, CantAddData, IOException {
        backupService.importToDB(path);



    }

    @Override
    public void exportFromDB(String path) throws CantFetchData, IO, FileNotFound {
        backupService.exportFromDB(path);


    }

    @Override
    public List<DepositDTO> depositSearchByCustomerID(DepositDTO depositDTO,CustomerDTO customerDTO) {
        customerEntity.setCustomerID(customerDTO.getCustomerID());
        depositEntity.setCustomerEntity(customerEntity);

        return null;
    }
}
