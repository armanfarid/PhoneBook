package com.rayan.Bank.UI.implementation;

import com.rayan.Bank.DTO.CustomerDTO;
import com.rayan.Bank.DTO.ReportDTO;
import com.rayan.Bank.UI.interfaces.IReportUI;
import com.rayan.Bank.exception.CanNotGetReport;
import com.rayan.Bank.exception.CantFetchData;
import com.rayan.Bank.exception.FileNotFound;
import com.rayan.Bank.facade.IFacade;
import net.sf.dynamicreports.report.exception.DRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Component("ReportUI")
public class ReportUI implements IReportUI {
    @Autowired
    private IFacade facade;
    Scanner input  =new Scanner(System.in);
    ReportDTO reportDTO = new ReportDTO();



    @Override
    public void depositReport() {

        System.out.println("please enter the format of your report file");
        System.out.println("please enter 1 for pdf or enter 2 to get a exceltype report");
        int choose = input.nextInt();
        if (choose==1){
            reportDTO.setReportType("PDF");
        }else {

            reportDTO.setReportType("EXCEL");
        }
        System.out.println("please enter a path to save report");
        reportDTO.setAddress(input.next());
        try {
            facade.depositReport(reportDTO);
        } catch (CanNotGetReport canNotGetReport) {
            canNotGetReport.printStackTrace();
        }catch (CantFetchData e){
            System.out.println(e.getMessage());
        }catch (DRException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }catch (FileNotFound e){
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void customerReport() {

        System.out.println("please enter the format of your report file ");
        System.out.println("please enter 1 for pdf or enter 2 to get a exceltype report");
        int choose = input.nextInt();
        if (choose==1){
            reportDTO.setReportType("PDF");
        }else {

            reportDTO.setReportType("EXCEL");
        }
        System.out.println("please enter a path to save report");
        reportDTO.setAddress(input.next());
        try {

                facade.customerReport(reportDTO);

        } catch (CantFetchData cantFetchData) {
            System.out.println(cantFetchData.getMessage());
        }catch (DRException e) {
            System.out.println(e.getMessage());
        }catch (FileNotFound e){
            System.out.println(e.getMessage());
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void depositForACustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        System.out.println("please enter the format of your report file  ");
        System.out.println("please enter 1 for pdf or enter 2 to get a exceltype report");
        int choose = input.nextInt();
        if (choose==1){
            reportDTO.setReportType("PDF");
        }else {

            reportDTO.setReportType("EXCEL");
        }
        System.out.println("please enter a path to save report");
        reportDTO.setAddress(input.next());
        System.out.println("please enter a Customer name");
        String name = input.next();
        customerDTO.setName(name);
        List<CustomerDTO>customerDTOS= null;
        try {
            customerDTOS = facade.customerSearchByName(customerDTO);
            if(customerDTOS.size()>0){
                facade.depositForACustomer(reportDTO,customerDTO);
            }
        } catch (CantFetchData cantFetchData) {
            cantFetchData.printStackTrace();
        }




    }
}
