package com.kcc.core.security;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        exception.printStackTrace();
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
//        out.write(exception.getMessage());
        out.print("用户名或密码错误");
        out.flush();
        out.close();
    }
}
