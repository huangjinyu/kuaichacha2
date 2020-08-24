package com.kcc.core.security;

import com.kcc.dao.UserDao;
import com.kcc.pojo.po.UserPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername = " + username);

        JwtUser user = null;

        UserPO userPO = userDao.loadUserByUsername(username);

        if (userPO == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        if (userPO != null) {
            user = JwtUser
                    .builder()
                    .username(userPO.getUsername())
                    .password(userPO.getPassword())
                    .roles("USER")
                    .secret(userPO.getSecret())
                    .accountNonExpired(userPO.getAccountNonExpired())
                    .accountNonLocked(userPO.getAccountNonLocked())
                    .credentialsNonExpired(userPO.getCredentialsNonExpired())
                    .enabled(userPO.getEnabled())
                    .passwordLastResetDate(userPO.getPasswordLastResetDate())
                    .build();
        }

        return user;
    }
}
