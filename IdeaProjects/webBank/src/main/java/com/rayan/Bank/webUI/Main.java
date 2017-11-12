package com.rayan.Bank.webUI;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/main")
public class Main extends HttpServlet{


    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = "/Menu.html";
        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher(page);
        rd.include(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service(req,resp);
    }
}
