package com.kcc.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * https://www.jianshu.com/p/c7c7a029f22e
 */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取 UsernameAuthorization 请求头 和 Authorization 请求头

        // 步骤1：获取 UsernameAuthorization 请求头，该请求头通过公有密钥进行加密，用公有密钥进行解密，获取用户名
        // 步骤2：通过用户名查询数据库，获取用户信息及私有密钥
        // 步骤3：通过私有密钥， Authorization 请求头进行认证


        final String usernameToken = request.getHeader(SecurityConstants.TOKEN_USERNAME_HEADER);
        final String token = request.getHeader(SecurityConstants.TOKEN_HEADER);

        if (!StringUtils.isEmpty(usernameToken)
                && usernameToken.startsWith(SecurityConstants.TOKEN_PREFIX)
                && !StringUtils.isEmpty(token)
                && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {

            String preUsernameSecret = jwtUtils.generateSecret(securityConfiguration.getSecret());
//            String preUsername = jwtUtils.getUsernameFromUsernameToken(usernameToken, preUsernameSecret);
            String preUsername = jwtUtils.getUsernameFromToken(usernameToken, preUsernameSecret);

            if (!StringUtils.isEmpty(preUsername)) {
                // 从数据库获取用户信息
                JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(preUsername);

                String usernameSecret = jwtUtils.generateSecret(user.getSecret());
                String username = jwtUtils.getUsernameFromToken(token, usernameSecret);

                if (!StringUtils.isEmpty(username) && StringUtils.equals(preUsername, username)) {
                    // && SecurityContextHolder.getContext().getAuthentication() == null) {

                    // 判定token是否合法，不合法走异常处理exceptionHandling().authenticationEntryPoint
                    if (jwtUtils.validateToken(token, user)) {
                        // 合法，创建带用户名和密码以及权限的Authentication,这里实例化UsernamePasswordAuthenticationToken
                        // 构造函数内实际上已经设置为认证通过super.setAuthenticated(true);
                        // 构造函数3个参数：
                        // 用户信息（身份认证信息，还有其他外带信息都可以增加）
                        // 用户密码（于证明principal是正确的信息，比如密码）
                        // 用户权限（授权信息，比如角色）
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                user.getAuthorities()
                        );

                        // 设置获取 request 的一些 http 信息，把 http 的信息放到 authentication
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        // 记录日志
                        // log.info("authorizated user '{}', setting security context", username);

                        // 从 SecurityContextHolder 获取 SecurityContext 实例，设置 authentication
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }

        filterChain.doFilter(request, response);

//        String username = null;
//        String token = null;
//        //处理请求头中的token
//        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
//            //获取token
//            token = requestHeader.substring(7);
//            //获取用户名（外带数据）
//            try {
//                username = jwtUtil.getUsernameFromToken(token);
//            } catch (ExpiredJwtException e) {
//                //发生异常，需要记录日志
//                //log.error(e.getMessage());
//            }
//        }
//        //用户名判定
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            //获取用户信息
//            UserDetailsDto userDetailsDto = (UserDetailsDto) this.userDetailsService.loadUserByUsername(username);
//            //判定token是否合法，不合法走异常处理exceptionHandling().authenticationEntryPoint
//            if (jwtUtil.validateToken(token, userDetailsDto)) {
//                //合法，创建带用户名和密码以及权限的Authentication,这里实例化UsernamePasswordAuthenticationToken
//                //构造函数内实际上已经设置为认证通过super.setAuthenticated(true);
//                //构造函数3个参数：
//                // 用户信息（身份认证信息，还有其他外带信息都可以增加）
//                // 用户密码（于证明principal是正确的信息，比如密码）
//                // 用户权限（授权信息，比如角色）
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                        userDetailsDto,
//                        null,
//                        userDetailsDto.getAuthorities());
//                //设置获取request的一些http信息，把http的信息放到authentication
//                authentication.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(request)
//                );
//                //记录日志
//                //log.info("authorizated user '{}', setting security context", username);
//                //从SecurityContextHolder获取SecurityContext实例，设置authentication
//                //已验证的主体，或删除身份验证信息
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//        //调用后续filter
//        filterChain.doFilter(request, response);
    }
}
