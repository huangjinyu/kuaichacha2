package com.kcc.core.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "security.jwt.token")
public class SecurityConfiguration {
    /**
     * 密钥很重要，需要隔一段时间进行修改，密钥一旦公开用户信息将会不安全
     */
    private String secret;
    /**
     * 盐，用于混淆加密
     */
    private String secretSalt;
    private String issuer;
    private Long expiration;
}
