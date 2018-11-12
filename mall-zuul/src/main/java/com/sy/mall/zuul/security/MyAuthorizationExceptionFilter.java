package com.sy.mall.zuul.security;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dell on 2018/11/8.
 * @author dell
 */
public class MyAuthorizationExceptionFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=utf-8");
        try {
            chain.doFilter(request, response);
        } catch (Exception var9) {
            if (var9.getCause() instanceof ArithmeticException){
                throw  var9;
            }
            res.getWriter().write(var9.getMessage());
        }
    }
}
