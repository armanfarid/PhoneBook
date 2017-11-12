package com.rayan.Bank.model.daoImplemention;

import com.rayan.Bank.exception.*;
import com.rayan.Bank.model.daoInterfaces.ICustomerDAO;
import com.rayan.Bank.model.entity.CustomerEntity;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Component("CustomerDAO")
public class CustomerDAO implements ICustomerDAO,Serializable {
    private EntityManager entityManager;

    public CustomerDAO() throws CantConnectToDB {
        try{
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myJPA");
            this.entityManager = entityManagerFactory.createEntityManager();


        }catch (PersistenceException e){
            throw new CantConnectToDB("there is a problem in connecting to DB");
        }
    }

    @Override
    public CustomerEntity search(CustomerEntity customerEntity) throws CantCastToOtherClass, CantFetchData {
        CustomerEntity customerEntity1 = null;


        try{
            String jpql = "select customerEntity1 from CustomerEntity customerEntity1 where customerEntity1.customerNumber= :ID";
            Query q = entityManager.createQuery(jpql);
            q.setParameter("ID", customerEntity.getCustomerNumber());
            List resultList =q.getResultList();
            if(resultList.size()>0){
                customerEntity1 =(CustomerEntity) resultList.get(0);
            }


         }catch (ClassCastException e){

            throw new CantCastToOtherClass("moshkele systemi rokh dade ast");

        }catch (PersistenceException e){
            throw new CantFetchData("fetching data fails");
        }




        return customerEntity1;
    }

    @Override
    public void insert(CustomerEntity customerEntity) throws CantAddData, CantFetchData {
        try {

            entityManager.getTransaction().begin();
            entityManager.persist(customerEntity);
            entityManager.getTransaction().commit();
        }catch (PersistenceException e){
            throw new CantAddData("data adding failed  ");
        }

    }

    @Override
    public void update(CustomerEntity customerEntity) throws  CantUpdateData {


//        CustomerEntity customerEntity1=null;
        try{

            entityManager.getTransaction().begin();
//            String jpql = "select customerEntity1 from CustomerEntity customerEntity where customerEntity1.customerNumber = :customerNumber";
//            Query query=entityManager.createQuery(jpql);
//            query.setParameter("customerNumber", customerEntity.getCustomerNumber());
//            customerEntity1=(CustomerEntity) query.getSingleResult();
//            customerEntity1.setName(customerEntity.getName());
//            customerEntity1.setFamily( customerEntity.getFamily());
//            customerEntity1.seteMail( customerEntity.geteMail());



            entityManager.merge(customerEntity);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            throw new CantUpdateData("update data failed  ");

        }



    }

    @Override
    public void delete(CustomerEntity customerEntity) throws CantDeleteData {

//        CustomerEntity customerEntity1=null;

        try{

            entityManager.getTransaction().begin();
//            String jpql = "select customerEntity1 from CustomerEntity customerEntity where customerEntity1.customerNumber = :customerNumber";
//            Query query=entityManager.createQuery(jpql);
//            query.setParameter("customerNumber", customerEntity.getCustomerNumber());
//            customerEntity1=(CustomerEntity) query.getSingleResult();
//            customerEntity1.setName(customerEntity.getName());
//            customerEntity1.setFamily( customerEntity.getFamily());
//            customerEntity1.seteMail( customerEntity.geteMail());



            entityManager.remove(customerEntity);
            entityManager.getTransaction().commit();
        }catch (PersistenceException e){
            throw new CantDeleteData("delete data failed   ");


        }


    }

    @Override
    public List<CustomerEntity> getAll() throws CantFetchData {

        List<CustomerEntity> customerEntities = null;
        try {
        String jpql = "select customerEntity from CustomerEntity customerEntity";
        Query query = entityManager.createQuery(jpql,CustomerEntity.class);
            customerEntities = (List<CustomerEntity>) query.getResultList();


//            log.LogMessage("Start getting customer list", LogType.INFO);

//            for (CustomerEntity customerEntity1 : customerEntities) {
//                entityManager.detach(customerEntity1);
//            }
//            log.LogMessage("Finish getting customer list", LogType.INFO);

        } catch (Exception e) {
//            log.LogMessage("Not found object, cause: " + e.getMessage(), LogType.WARNIN);
            throw new CantFetchData("fetching data failed  ");
        }

        return customerEntities;



    }
    public List<CustomerEntity> searchByName(CustomerEntity customerEntity) throws CantFetchData {


            List<CustomerEntity> customerEntities=null;
          try {

              String jpql = "select customerEntity from CustomerEntity customerEntity where customerEntity.name= :name";
              Query query = entityManager.createQuery(jpql,CustomerEntity.class);
              query.setParameter("name", customerEntity.getName());
//              List resultList =q.getResultList();
              customerEntities = (List<CustomerEntity>) query.getResultList();
          }catch (Exception e){
              throw new CantFetchData("fetching data failed");
          }








        return customerEntities;




    }
}
