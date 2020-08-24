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
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;

        MyUserDetails myUserDetails = null;

        UserPO userPO = userDao.loadUserByUsername(username);

        if (userPO == null) {
            // 抛出 UsernameNotFoundException 异常，消息会被覆盖，如果自定义 hideUserNotFoundExceptions 为 true，则可以使用
            throw new UsernameNotFoundException("用户名不存在，请重新输入");
            // throw new MyUsernameNotFoundException("用户名不存在，请重新输入");
            // throw new BadCredentialsException("用户名不存在，请重新输入");
        }


//        if (null == sysUser) {
//            log.error("用户{}不存在", username);
//            throw new BadCredentialsException("帐号不存在，请重新输入");
//        }
//        // 自定义业务逻辑校验
//        if ("userli".equals(sysUser.getUsername())) {
//            throw new BadCredentialsException("您的帐号有违规记录,无法登录!");
//        }
//        // 自定义密码验证
//        if (!password.equals(sysUser.getPassword())){
//            throw new BadCredentialsException("密码错误，请重新输入");
//        }


        if (userPO != null) {
            UserDetails userDetails = User
                    .withUsername(userPO.getUsername())
                    .password(userPO.getPassword())
                    .authorities(userPO.getAuthority())
//                    .accountLocked()
                    .disabled(!userPO.getEnabled())
//                    .roles(userPO.getRole())
                    .build();

            // 怎么同时添加角色和权限？

            myUserDetails = new MyUserDetails(userDetails);

            myUserDetails.setNickname(userPO.getNickname());
            //myUserDetails.isEnabled(userPO.getEnabled());

//            user = new User(userPO.getName(),userPO.getPassword(),);
//            myUserDetails = new MyUserDetails(userPO.getName(),
//                    userPO.getPassword(),
//                    AuthorityUtils.commaSeparatedStringToAuthorityList("read, ROLE_USER"),//设置权限和角色
//                    // 1. commaSeparatedStringToAuthorityList放入角色时需要加前缀ROLE_，而在controller使用时不需要加ROLE_前缀
//                    // 2. 放入的是权限时，不能加ROLE_前缀，hasAuthority与放入的权限名称对应即可
//                    userPO.getMobileNumber());

//            MyUserDetails
//                    .withUsername(userPO.getName())
//                    .password(userPO.getPassword())
//                    .authorities(userPO.getAuthority())
//                    .roles(userPO.getRole())
//                    .build();
        }

        return myUserDetails;
    }
}
