package com.rayan.Bank.UI.implementation;

import com.rayan.Bank.DTO.CustomerDTO;
import com.rayan.Bank.DTO.DepositDTO;
import com.rayan.Bank.UI.interfaces.ICustomerUI;
import com.rayan.Bank.exception.*;
import com.rayan.Bank.facade.IFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Scanner;

@Component("CustomerUI")
public class CustomerUI implements ICustomerUI{
    Scanner input =new Scanner(System.in);

    @Autowired
    private IFacade facade;

    ArrayList<DepositDTO> depositDTOArrayList = new ArrayList<>();

    CustomerDTO customerDTO = new CustomerDTO();
    @Override
    public void insert() {
        boolean addCustomer = true;
        boolean addDeposit = true;
        int i = 0;



            try {
                while (addCustomer) {
                System.out.println("do you want add contact");
                System.out.println("please enter y for yes and n for no");
                String userAddCustomer = input.next();
                if (userAddCustomer.equalsIgnoreCase("y")) {
                    System.out.println("please enter your name");
                    customerDTO.setName(input.next());
                    System.out.println("please enter your family");
                    customerDTO.setFamily(input.next());
                    System.out.println("please enter a IDNumber");
                    String id = input.next();
                    if (facade.customerSearch(id) == null) {
                        customerDTO.setCustomerNumber(id);
                        System.out.println("please enter your email");
                        customerDTO.seteMail(input.next());
                        while (addDeposit) {
                            System.out.println("do you want to add deposit for this customer");
                            System.out.println("please enter y for yes and n for no");
                            String userAddDeposit = input.next();
                            if (userAddDeposit.equalsIgnoreCase("y")) {
                                DepositDTO depositDTO = new DepositDTO();
                                System.out.println("please enter the type of your deposit");
                                System.out.println("1)gharzol hasane\n2)seporde sarmayeGozari kutah modat\n3)seporde sarmayegozari boland modat\n4)arzi");

                                int userInput = Integer.parseInt(input.next());
                                switch (userInput) {
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

                                depositDTOArrayList.add(depositDTO);
                                ++i;


                            } else if (userAddDeposit.equalsIgnoreCase("n")) {
                                addDeposit = false;

                            }

                        }
                        customerDTO.setDeposits(depositDTOArrayList);


                    } else {
                        System.out.println("id is invalid");
                    }

                } else {
                    addCustomer = false;
                }


            }
            customerDTO.setDeposits(depositDTOArrayList);

            facade.customerInsert(customerDTO);


        }catch (CantFetchData e){
                System.out.println(e.getMessage());
            }catch (CantCastToOtherClass e){
                System.out.println(e.getMessage());
            }catch (CantAddData e){
                System.out.println( e.getMessage());
            }
    }

    @Override
    public void update() {
        try {

            CustomerDTO customerDTO1;
            System.out.println("please enter the Customer ID that you wanna change ");
            String id = input.next();
            customerDTO1=facade.customerSearch(id);
            if(customerDTO1.getCustomerNumber()!=null){
                System.out.println("1)update name \n2)update family\n3)update email");
                int userChoose = input.nextInt();
                switch (userChoose){
                    case 1:
                        System.out.println("please enter new name");
                        customerDTO.setName(input.next());
                        customerDTO.setFamily(customerDTO1.getFamily());
                        customerDTO.setCustomerNumber(customerDTO1.getCustomerNumber());
                        customerDTO.seteMail(customerDTO1.geteMail());
                        customerDTO.setCustomerID(customerDTO1.getCustomerID());
                        facade.customerUpdate(customerDTO);
                        break;
                    case 2:
                        System.out.println("please enter your new family ");
                        customerDTO.setCustomerNumber(input.next());
                        customerDTO.setName(customerDTO1.getName());
                        customerDTO.setFamily(customerDTO1.getFamily());
                        customerDTO.seteMail(customerDTO1.geteMail());
                        facade.customerUpdate(customerDTO);
                        break;
                    case 3:
                        System.out.println("please your new email");
                        customerDTO.seteMail(input.next());
                        customerDTO.setName(customerDTO1.getName());
                        customerDTO.setFamily(customerDTO1.getFamily());
                        customerDTO.setCustomerNumber(customerDTO1.getCustomerNumber());

                        facade.customerUpdate(customerDTO);
                        break;


                }
            }else {
                System.out.println("this CustomerNumber does not exist enter a valid one");
            }
        }catch (CantFetchData e){
            System.out.println(e.getMessage());
        }catch (CantUpdateData e){
            System.out.println(e.getMessage());
        }catch (CantCastToOtherClass e){
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void delete() {
      try {

          CustomerDTO customerDTO1;
          System.out.println("please enter the customer ID of that customer you wanna delete");
          String id = input.next();
          customerDTO1=facade.customerSearch(id);
          if(customerDTO1!=null){
              customerDTO.setName(customerDTO1.getName());
              customerDTO.setFamily(customerDTO1.getFamily());
              customerDTO.setCustomerNumber(customerDTO1.getCustomerNumber());
              customerDTO.seteMail(customerDTO1.geteMail());




          }

          facade.customerDelete(customerDTO);
      }catch (CantFetchData e){
          System.out.println(e.getMessage());
      }catch (CantDeleteData e){
          System.out.println(e.getMessage());
      }catch (CantCastToOtherClass e){
          System.out.println(e.getMessage());
      }
    }
}
