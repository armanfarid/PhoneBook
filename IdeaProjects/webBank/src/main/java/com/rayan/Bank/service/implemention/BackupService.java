package com.rayan.Bank.service.implemention;

import com.rayan.Bank.exception.*;
import com.rayan.Bank.model.daoInterfaces.ICustomerDAO;
import com.rayan.Bank.model.entity.CustomerEntity;
import com.rayan.Bank.service.interfaces.IBackupService;
import com.rayan.Bank.service.interfaces.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;


@Component
public class BackupService implements IBackupService {
    @Autowired
    private ICustomerDAO customerDAO;

    @Autowired
    private ICustomerService customerService;

    CustomerEntity customerEntity = new CustomerEntity();

    @Override
    public void importToDB(String path) throws IOException, ClassNotFoundException, CantFetchData, CantAddData, CantCastToOtherClass {

        List<CustomerEntity>customerEntities=null;

        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        customerEntities = (List<CustomerEntity>) objectInputStream.readObject();
        objectInputStream.close();
        for(int i=0;i<customerEntities.size();++i){
            customerEntity.setName(customerEntities.get(i).getName());
            customerEntity.setFamily(customerEntities.get(i).getFamily());
            customerEntity.setCustomerNumber(customerEntities.get(i).getCustomerNumber());
            customerEntity.seteMail(customerEntities.get(i).geteMail());
            customerEntity.setDeposits(customerEntities.get(i).getDeposits());
            customerService.insert(customerEntity);
        }



    }

    @Override
    public void exportFromDB(String path) throws CantFetchData, FileNotFound, IO {
        List<CustomerEntity>customerEntities=customerDAO.getAll();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(customerEntities);
            oos.close();

        } catch (FileNotFoundException e) {
            throw new FileNotFound("export to file faield try again Later");
        }catch (IOException e){

            throw new IO("faield to save file");

        }










    }
}
