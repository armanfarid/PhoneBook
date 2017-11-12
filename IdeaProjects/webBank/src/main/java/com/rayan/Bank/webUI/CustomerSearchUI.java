package com.rayan.Bank.webUI;

import com.rayan.Bank.DTO.CustomerDTO;
import com.rayan.Bank.exception.CantCastToOtherClass;
import com.rayan.Bank.exception.CantFetchData;
import com.rayan.Bank.facade.Facade;
import com.rayan.Bank.facade.IFacade;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/customer/search")
public class CustomerSearchUI extends HttpServlet {
    private IFacade facade;
    CustomerDTO customerDTO=new CustomerDTO();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        facade = (facade == null) ? (IFacade) ctx.getBean("Facade") : facade;


        String page = "/Menu.html";
        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher(page);
        rd.include(req,resp);
        String page1 = "/searchBox.html";
        ServletContext context1 = getServletContext();
        RequestDispatcher rd1 = context1.getRequestDispatcher(page1);
        rd1.include(req, resp);
        try {
            facade.customerSearch(req.getParameter("customerSearch"));
        } catch (CantCastToOtherClass cantCastToOtherClass) {
            cantCastToOtherClass.printStackTrace();
        } catch (CantFetchData cantFetchData) {
            cantFetchData.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service(req,resp);
        
    }
}

