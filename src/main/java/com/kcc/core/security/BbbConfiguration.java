package com.kcc.core.security;

import com.kcc.core.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(1)
public class BbbConfiguration extends BaseConfiguration {
    public BbbConfiguration() {
        System.out.println("BbbConfiguration .........................");
    }

    @Bean("getLoginUser1")
    public Result getLoginUser() {
        System.out.println("getLoginUser1");
        return new Result();
    }
}
