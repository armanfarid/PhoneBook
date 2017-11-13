package com.rayan.Bank.webUI;

import com.rayan.Bank.DTO.CustomerDTO;
import com.rayan.Bank.exception.*;
import com.rayan.Bank.facade.IFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.event.ActionEvent;
import java.io.Serializable;

@Component
@Scope("view")
public class CustomerBean  implements Serializable {

    private CustomerDTO customerDTO = new CustomerDTO();
    private String lable;
    @Autowired
    private IFacade facade;

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public void insert(ActionEvent actionEvent) {
        lable = customerDTO.getName() + ";" + customerDTO.getFamily();
        try {
            facade.customerInsert(customerDTO);
        } catch (CantFetchData cantFetchData) {
            cantFetchData.printStackTrace();
        } catch (CantAddData cantAddData) {
            cantAddData.printStackTrace();
        } catch (CantCastToOtherClass cantCastToOtherClass) {
            cantCastToOtherClass.printStackTrace();
        }


    }

    public void update(ActionEvent actionEvent) {
        CustomerDTO customerDTO = null;
        try {
            customerDTO = facade.customerSearch(this.customerDTO.getCustomerNumber());
        } catch (CantCastToOtherClass cantCastToOtherClass) {
            cantCastToOtherClass.printStackTrace();
        } catch (CantFetchData cantFetchData) {
            cantFetchData.printStackTrace();
        }

        if (customerDTO != null) {

            try {
                facade.customerUpdate(customerDTO);
            } catch (CantCastToOtherClass cantCastToOtherClass) {
                cantCastToOtherClass.printStackTrace();
            } catch (CantFetchData cantFetchData) {
                cantFetchData.printStackTrace();
            } catch (CantUpdateData cantUpdateData) {
                cantUpdateData.printStackTrace();
            }

        }


    }
    public void delete(ActionEvent actionEvent){
        CustomerDTO customerDTO=null;
        try {
            facade.customerSearch(this.customerDTO.getCustomerNumber());
        } catch (CantCastToOtherClass cantCastToOtherClass) {
            cantCastToOtherClass.printStackTrace();
        } catch (CantFetchData cantFetchData) {
            cantFetchData.printStackTrace();
        }
        if(customerDTO!=null){

            try {
                facade.customerDelete(customerDTO);
            } catch (CantCastToOtherClass cantCastToOtherClass) {
                cantCastToOtherClass.printStackTrace();
            } catch (CantFetchData cantFetchData) {
                cantFetchData.printStackTrace();
            } catch (CantDeleteData cantDeleteData) {
                cantDeleteData.printStackTrace();
            }

        }
    }
}