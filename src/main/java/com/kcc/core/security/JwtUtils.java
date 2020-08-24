package com.kcc.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 需要配合配置文件使用，全局变量需要从配置文件获取
 * 一定要注意不能在 token 存放敏感的字段，比如 密码，手机号等信息
 */
@Component
public class JwtUtils {
//    private static final String CLAIM_KEY_USERNAME = "sub";
//    private static final String CLAIM_KEY_ID = "id";
//    private static final String CLAIM_KEY_ROLES = "roles";

    @Autowired
    private SecurityConfiguration securityConfiguration;

//    @Value("${jwt.token.secret}")
//    private String secret;
//
//    @Value("${jwt.token.expiration}")
//    private int expiration; //过期时长，单位为秒,可以通过配置写入。

    /**
     * 获取用户名（从请求头 Authorization 获取取用户名）
     *
     * @param token
     * @param secret
     * @return
     */
    public String getUsernameFromToken(String token, String secret) {
        String username;
        try {
            username = getClaimsFromToken(token, secret).getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 获取用户名（从请求头 UsernameAuthorization 获取取用户名）
     * 密钥使用的是公有密钥，从配置文件获取
     *
     * @param token
     * @return
     */
    public String getUsernameFromUsernameToken(String token) {
        return getUsernameFromToken(token, generateSecret(securityConfiguration.getSecret()));
    }

    public Date getCreatedDateFromToken(String token, String secret) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token, secret);
            created = claims.getIssuedAt();
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token, String secret) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token, secret);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token, String secret) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 生成过期日期
     * 从配置文件获取配置 securityConfiguration expiration，单位：秒
     *
     * @return
     */
    private Date generateExpirationDate() {
        // securityConfiguration.getExpiration() 单位是秒，* 1000 转换成毫秒
        return new Date(System.currentTimeMillis() + securityConfiguration.getExpiration() * 1000);
    }

    private Boolean isTokenExpired(String token, String secret) {
        final Date expiration = getExpirationDateFromToken(token, secret);
        return expiration.before(new Date());
    }

    /**
     * 从 User 生成令牌
     *
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;

        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT, userDetails.getUsername());
//        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(Claims.ISSUED_AT, new Date());

//        claims.put(CLAIM_KEY_ID, userDetails.getId());
//        claims.put(CLAIM_KEY_ROLES, userDetails.getAuthorities());
        return generateToken(claims, generateSecret(user.getSecret()));
    }

    /**
     * 从 claims 生成令牌
     *
     * @param claims
     * @return
     */
    public String generateToken(Map<String, Object> claims, String secret) {
//        String s = Base64.encode(secret.getBytes());

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                // 声明
                .setClaims(claims)
                // 创建日期声明
                .setIssuedAt(new Date())
                // 过期日期声明
                .setExpiration(generateExpirationDate())
                // 密钥签名
                .signWith(SignatureAlgorithm.HS256, secret)
//                .signWith(SignatureAlgorithm.HS256, generateSecret(securityConfiguration.getSecret()))
                .compact();


    }

    public Boolean canTokenBeRefreshed(String token, String secret) {
        return !isTokenExpired(token, secret);
    }

    /**
     * 刷新令牌
     *
     * @param token  原令牌
     * @param secret 新令牌
     * @return
     */
    public String refreshToken(String token, String secret) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token, secret);
            claims.put(Claims.ISSUED_AT, new Date());
            refreshedToken = generateToken(claims, secret);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 加盐生成密钥
     *
     * @param secret
     * @return
     */
    public String generateSecret(String secret) {
        secret = secret + "/" + securityConfiguration.getSecretSalt();
        secret = DigestUtils.md5DigestAsHex(secret.getBytes());
        return secret;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final String username = getUsernameFromToken(token, generateSecret(user.getSecret()));
        final Date createdDate = getCreatedDateFromToken(token, generateSecret(user.getSecret()));

        // return (username.equals(user.getUsername()) && !(this.isTokenExpired(token, secret)) && !(this.isCreatedBeforePasswordLastResetDate(createdDate, user.getPasswordLastResetDate())));
        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token, generateSecret(user.getSecret()))
                && !isCreatedBeforePasswordLastResetDate(createdDate, user.getPasswordLastResetDate());
    }

    /**
     * 检查 token 是否是在最后一次修改密码之前创建的（账号修改密码之后之前生成的 token 即使没过期也判断为无效）
     *
     * @param createdDate
     * @param passwordLastResetDate
     * @return
     */
    private Boolean isCreatedBeforePasswordLastResetDate(Date createdDate, Date passwordLastResetDate) {
        return (passwordLastResetDate != null && createdDate.before(passwordLastResetDate));
    }
}
