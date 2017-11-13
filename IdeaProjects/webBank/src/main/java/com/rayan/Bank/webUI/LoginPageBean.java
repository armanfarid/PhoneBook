package com.rayan.Bank.webUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.IOException;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class LoginPageBean {
    private String userName;
    private String password;

    @Autowired
    private LoginBean loginBean;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void loginAction(ActionEvent actionEvent){
        if("a".equals(userName) && "b".equals(password)){
            loginBean.setUserName(userName);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/index.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "password or userName is invalid"));
        }
    }
}
