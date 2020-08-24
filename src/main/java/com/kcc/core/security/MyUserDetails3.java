package com.kcc.core.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MyUserDetails3 extends User {
    private String nickName;
    private String mobileNumber;


    public MyUserDetails3(String username, String password, Collection<? extends GrantedAuthority> authorities, String mobileNumber) {
        super(username, password, authorities);
        this.mobileNumber = mobileNumber;
    }

    public MyUserDetails3(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
