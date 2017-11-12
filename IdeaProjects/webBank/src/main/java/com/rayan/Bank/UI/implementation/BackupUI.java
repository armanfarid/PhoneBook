package com.rayan.Bank.UI.implementation;

import com.rayan.Bank.UI.interfaces.IBackupUI;
import com.rayan.Bank.exception.*;
import com.rayan.Bank.facade.IFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class BackupUI implements IBackupUI {
    @Autowired
    @Qualifier("Facade")
    private IFacade facade;
    Scanner input = new Scanner(System.in);
    @Override
    public void importToDB() {
        System.out.println("please enter the path of file");
        String path=input.next();
        try {
            facade.importToDB(path);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (CantCastToOtherClass cantCastToOtherClass) {
            System.out.println(cantCastToOtherClass.getMessage());
        } catch (CantFetchData cantFetchData) {
            System.out.println(cantFetchData.getMessage());
        } catch (CantAddData cantAddData) {
            System.out.println(cantAddData.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void exportFromDB() {

        System.out.println("please enter a path to save file");
        String path = input.next();
        try {
            facade.exportFromDB(path);
        } catch (CantFetchData cantFetchData) {
            System.out.println(cantFetchData.getMessage());
        } catch (IO io) {
            System.out.println(io.getMessage());
        } catch (FileNotFound fileNotFound) {
            System.out.println(fileNotFound.getMessage());
        }


    }
}
