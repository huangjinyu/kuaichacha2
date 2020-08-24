package com.kcc.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcc.core.Result;
import com.kcc.core.ResultCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// MyAuthenticationEntryPoint 参考别名 UnauthorizedEntryPoint
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (MyUtils.isAjax(request)) {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType("application/json;charset=UTF-8");
//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            response.setCharacterEncoding("UTF-8");

            // 默认格式
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "未认证");

            // 自定义格式
            Result result = new Result();
            result.setCode(ResultCodeEnum.UNAUTHORIZED.getCode());
            result.setMsg(ResultCodeEnum.UNAUTHORIZED.getMsg());

            PrintWriter writer = response.getWriter();

            ObjectMapper objectMapper = new ObjectMapper();
            String value = objectMapper.writeValueAsString(result);

            writer.write(value);
//          writer.write(new ObjectMapper().writeValueAsString(result));

            writer.flush();
            writer.close();
        } else {
            response.sendRedirect("/security/login.html");
        }
    }
}
