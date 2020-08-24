package com.kcc.conf;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 配置自定义错误页
 */
//@Configuration
//public class WebServerAutoConfiguration {
//    @Bean
//    public ConfigurableServletWebServerFactory webServerFactory() {
//        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
////        ErrorPage errorPage400 = new ErrorPage(HttpStatus.BAD_REQUEST, "/error/123");
//
//        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/123.html");
//        factory.addErrorPages(errorPage404);
//        return factory;
//    }
//}
