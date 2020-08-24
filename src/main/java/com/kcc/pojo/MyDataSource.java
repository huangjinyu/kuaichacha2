package com.kcc.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Setter
@Getter
@ToString
@Slf4j
//@ConfigurationProperties(prefix = "jdbc")
@Validated
public class MyDataSource {
    private String url;
    private String driverClassName;
    private String username;

    @NotNull
    private String password;

    @NotNull
    @NotEmpty
    private  String timeout;

    public void setUrl(String url) {

        //ConfigurationPropertiesBindingPostProcessor

        log.info("setUrl 被执行依赖注入");
        this.url = url;
    }

//    public void settiMEO_u_t(String timeout, String a) {
//        log.info("setTimeout 被执行依赖注入 " + timeout);
//    }
//
//    public void settiMEO_u_t(String timeout) {
//        log.info("setTimeout 被执行依赖注入 String timeout " + timeout);
//    }
//
//    public void settiMEO_u_t(Integer timeout) {
//        log.info("setTimeout 被执行依赖注入 Integer timeout " + timeout);
//    }
}
