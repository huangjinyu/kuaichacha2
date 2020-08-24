package com.kcc.core.security;

import com.kcc.core.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(2)
public class AaaConfiguration extends BaseConfiguration {
    public AaaConfiguration() {
        System.out.println("AaaConfiguration .........................");
    }

    @Bean("getLoginUser2")
    public Result getLoginUser() {
        System.out.println("getLoginUser2");
        return new Result();
    }
}
