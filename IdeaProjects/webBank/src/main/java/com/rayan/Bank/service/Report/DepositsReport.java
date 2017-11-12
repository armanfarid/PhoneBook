package com.rayan.Bank.service.Report;


import com.rayan.Bank.DTO.ReportDTO;
import com.rayan.Bank.model.entity.CustomerEntity;
import com.rayan.Bank.model.entity.DepositEntity;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

public class DepositsReport  {
    public DepositsReport(List<CustomerEntity>customerEntities,ReportDTO reportDTO) throws DRException, IOException {
        createReports(customerEntities,reportDTO);
    }


    public void createReports(List<CustomerEntity>customerEntities,ReportDTO reportDTO) throws DRException, IOException {
        try {
            StyleBuilder boldStyle = stl.style().bold();
            StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
            StyleBuilder ColumnTitleStyle = stl.style(boldCenteredStyle).setBorder(stl.pen1Point()).setBackgroundColor(Color.lightGray);

            report()


                    .columns(

                            col.column("Customer Name", "Customer Name", type.stringType()).setFixedColumns(10).setStyle(boldCenteredStyle),

                            col.column("Deposit Type", "Deposit Type", type.stringType()).setStyle(boldCenteredStyle),

                            col.column("Deposit Number", "Deposit Number", type.stringType()).setStyle(boldCenteredStyle),

                            col.column("Balance", "Balance", type.stringType()).setStyle(boldCenteredStyle))




                    .setColumnTitleStyle(ColumnTitleStyle)
                    .highlightDetailEvenRows()
                    .title(cmp.text("DepositReport"))
                    .pageFooter(cmp.pageXofY().setStyle(boldCenteredStyle))
                    .setDataSource(createDataSource(customerEntities))

                    .show()
                    .toPdf(new FileOutputStream(reportDTO.getAddress()))
                    .toXlsx(new FileOutputStream(reportDTO.getAddress()));


            if(reportDTO.getReportType().equalsIgnoreCase("PDF")){
                 report().toPdf(new FileOutputStream(reportDTO.getAddress()));

            }else {
                report().toXlsx(new FileOutputStream(reportDTO.getAddress()));
            }






        } catch (DRException e) {

            e.printStackTrace();


        }

    }
    private JRDataSource createDataSource(List<CustomerEntity>customerEntities) {

        DRDataSource dataSource = new DRDataSource("Customer Name", "Deposit Type", "Deposit Number", "Balance");


        for (int i = 0; i < customerEntities.size(); i++) {
            String depositType = null, depositNumber = null, depositBlance = null,  customerName=null;

            customerName=customerEntities.get(i).getName();
            List<DepositEntity> depositEntities = customerEntities.get(i).getDeposits();

            for (int j = 0; j < depositEntities.size(); j++) {

                depositType = depositEntities.get(j).getType();
                depositNumber = depositEntities.get(j).getDepositNumber();
                depositBlance = depositEntities.get(j).getBalance();

                dataSource.add(customerName, depositType, depositNumber, depositBlance);
            }

        }

        return dataSource;
    }






    //    Collections.sort(cd, DepositEntity);


//    private DRDataSource dataSource = new DRDataSource("CustomerName", "DepositNumber", "DepositType", "Balance");

//    protected List<DepositEntity> depositEntities = new ArrayList<>();


//    private void createReport(List<DepositEntity> depositEntities) {


//    private JRDataSource createDataSource(List<ContactEntity> c) {
//        DRDataSource dataSource = new DRDataSource("name", "family","mobile","homeNumber","email");
//        for (int i = 0; i <c.size() ; i++) {
//            String home=null,email=null,mob=null;
//            List<NumberEntity> nums=c.get(i).getNums();
//            for (int j = 0; j <nums.size() ; j++) {
//
//
//                if(nums.get(j).getTp().toString().equals("HOME")){
//                    home=nums.get(j).getNumber();
//                }if(nums.get(j).getTp().toString().equals("EMAIL")){
//                    email = nums.get(j).getNumber();
//                }if(nums.get(j).getTp().toString().equals("MOBILE")){
//                    mob= nums.get(j).getNumber();
//                }
//
//
//            }
//            dataSource.add(c.get(i).getName(),c.get(i).getFamily(),mob,home,email);
//        }
//
//        return dataSource;
//
//    }


//    public DepositsReport(List<CustomerEntity> cutomerEntities,ReportDTO reportDTO) {
//        try {
//            createReports(cutomerEntities,reportDTO);
//        } catch (DRException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void createReports(List<CustomerEntity> customerEntities, ReportDTO reportDTO) throws DRException, IOException {
//
//
//
//
//        StyleBuilder boldStyle = stl.style().bold();
//
//        StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
//
//        StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle)
//
//                .setBorder(stl.pen1Point())
//
//                .setBackgroundColor(Color.lightGray);
//
//        report()
//
//
//                .columns(
//
//
//                        col.column("CustomerName", "CustomerName", type.stringType()),
//                        col.column("DepositType", "DepositType", type.stringType()),
//                        col.column("DepositNumber", "DepositNumber", type.stringType()),
//                        col.column("Balance", "Balance", type.stringType())
//
//
//
//
//                ) .setColumnTitleStyle(columnTitleStyle)
//                .highlightDetailEvenRows()
//                .title(cmp.text("Deposits").setStyle(boldCenteredStyle))
//                .pageFooter(cmp.pageXofY().setStyle(boldCenteredStyle))
//                 .setDataSource(createDataSource(customerEntities))
//                .show();
//
//
//
//
//    }
//
//
//
//    public JRDataSource createDataSource(List<CustomerEntity> customerEntities) {
//        DRDataSource dataSource = new DRDataSource("CustomerName", "DepositType", "DepositNumber", "Balance");
//
//
//    }
}
