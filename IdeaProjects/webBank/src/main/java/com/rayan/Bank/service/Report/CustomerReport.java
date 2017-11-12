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


public class CustomerReport  {
    public CustomerReport(List<CustomerEntity>customerEntities,ReportDTO reportDTO) throws DRException, IO {
        createReports(customerEntities,reportDTO);
    }


    public void createReports(List<CustomerEntity> customerEntities,ReportDTO reportDTO) throws DRException, IO {

        try {
            StyleBuilder boldStyle = stl.style().bold();
            StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
            StyleBuilder ColumnTitleStyle = stl.style(boldCenteredStyle).setBorder(stl.pen1Point()).setBackgroundColor(Color.lightGray);

            report()


                    .columns(

                            col.column("Name", "Name", type.stringType()).setFixedColumns(10).setStyle(boldCenteredStyle),

                            col.column("Family", "Family", type.stringType()).setStyle(boldCenteredStyle),

                            col.column("CustomerID", "CustomerID", type.stringType()).setStyle(boldCenteredStyle),

                            col.column("Email", "Email", type.stringType()).setStyle(boldCenteredStyle))




                    .setColumnTitleStyle(ColumnTitleStyle)
                    .highlightDetailEvenRows()
                    .title(cmp.text("CustomerReport"))
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

        DRDataSource dataSource = new DRDataSource("Name", "Family", "CustomerID", "Email");
        for (int i = 0; i < customerEntities.size(); i++) {
            String name=null,family=null,ID=null,Email=null;
            name=customerEntities.get(i).getName();
            family=customerEntities.get(i).getFamily();
            ID = customerEntities.get(i).getCustomerNumber();
            Email = customerEntities.get(i).geteMail();


            dataSource.add(name, family, ID, Email);
        }

        return dataSource;

    }
}
