package com.rayan.Bank.service.interfaces;

import com.rayan.Bank.exception.*;

import java.io.IOException;

public interface IBackupService {

    void importToDB(String path) throws IOException, ClassNotFoundException, CantFetchData, CantAddData, CantCastToOtherClass;
    void exportFromDB(String path) throws CantFetchData, FileNotFound, IO;

}
