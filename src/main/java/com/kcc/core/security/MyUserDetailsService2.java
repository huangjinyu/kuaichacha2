package com.kcc.core.security;

import com.kcc.dao.UserDao;
import com.kcc.pojo.po.UserPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
public class MyUserDetailsService2 implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername = " + username);

        User user = null;

        UserDetails userDetails = null;

        UserPO userPO = userDao.loadUserByUsername(username);

        if (userPO == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        if (userPO != null) {
//            user = new User(userPO.getName(),userPO.getPassword(),);
            userDetails = User
                    .withUsername(userPO.getUsername())
                    .password(userPO.getPassword())
                    .authorities(userPO.getAuthority())
                    .roles(userPO.getRole())
                    .build();
        }

        return userDetails;
    }
}
