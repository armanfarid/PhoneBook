package com.rayan.Bank.main;

import com.rayan.Bank.UI.showMenu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("Main")
public class Main {
    @Autowired
    private Menu menu;
    public void toMenu(){
        menu.showMenu();







    }



}
