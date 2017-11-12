package com.rayan.Bank.webUI;

import com.rayan.Bank.DTO.CustomerDTO;
import com.rayan.Bank.exception.CantAddData;
import com.rayan.Bank.exception.CantCastToOtherClass;
import com.rayan.Bank.exception.CantFetchData;
import com.rayan.Bank.facade.Facade;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/customer/Insert")
public class CustomerInsertUI extends HttpServlet {

    private Facade facade;
    CustomerDTO customerDTO = new CustomerDTO();
    private PrintWriter out;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        facade = (facade == null) ? (Facade) ctx.getBean("Facade") : facade;
        String page = "/Menu.html";
        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher(page);
        rd.include(req, resp);
        String page1 = "/insertCustomer.html";
        ServletContext context1 = getServletContext();
        RequestDispatcher rd1 = context1.getRequestDispatcher(page1);
        rd1.include(req, resp);

        String name = req.getParameter("firstname");
        customerDTO.setName(name);
        String lastName = req.getParameter("lastname");
        customerDTO.setFamily(lastName);
        String customerNumber = req.getParameter("customerNumber");
        customerDTO.setCustomerNumber(customerNumber);
        String email = req.getParameter("email");
        customerDTO.seteMail(email);


        try {
            facade.customerInsert(customerDTO);
            out = resp.getWriter();
            out.println("data added succesfully!");
        } catch (CantFetchData cantFetchData) {
            cantFetchData.printStackTrace();
        } catch (CantAddData cantAddData) {
            cantAddData.printStackTrace();
        } catch (CantCastToOtherClass cantCastToOtherClass) {
            cantCastToOtherClass.printStackTrace();
        }

       }
    }





//
//    CustomerInsertUI("/customer/update")){
//
//            String page2 = "/searchBox.html";
//            ServletContext context2 = getServletContext();
//            RequestDispatcher rd2 = context2.getRequestDispatcher(page2);
//            rd2.include(request, response);
//            String customerNumber=request.getParameter("customerSearch");
//            try {
//                if(facade.customerSearch(customerNumber)!=null){
//
//
//
//                }
//            } catch (CantCastToOtherClass cantCastToOtherClass) {
//                cantCastToOtherClass.printStackTrace();
//            } catch (CantFetchData cantFetchData) {
//                cantFetchData.printStackTrace();
//            }
//
//
//        }
//
//
//    }
//    private String menu(){
//        return "<html lang=\"en\">\n" +
//                "    <head>\n" +
//                "        <meta charset=\"utf-8\" />\n" +
//                "        <meta name=\"author\" content=\"Script Tutorials\" />\n" +
//                "        <title>Whirling dropdown menu | Script Tutorials</title>\n" +
//                "\n" +
//                "        <!-- add styles -->\n" +
//                "        <link href=\"/css/main.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
//                "    </head>\n" +
//                "    <body>\n" +
//                "        <div class=\"container\" id=\"main\" role=\"main\">\n" +
//                "            <ul class=\"menu\">\n" +
//                "                <li><a href=\"#\">Home</a></li>\n" +
//                "                <li><a href=\"#s1\">Customer</a>\n" +
//                "                    <ul class=\"submenu\">\n" +
//                "                        <li><a href=\"#\">insert</a></li>\n" +
//                "                        <li><a href=\"#\">update</a></li>\n" +
//                "                        <li><a href=\"#\">delete</a></li>\n" +
//                "                    </ul>\n" +
//                "                </li>\n" +
//                "                <li class=\"active\"><a href=\"#s2\">Deposit</a>\n" +
//                "                    <ul class=\"submenu\">\n" +
//                "                        <li><a href=\"#\">insert</a></li>\n" +
//                "                        <li><a href=\"#\">update</a></li>\n" +
//                "                        <li><a href=\"#\">delete</a></li>\n" +
//                "                        <li><a href=\"#\">Deposit</a></li>\n" +
//                "                        <li><a href=\"#\">Withdraw</a></li>\n" +
//                "                        <li><a href=\"#\">transfer</a></li>\n" +
//                "                    </ul>\n" +
//                "                </li>\n" +
//                "                <li><a href=\"#\">Report</a>\n" +
//                "                    <ul class=\"submenu\">\n" +
//                "                        <li><a href=\"#\">Deposit</a></li>\n" +
//                "                        <li><a href=\"#\">Customer</a></li>\n" +
//                "                        <li><a href=\"#\">Deposit for a customer</a></li>\n" +
//                "                    </ul>\n" +
//                "                </li>\n" +
//                "    <li><a href=\"#\">backup</a>\n" +
//                "     <ul class=\"submenu\">\n"+
//                "    <li><a href=\"#\">importToDB</a></li>\n" +
//                "    <li><a href=\"#\">exportFromDB</a></li>\n" +
//
//                "            </ul>\n" +
//                "        </div>\n";
//    }
//    private String insertCustomer(HttpServletRequest req,HttpServletResponse resp){
//        return
//                "\n" +
//                "<div>\n" +
//                "  <form>\n" +
//                "    <label for=\"fname\">First Name</label>\n" +
//                "    <input type=\"text\" id=\"fname\" name=\"firstname\"pattern=\"[A-Za-z] \"placeholder=\"enter first name..\">\n" +
//                "\n" +
//                "    <label for=\"lname\">Last Name</label>\n" +
//                "    <input type=\"text\" id=\"lname\" name=\"lastname\" placeholder=\"enter last name..\">\n" +
//                "\n" +
//                "    <label for=\"customerNumber\">Customer Number</label>\n" +
//                "    <input type=\"text\" id=\"customerNumber\" name=\"customerNumber\" pattern=\"[0-9]{8}\"placeholder=\"enter  a 8digit customer number..\">\n" +
//                "\n" +
//                "    <label for=\"email\">Email</label>\n" +
//                "    <input type=\"text\" id=\"email\" name=\"email\" pattern=\"[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$\"placeholder=\"enter a valid email Address..\">\n" +
//                "  \n" +
//                "    <input type=\"submit\" value=\"Submit\">\n" +
//                "  </form>\n" +
//                "</div>" +
//                "</body>" +
//                "</html>";
//    }




