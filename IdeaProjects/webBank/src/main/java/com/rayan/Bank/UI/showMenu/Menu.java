package com.rayan.Bank.UI.showMenu;

import com.rayan.Bank.UI.interfaces.IBackupUI;
import com.rayan.Bank.UI.interfaces.IReportUI;
import com.rayan.Bank.main.Main;
import com.rayan.Bank.UI.interfaces.ICustomerUI;
import com.rayan.Bank.UI.interfaces.IDepositUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component("Menu")
public class Menu {
    @Autowired
    @Qualifier("ReportUI")
    private IReportUI reportUI;

    @Autowired
    private IBackupUI iBackupUI;
    @Autowired
    @Qualifier("Main")
    private Main main;

    @Autowired
    @Qualifier("CustomerUI")
    private ICustomerUI customerUI;
    @Autowired
    @Qualifier("DepositUI")
    private IDepositUI depositUI;
    public void showMenu() {
        boolean showMenu = true;
        while (showMenu) {
            Scanner input = new Scanner(System.in);
            System.out.println("Welcome to banking software");
            System.out.println("--------------------------------------");
            System.out.println("//Menu//");
            System.out.println("1)Customer\n2)Deposit\n3)Report\n4)Backup\n5)Exit");
            int userChoose = input.nextInt();
            switch (userChoose) {
                case 1:
                    boolean showCustomer=true;
                    while (showCustomer) {
                        System.out.println("1)Insert\n2)Update\n3)Delete\n4)Back");
                        int customerChoose = input.nextInt();
                        switch (customerChoose) {
                            case 1:
                                customerUI.insert();

                                break;
                            case 2:
                                customerUI.update();
                                break;
                            case 3:
                                customerUI.delete();
                                break;
                            case 4:
                                main.toMenu();

                        }
                    }
                    break;
                case 2:

                    System.out.println("1)Inset\n2)Update\n3)Delete\n4)Deposit\n5)WithDraw\n6)Transfer\n7)Back");
                    int depositChoose = input.nextInt();
                    switch (depositChoose) {
                        case 1:
                            depositUI.insert();
                            break;
                        case 2:
                            depositUI.update();
                            break;
                        case 3:
                            depositUI.delete();
                            break;
                        case 4:
                            depositUI.deposit();
                            break;
                        case 5:
                            depositUI.withDraw();
                            break;
                        case 6:
                            depositUI.transfer();
                            break;
                        case 7:
                            main.toMenu();

                    }
                    break;
                case 3:
                    System.out.println("please choose your report Type");
                    System.out.println(("1)DepositReport\n2)CustomerReport\n3)DepositForACustomer\n4)back"));
                    int choose =input.nextInt();
                    switch (choose){
                        case 1:
                            reportUI.depositReport();

                            break;
                        case 2:
                            reportUI.customerReport();
                            break;
                        case 3:
                            reportUI.depositForACustomer();
                            break;
                        case 4:
                            main.toMenu();

                    }


                    break;
                case 4:

                    System.out.println("please choose what  do you want to do");
                    System.out.println("1)import\n2)export\n3)back ");
                    int backupChoose=input.nextInt();
                    switch (backupChoose){

                        case 1:
                            iBackupUI.importToDB();
                            break;
                        case 2:
                            iBackupUI.exportFromDB();
                            break;
                        case 3:
                            main.toMenu();



                    }

                    break;
                case 5:System.exit(0);

            }

        }
    }
}
