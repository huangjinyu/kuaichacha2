package com.kcc.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 参考别名 JwtLoginFilter
 */
@Component
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    private JwtUtils jwtUtils;

//    BasicAuthenticationFilter
//    AbstractAuthenticationProcessingFilter
//    private final AuthenticationManager authenticationManager;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    private boolean postOnly = true;

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
        setAuthenticationManager(authenticationManager);
//        this.securityConfiguration = securityConfiguration;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        // String verify_code = (String) request.getSession().getAttribute("verify_code");

        // 从 请求体（request body） 获取参数 @RequestBody
        ObjectMapper objectMapper = new ObjectMapper();
        LoginRequestDTO loginRequestDTO = objectMapper.readValue(request.getInputStream(), LoginRequestDTO.class);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                loginRequestDTO.getUsername(),
                loginRequestDTO.getPassword()
        );

        // 表单、URL参数 获取参数
//        String username = obtainUsername(request);
//        String password = obtainPassword(request);
//
//        if (username == null) {
//            username = "";
//        }
//
//        if (password == null) {
//            password = "";
//        }
//
//        username = username.trim();
//
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);

//        return authenticationManager.authenticate(authentication);

        // 会调用 JwtUserDetailsService#loadUserByUsername，进行用户认证
        return getAuthenticationManager().authenticate(authentication);
    }

    /**
     * 认证成功回调，生成 token
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Object principal = authResult.getPrincipal();
        JwtUser user = (JwtUser) principal;

//        if (principal instanceof JwtUser) {
//            user = (JwtUser) principal;
//        }

//        String username;
//
//        if (principal instanceof MyUserDetails) {
//            username = ((MyUserDetails) principal).getUsername();
//        } else if (principal instanceof UserDetails) {
//            username = ((UserDetails) principal).getUsername();
//        }
//        else if (principal instanceof JwtUser) {
//            username = ((JwtUser) principal).getUsername();
//        }
//        else {
//            username = principal.toString();
//        }

        // 一定要注意不能在 token 存放敏感的字段，比如 密码，手机号等信息

        // 权限不放入 token，每次操作进行数据库查询，保证修改权限后的时时性
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.ISSUER, securityConfiguration.getIssuer());
        // 用户名（如果担心户名泄漏，可以使用数据库唯一的标识符进行设计）
        claims.put(Claims.SUBJECT, user.getUsername());

        // 生成 用户名令牌

        // 用户密钥
        String usernameSecret = jwtUtils.generateSecret(securityConfiguration.getSecret());
        String usernameToken = jwtUtils.generateToken(claims, usernameSecret);

        // 生成 认证令牌，该令牌与用户名令牌，密钥不同，认证令牌使用用户独立的密钥

//        加盐 Salt
        String secret = jwtUtils.generateSecret(user.getSecret());
        String token = jwtUtils.generateToken(claims, secret);

        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // 设置 请求头 UsernameAuthorization = Bearer xxx.yyy.zzz
        response.setHeader(SecurityConstants.TOKEN_USERNAME_HEADER, SecurityConstants.TOKEN_PREFIX + usernameToken);

        // 设置 请求头 Authorization = Bearer xxx.yyy.zzz
        response.setHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token);

        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(token));
        out.flush();
        out.close();

//        String token = Jwts.builder()
//                .setHeaderParam("TYP", "JWT")
//                .setHeaderParam("qoasdff", "wtadfafw")
//                .setHeaderParam("abc", "1234")
//                .setIssuer(securityConfiguration.getIssuer())
//                // 主题 存放用户名
//                .setSubject(user.getUsername())
//                // 自定义属性 存放用户求权限
//                // 用户权限可以通用用户名，在数据库进行查询获取，这样可以做到及时性，时间换时时性，查询数据库需要一定的时间损耗
////                .claim("authorities", "admin")
////                .claim("adsfadf", "iampassword")
//                // 失效时间
//                .setExpiration(new Date(System.currentTimeMillis() + securityConfiguration.getExpiration() * 1000))
//                // 签名算法和密钥
//                .signWith(SignatureAlgorithm.HS256, securityConfiguration.getSecret())
//                .compact();
    }

    /**
     * 认证失败等其它异常回调
     *
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString("唉~~~，用户名或密码错误！"));
        out.flush();
        out.close();
    }
}
