package com.kcc.service.impl;

import com.kcc.service.HelloService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service("helloService")
public class HelloServiceImpl implements HelloService {
    @Override
    public String test1() {
//        test2();
        return "测试1";
    }

    @PreAuthorize("hasAuthority('write')")
    // @PreAuthorize("hasRole('ROLE_write')")
    @Override
    public String test2() {
        return "测试2 @PreAuthorize(\"hasAuthority('write')\")";
    }
}
