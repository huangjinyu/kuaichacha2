package com.kcc.core.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// FilterConfiguration 类进行 bean 配置，可以设置过滤器之间的优先级
// 如果用的是 @Component 注解，bean 不能取默认的 corsFilter 名称，会与 org.springframework.web.filter.CorsFilter 冲突
// @Component
// @WebFilter
public class MyCorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("cors filter");

        HttpServletResponse res = (HttpServletResponse) response;
        res.addHeader("Access-Control-Allow-Credentials", "true");
        res.addHeader("Access-Control-Allow-Origin", "http://localhost:8613");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
//        res.addHeader("Access-Control-Allow-Headers", "Content-Type,X-CAF-Authorization-Token,sessionToken,X-TOKEN");
        if (((HttpServletRequest) request).getMethod().equals("OPTIONS")) {
            response.getWriter().println("ok");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}