package com.kcc.core.security;

import org.springframework.security.core.AuthenticationException;

public class MyUsernameNotFoundException extends AuthenticationException {
//    private static final long serialVersionUID = 0L;

    public MyUsernameNotFoundException(String msg) {
        super(msg);
    }

    public MyUsernameNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
