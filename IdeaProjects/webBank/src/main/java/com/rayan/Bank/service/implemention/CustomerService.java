package com.rayan.Bank.service.implemention;
import com.rayan.Bank.DTO.ReportDTO;
import com.rayan.Bank.exception.*;
import com.rayan.Bank.model.daoInterfaces.ICustomerDAO;
import com.rayan.Bank.model.entity.CustomerEntity;

import com.rayan.Bank.service.Report.CustomerReport;
import com.rayan.Bank.service.interfaces.ICustomerService;
import net.sf.dynamicreports.report.exception.DRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component("CustomerService")
public class CustomerService implements ICustomerService{


    @Autowired
    @Qualifier("CustomerDAO")
    private ICustomerDAO iCustomerDAO;




    String emailPattern = "^[(\\w)]+@[(\\w)]+\\.[(\\w)]+$";
    @Override
    public void insert(CustomerEntity customerEntity) throws CantFetchData, CantAddData, CantCastToOtherClass {
        boolean validated = Pattern.matches(emailPattern, customerEntity.geteMail());
        if(customerEntity.getCustomerNumber().length() == 8 && validated && search(customerEntity)==null){
            iCustomerDAO.insert(customerEntity);

        }



    }

    @Override
    public void Update(CustomerEntity customerEntity) throws CantUpdateData, CantCastToOtherClass, CantFetchData {
        boolean validated = Pattern.matches(emailPattern, customerEntity.geteMail());
        if (customerEntity.getCustomerNumber().length() == 8 && validated && search(customerEntity)==null) {
            iCustomerDAO.update(customerEntity);


        }
    }

    @Override
    public void delete(CustomerEntity customerEntity) throws CantDeleteData, CantCastToOtherClass, CantFetchData {
        boolean validated = Pattern.matches(emailPattern, customerEntity.geteMail());
        if (customerEntity.getCustomerNumber().length() == 8 && validated && search(customerEntity)==null) {
            iCustomerDAO.delete(customerEntity);

        }
    }

    @Override
    public CustomerEntity search(CustomerEntity customerEntity) throws CantCastToOtherClass, CantFetchData {
        CustomerEntity customerEntity1=null;
//        ArrayList<CustomerEntity>searchResult=new ArrayList<>();
        if(customerEntity.getCustomerNumber().length()==8){
            customerEntity1=iCustomerDAO.search(customerEntity);
        }
        return customerEntity1;
    }

    @Override
    public void customerReport(CustomerEntity customerEntity, ReportDTO reportDTO) throws CantFetchData, DRException,  FileNotFound {


        List<CustomerEntity>customerEntities=iCustomerDAO.getAll();
        new Thread( () -> {
//            BankReports bankReports=null;


            try {
              new CustomerReport(customerEntities,reportDTO);
//                bankReports.bankReport(reportDTO,customerEntities);
            } catch (DRException e) {
                e.getMessage();
            } catch (IO io) {
                io.getMessage();
            }


//            new DepositsReport(customerEntities);

        }  ).start();

    }

    @Override
    public List<CustomerEntity> customerSearchByName(CustomerEntity customerEntity) throws CantFetchData {
        List<CustomerEntity> customerEntities;

           customerEntities= iCustomerDAO.searchByName(customerEntity);

        return customerEntities;

    }
}
