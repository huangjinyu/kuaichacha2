package com.kcc.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcc.core.Result;
import com.kcc.core.ResultCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
//        response.setContentType("application/json;charset=UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        Result result = new Result();

        result.setCode(ResultCodeEnum.FORBIDDEN.getCode());
        result.setMsg(ResultCodeEnum.FORBIDDEN.getMsg());

        PrintWriter writer = response.getWriter();

        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(result);

        writer.write(value);
//        writer.write(new ObjectMapper().writeValueAsString(result));
//        JSONObject.toJSONString(result)

        writer.flush();
        writer.close();
    }
}
