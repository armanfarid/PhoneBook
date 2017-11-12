package com.rayan.Bank.UI.implementation;

import com.rayan.Bank.DTO.DepositDTO;
import com.rayan.Bank.UI.interfaces.IDepositUI;
import com.rayan.Bank.exception.*;
import com.rayan.Bank.facade.IFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Scanner;
@Component("DepositUI")
public class DepositUI implements IDepositUI{
    DepositDTO depositDTO  =new DepositDTO();
    Scanner input = new Scanner(System.in);
    @Autowired
    private IFacade facade;
    ArrayList <DepositDTO> searchArray = new ArrayList<>();
    int i=0;


    public void insert() {

        try {

            System.out.println("please enter CustomerID to Add");
            String customerID = input.next();

            if(facade.customerSearch(customerID)!=null){


                System.out.println("please enter the type of your deposit");
                System.out.println("1)gharzol hasane\n2)seporde sarmayeGozari kutah modat\n3)seporde sarmayegozari boland modat\n4)arzi");

                int userInput=Integer.parseInt(input.next());
                switch (userInput){
                    case 1:
                        depositDTO.setType(DepositType.gharzolhasane.toString());
                        break;
                    case 2:
                        depositDTO.setType(DepositType.sarmayeGozariKutahModat.toString());
                        break;
                    case 3:
                        depositDTO.setType(DepositType.sarmayeGozariBolandModat.toString());
                        break;
                    case 4:
                        depositDTO.setType(DepositType.arzi.toString());
                        break;

                }
                System.out.println("please enter the deposit ID");
                depositDTO.setDepositNumber(input.next());
                depositDTO.setBalance("0");


            }
            else {
                System.out.println("sorry the customer is not exist please enter a valid one");
            }


        }catch (CantCastToOtherClass e){
            System.out.println(e.getMessage());
        }
        catch (CantFetchData e){
            System.out.println(e.getMessage());
        }




    }

    public void update() {
        DepositDTO depositDTO1;
        try {

            System.out.println("please enter the deposit ID ");
            String depositID = input.next();

            depositDTO1=facade.depositSearch(depositID);
            if (depositDTO1!=null) {
                System.out.println("1)change deposit type\n2)change deposit Balance\n3)change deposit ID");
                int userChoose = input.nextInt();
                switch (userChoose) {
                    case 1:
                        System.out.println("please choose your new deposit type");
                        System.out.println("1)gharzol hasane\n2)seporde sarmayeGozari kutah modat\n3)seporde sarmayegozari boland modat\n4)arzi");

                        int typeInput = Integer.parseInt(input.next());
                        switch (typeInput) {
                            case 1:
                                depositDTO1.setType(DepositType.gharzolhasane.toString());
                                break;
                            case 2:
                                depositDTO1.setType(DepositType.sarmayeGozariKutahModat.toString());
                                break;
                            case 3:
                                depositDTO1.setType(DepositType.sarmayeGozariBolandModat.toString());
                                break;
                            case 4:
                                depositDTO1.setType(DepositType.arzi.toString());
                                break;

                        }
                        depositDTO.setDepositNumber(depositDTO1.getDepositNumber());
                        depositDTO.setType(depositDTO1.getType());
                        depositDTO.setBalance(depositDTO1.getBalance());
                        facade.depositUpdate(depositDTO);
                        break;
                    case 2:
                        System.out.println("please enter new balance");
                        String newBalance = input.next();
                        depositDTO.setBalance(newBalance);
                        depositDTO.setDepositNumber(depositDTO1.getDepositNumber());
                        depositDTO.setType(depositDTO1.getType());
                        facade.depositUpdate(depositDTO);

                        break;
                    case 3:
                        System.out.println("please enter new ID");
                        String newID = input.next();
                        depositDTO.setDepositNumber(newID);
                        depositDTO.setType(depositDTO1.getType());
                        depositDTO.setBalance(depositDTO1.getBalance());
                        facade.depositUpdate(depositDTO);
                        break;




                }


            }
        }catch (CantFetchData e){
            System.out.println(e.getMessage());
        }catch (CantUpdateData e){
            System.out.println(e.getMessage());
        }

    }

    public void delete() {
        DepositDTO depositDTO1;
        try {

            System.out.println("please enter the depositID that you wanna delete");
            String deleteID = input.next();
            depositDTO1=facade.depositSearch(deleteID);
            if(depositDTO1!=null){

                depositDTO.setDepositNumber(depositDTO1.getDepositNumber());
                depositDTO.setType(depositDTO1.getType());
                depositDTO.setBalance(depositDTO1.getBalance());
                facade.depositDelete(depositDTO);

            }
            else {
                System.out.println("the deposit that you entered is not exist try other one");
            }
        }catch (CantFetchData e){
            System.out.println(e.getMessage());
        }catch (CantDeleteData e){
            System.out.println(e.getMessage());
        }

    }

    public  void deposit() {
        try {
            DepositDTO depositDTO1;
            System.out.println("please enter the depositNumber ");
            String depositID = input.next();
            depositDTO1 = facade.depositSearch(depositID);
            if (depositDTO1 != null) {
                System.out.println("how much you wanna add to this account?");
                long amount = input.nextLong();

                facade.depositDeposit(depositDTO1,amount);
            }
        }catch (CantFetchData e){
            System.out.println(e.getMessage());
        }catch (CantUpdateData e){
            System.out.println(e.getMessage());
        }


    }

    public  void withDraw() {
        try {
            DepositDTO depositDTO1;
            System.out.println("please enter the depositNumber ");
            String depositID = input.next();
            depositDTO1=facade.depositSearch(depositID);
            if(depositDTO1!=null){
                System.out.println("please enter the amount you wanna withDraw");
                long amount = input.nextLong();
                facade.depositWithDaraw(depositDTO1,amount);
            }
        }catch (CantFetchData e){
            System.out.println(e.getMessage());
        }catch (CantUpdateData e){
            System.out.println(e.getMessage());
        }catch (NotEnoughBalance e){
            System.out.println(e.getMessage());
        }

    }

    public void transfer() {
        try {

            DepositDTO depositDTO1;
            DepositDTO depositDTO2;
            System.out.println("please enter the source depositNumber");
            String depositID1=input.next();
            depositDTO1=facade.depositSearch(depositID1);
            if(depositDTO1!=null){
                System.out.println("please enter the target depositNumber");
                String depositID2=input.next();
                depositDTO2=facade.depositSearch(depositID2);
                if(depositDTO2!=null){
                    System.out.println("please enter the amount");
                    long amount = input.nextLong();
                    facade.depositTransfer(depositDTO1,depositDTO2, amount);
                }
            }
        }catch (CantFetchData e){
            System.out.println(e.getMessage());
        }catch (CantUpdateData e){
            System.out.println(e.getMessage());
        }catch (NotEnoughBalance e){
            System.out.println(e.getMessage());
        }


    }
}
