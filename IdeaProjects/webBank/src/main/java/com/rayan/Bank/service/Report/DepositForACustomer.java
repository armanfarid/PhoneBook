package com.rayan.Bank.service.Report;

import com.rayan.Bank.DTO.ReportDTO;
import com.rayan.Bank.exception.IO;
import com.rayan.Bank.model.entity.CustomerEntity;
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
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;

public class DepositForACustomer {
    public DepositForACustomer(ReportDTO reportDTO, List<CustomerEntity> customerEntities) throws DRException, IO {

        createReports(reportDTO,customerEntities);

    }
    public void createReports(ReportDTO reportDTO,List<CustomerEntity> customerEntities) throws DRException, IO {

        try {
            StyleBuilder boldStyle = stl.style().bold();
            StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
            StyleBuilder ColumnTitleStyle = stl.style(boldCenteredStyle).setBorder(stl.pen1Point()).setBackgroundColor(Color.lightGray);

            report()


                    .columns(

                            col.column("CustomerName", "CustomerName", type.stringType()).setFixedColumns(10).setStyle(boldCenteredStyle),
                            col.column("CustomerNumber","CustomerNumber",type.stringType()).setStyle(boldCenteredStyle),

                            col.column("DepositType", "DepositType", type.stringType()).setStyle(boldCenteredStyle),

                            col.column("DepositNumber", "DepositNumber", type.stringType()).setStyle(boldCenteredStyle),

                            col.column("Balance", "Balance", type.stringType()).setStyle(boldCenteredStyle))




                    .setColumnTitleStyle(ColumnTitleStyle)
                    .highlightDetailEvenRows()
                    .title(cmp.text("Deposit for a customer"))
                    .pageFooter(cmp.pageXofY().setStyle(boldCenteredStyle))
                    .setDataSource(createDataSource(customerEntities))


                    .show();

            if(reportDTO.getReportType().equalsIgnoreCase("PDF")){
                report().toPdf(new FileOutputStream(reportDTO.getAddress()));
            }
            else {
                report().toXlsx(new FileOutputStream(reportDTO.getAddress()));
            }

        } catch (DRException e) {

            e.printStackTrace();


        }catch (IOException e){
            throw new IO("failed to save file");
        }


    }




    private JRDataSource createDataSource(List<CustomerEntity> customerEntities) {

        DRDataSource dataSource = new DRDataSource("CustomerName","CustomerNumber", "DepositType", "DepositNumber", "Balance");
        for (int i = 0; i < customerEntities.size(); i++) {
            String name=null,customerNumber=null,depositType=null,depositNumber=null,balance=null;
            name=customerEntities.get(i).getName();
            customerNumber=customerEntities.get(i).getCustomerNumber();
            for (int j=0;j<customerEntities.get(i).getDeposits().size();++j){
                depositType=customerEntities.get(i).getDeposits().get(j).getType();
                depositNumber=customerEntities.get(i).getDeposits().get(j).getDepositNumber();
                balance=customerEntities.get(i).getDeposits().get(j).getBalance();


            }



            dataSource.add(name, customerNumber, depositType, depositNumber,balance);
        }

        return dataSource;

    }
}
