//package com.rayan.Bank.webUI;
//
//import com.rayan.Bank.DTO.DepositDTO;
//import com.rayan.Bank.exception.CantAddData;
//import com.rayan.Bank.exception.CantFetchData;
//import com.rayan.Bank.facade.Facade;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@WebServlet(value = "/deposit")
//public class DepositWebUI extends HttpServlet {
//
//    private Facade facade;
////    private PrintWriter out;
//    DepositDTO depositDTO=new DepositDTO();
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
//        facade = (facade == null) ? (Facade) ctx.getBean("Facade") : facade;
//        resp.setContentType("text/html;charset=UTF-8");
//        out = resp.getWriter();
//        out.println(menu());
//
//        if(req.getRequestURI().equalsIgnoreCase("/deposit/insert")){
//            out.println(insertDeposit(req, resp));
//            String depositNumber = req.getParameter("depositNumber");
//            depositDTO.setDepositNumber(depositNumber);
//            String balance = req.getParameter("balance");
//            depositDTO.setBalance(balance);
//            String type = req.getParameter("type");
//            depositDTO.setType(type);
//
//            try {
//                facade.depositInsert(depositDTO);
//            } catch (CantFetchData cantFetchData) {
//                cantFetchData.printStackTrace();
//            } catch (CantAddData cantAddData) {
//                cantAddData.printStackTrace();
//            }
//
//
//        }
//        else if(req.getRequestURI().equalsIgnoreCase("/deposit/update")){
//            out.println(update(req, resp));
//            String depositNumber = req.getParameter("depositNumber");
//            try {
//                facade.depositSearch(depositNumber);
//            } catch (CantFetchData cantFetchData) {
//                cantFetchData.printStackTrace();
//            }
//
//
//        }
//        out.close();
//    }
//
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
//    private String insertDeposit(HttpServletRequest req,HttpServletResponse resp){
//        return
//                "\n" +
//                "<div>\n" +
//                "  <form>\n" +
//                "    <label for=\"depositNumber\">Deposit Number</label>\n" +
//                "    <input type=\"text\" id=\"depositNumber\" name=\"depositnumber\"pattern=\"[0-9]{10} \"placeholder=\"enter 10 digit Deposit number..\">\n" +
//                "\n" +
//                "    <label for=\"Balance\">Balance</label>\n" +
//                "    <input type=\"text\" id=\"Balance\" name=\"balance\" placeholder=\"enter Balance ..\">\n" +
//                "\n"+
//                " <label for=\"type\">DepositType</label>\n" +
//                "    <select id=\"type\" name=\"type\">\n" +
//                "      <option value=\"gharzolHasane\">Gharzolhasane</option>\n" +
//                "      <option value=\"sepordeSarmayeGozarieKutahModat\">SepordeSarmayeGozarieKutahModat</option>\n" +
//                "      <option value=\"sepordeSarmayeGozarieBolandModat\">SepordeSarmayeGozarieBolandModat</option>\n" +
//                "    </select>\n"+
//                "  \n" +
//                "    <input type=\"submit\" value=\"Submit\">\n" +
//                "  </form>\n" +
//                "</div>" +
//                "</body>" +
//                "</html>";
//    }
//    private String update(HttpServletRequest request,HttpServletResponse response){
//
//        return "<label for=\"depositSerach\">Enter customerNumber here : </label> \n" +
//                "<input id=\"depositNumber\" type=\"search\" placeholder=\"search\">\n";
//    }
//    private String delete(HttpServletRequest request,HttpServletResponse response){
//        return "";
//    }
//    private String deposit(HttpServletRequest request,HttpServletResponse response){
//        return "";
//    }
//    private String withdraw(HttpServletRequest request,HttpServletResponse response){
//        return "";
//    }
//    private String transfer(HttpServletRequest request,HttpServletResponse response){
//        return "";
//    }
//
//
//}
