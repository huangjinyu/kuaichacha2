package com.kcc.pojo.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserPO implements Serializable {
    private Integer id;
    private String username;
    private String password;
    /**
     * 用户重置密码后，程序要重置用户的私有密钥，让重置密码之前生成的 token 失效
     * 系统可以隔几个月重置用户的私有密钥，防止数据库数据泄漏，通过私有密钥生成 token 进行登录
     * 私有密钥 可以 进行加密，数据库存的是明文数据，对密钥进行加密后再生成 token，可以防止程序员离职，利用明文密钥生成 token 进行登录
     * 也就是说数据库存放的是明文密钥，真正的是密钥对明文密钥进行加密的结果
     */
    private String secret;
    private String role;
    private String authority;
    private String mobileNumber;
    private String nickname;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    private Date passwordLastResetDate;

    // Lombok boolean 会生成 isXxx is开头的 get 方法
    // private boolean enabled;
    // isEnabled

    // Lombok Boolean 会生成 getXxx get开头的 get 方法
    // private boolean enabled;
    // getEnabled
}
