package com.rayan.Bank.model.daoImplemention;

import com.rayan.Bank.exception.*;
import com.rayan.Bank.model.daoInterfaces.IDepositDAO;
import com.rayan.Bank.model.entity.DepositEntity;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component("DepositDAO")
public class DepositDAO implements IDepositDAO,Serializable{

    private EntityManager entityManager;
    public DepositDAO() throws CantConnectToDB {
        try {

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myJPA");
            this.entityManager = entityManagerFactory.createEntityManager();
        }catch (PersistenceException e){
            throw new CantConnectToDB("moshkel dar ertebat ba DB");
        }


    }


    @Override
    public void Deposit() throws CantDeposit {
        try{

        }catch (PersistenceException e){
            throw new CantDeposit("variz na movafagh bud");
        }

    }

    @Override
    public void withDraw() throws CantWithDraw {

        try {

        }catch (PersistenceException e){
            throw new CantWithDraw("bardashte vajh namovafagh bud");
        }
    }

    @Override
    public void transfer() {

    }

    @Override
    public DepositEntity search(DepositEntity depositEntity) throws CantFetchData {
        try{


            ArrayList<DepositEntity> searchArray = new ArrayList<DepositEntity>();


            DepositEntity depositEntity1 = null;
            String jpql = "select depositEntity1 from DepositEntity depositEntity1 where depositEntity1.depositNumber= :ID";
            Query q = entityManager.createQuery(jpql);
            q.setParameter("ID", depositEntity.getDepositNumber());
            List resultList =q.getResultList();

            if(resultList.size()>0){
                depositEntity1 =(DepositEntity) resultList.get(0);
            }
            return depositEntity1;

        }catch (PersistenceException e){
            throw new CantFetchData("fetching data fails");

        }






    }

    @Override
    public void insert(DepositEntity depositEntity) throws CantAddData {
        try{


            entityManager.getTransaction().begin();
            entityManager.persist(depositEntity);
            entityManager.getTransaction().commit();
        }catch (PersistenceException e){
            throw new CantAddData("data adding failed ");

        }

    }

    @Override
    public void update(DepositEntity depositEntity) throws CantUpdateData {

//        DepositEntity depositEntity1=null;

        try {
            entityManager.getTransaction().begin();
//            String jpql = "select depositEntity1 from CustomerEntity depositEntity where depositEntity1.depositNumber = :depositNumber";
//            Query query=entityManager.createQuery(jpql);
//            query.setParameter("depositNumber", depositEntity.getDepositNumber());
//            depositEntity1=(DepositEntity) query.getSingleResult();
//            depositEntity1.setType(depositEntity.getType());
//            depositEntity1.setDepositNumber( depositEntity.getDepositNumber());
//            depositEntity1.setBalance( depositEntity.getBalance());

            entityManager.merge(depositEntity);
            entityManager.getTransaction().commit();
        }catch (PersistenceException e){
            throw new CantUpdateData("update data failed ");

        }


    }

    @Override
    public void delete(DepositEntity depositEntity) throws CantDeleteData {


//        DepositEntity depositEntity1=null;
        try {
            entityManager.getTransaction().begin();
//            String jpql = "select depositEntity1 from CustomerEntity depositEntity where depositEntity1.depositNumber = :depositNumber";
//            Query query=entityManager.createQuery(jpql);
//            query.setParameter("depositNumber", depositEntity.getDepositNumber());
//            depositEntity1=(DepositEntity) query.getSingleResult();
//            depositEntity1.setType(depositEntity.getType());
//            depositEntity1.setDepositNumber( depositEntity.getDepositNumber());
//            depositEntity1.setBalance( depositEntity.getBalance());

            entityManager.remove(depositEntity);
            entityManager.getTransaction().commit();
        }catch (PersistenceException e){
            throw new CantDeleteData("delete data failed  ");

        }



    }

    @Override
    public List<DepositEntity> getAll() throws CantFetchData {
        List<DepositEntity> depositEntities = null;
        String jpql = "select Deposit from DepositEntity Deposit";
        Query query = entityManager.createQuery(jpql);

        try {
//            log.LogMessage("Start getting customer list", LogType.INFO);

            depositEntities = (List<DepositEntity>) query.getResultList();
            for (DepositEntity depositEntity1 : depositEntities) {
                entityManager.detach(depositEntity1);
            }
//            log.LogMessage("Finish getting customer list", LogType.INFO);

        } catch (Exception e) {
//            log.LogMessage("Not found object, cause: " + e.getMessage(), LogType.WARNIN);
            throw new CantFetchData("fetching data failed  ");
        }

        return depositEntities;

    }
}
