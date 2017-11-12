//package com.rayan.Bank.webUI;
//
//import com.rayan.Bank.DTO.CustomerDTO;
//import com.rayan.Bank.facade.Facade;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class CustomerUpdateUI extends HttpServlet {
//    private Facade facade;
//    CustomerDTO customerDTO=new CustomerDTO();
//    @Override
//    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
//        facade = (facade == null) ? (Facade) ctx.getBean("Facade") : facade;
//
//        String page = "/Menu.html";
//        ServletContext context = getServletContext();
//        RequestDispatcher rd = context.getRequestDispatcher(page);
//        rd.include(req, res);
//
//
//
//
//
//
//
//    }
//
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        service(req,resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        service(req,resp);
//    }
//
//
//}
