package com.rayan.Bank.webUI;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns = {"/*"})
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(httpServletRequest.getSession().getServletContext());

        LoginBean bean = ctx.getBean(LoginBean.class);
        List<String> uriList = new ArrayList<>();
        uriList.add("/Login.xhtml");
        if(uriList.contains(httpServletRequest.getRequestURI()) || httpServletRequest.getRequestURI().matches("/javax.faces.resource/.*") || bean.getUserName() != null){
            chain.doFilter(request, response);
        }else{
            ((HttpServletResponse)response).sendRedirect("/Login.xhtml");
        }
    }

    @Override
    public void destroy() {

    }
}
