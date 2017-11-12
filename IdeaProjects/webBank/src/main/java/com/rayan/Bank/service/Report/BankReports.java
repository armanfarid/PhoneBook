//package com.rayan.Bank.service.Report;
//
//import ReportDTO;
//import CanNotGetReport;
//import FileNotFound;
//import CustomerEntity;
//import DepositEntity;
//import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
//import net.sf.dynamicreports.report.builder.DynamicReports;
//import net.sf.dynamicreports.report.builder.component.Components;
//import net.sf.dynamicreports.report.builder.style.StyleBuilder;
//import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
//import net.sf.dynamicreports.report.exception.DRException;
//import net.sf.jasperreports.engine.JRDataSource;
//
//import java.awt.*;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.List;
//
//import static net.sf.dynamicreports.report.builder.DynamicReports.*;
//
//public abstract class BankReports implements Serializable {
//
//    public void bankReport(ReportDTO reportDTO,List<CustomerEntity>customerEntities) throws DRException, FileNotFound {
//
//        try {
//           JasperReportBuilder report= createReports(customerEntities,reportDTO);
//            makeReport(reportDTO,customerEntities);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }catch (IOException e){
//            e.getMessage();
//        }
////        createDataSource(customerEntities);
//    }
////    ReportDTO reportDTO =new ReportDTO();
//
//    public void makeReport (ReportDTO reportDTO,List<CustomerEntity>customerEntites) throws DRException, FileNotFound {
//
//       if(reportDTO.getReportType().equalsIgnoreCase("PDF")){
//
//       }
//
//
//    }
//    public abstract void createReports(List<CustomerEntity>customerEntities,ReportDTO reportDTO) throws DRException, IOException;
////    public abstract JRDataSource createDataSource(List<CustomerEntity>  customerEntities);
//
//
//
//
//
//
//
//}
