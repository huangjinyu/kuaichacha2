package com.kcc.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.sql.DataSource;
import java.util.function.Function;

////@Configuration
////@EnableWebSecurity
//@EnableWebSecurity(debug = true)
////@Order(1)
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MyWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    /**
     * 开放访问的请求
     */
    private final static String[] PERMIT_ALL_MAPPING = {
            "/security/logout.html",
            // 登录页别名
            // "/security/login",
            // 登录处理
            "/security/dologin",
            "/api/hello",
            "/api/login",
            "/api/home",
            "/api/verifyImage",
            "/api/image/verify",
            "/images/**"
    };
    public static String ADMIN = "ROLE_ADMIN";
    public static String USER = "ROLE_USER";
    @Autowired
    private DataSource dataSource;

//    @Bean
//    @Override
////    public UserDetailsService userDetailsService(DataSource dataSource) {
//    protected UserDetailsService userDetailsService() {
//        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
//        Function<String, String> encoder = s -> passwordEncoder().encode(s);
//        manager.createUser(User.withUsername("root").password("root").roles("ADMIN").passwordEncoder(encoder).build());
//        manager.createUser(User.withUsername("chopper").password("123456").roles("USER").passwordEncoder(encoder).build());
//        return manager;
//    }

    @Bean
    public UserDetailsService myUserDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        Function<String, String> encoder = s -> passwordEncoder().encode(s);
//
//        if (!manager.userExists("javaboy")) {
//            manager.createUser(User.withUsername("javaboy").password("123").roles("admin").build());
//        }
//        if (!manager.userExists("江南一点雨")) {
//            manager.createUser(User.withUsername("江南一点雨").password("123").roles("user").build());
//        }

        if (!manager.userExists("admin")) {
            manager.createUser(User.withUsername("admin").passwordEncoder(encoder).password("admin").roles("admin").build());
            // passwordEncoder(encoder) 与 passwordEncoder().encode 不能同时出现，会进行2次加密
//            manager.createUser(User.withUsername("admin").passwordEncoder(encoder).password(passwordEncoder().encode("admin")).roles("admin").build());
//            manager.createUser(User.withUsername("admin").passwordEncoder(encoder).password("admin").roles("admin").build());
        }

        if (!manager.userExists("user")) {
            manager.createUser(User.withUsername("user").password("user").roles("user").build());
        }

//        manager.createUser(User.withUsername("chopper").password("123456").roles("USER").passwordEncoder(encoder).build());

        return manager;
    }

    /**
     * 只保留一个 UserDetailsService bean
     *
     * @return
     */
//    @Bean
//    public UserDetailsService myUserDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//
//        manager.createUser(User.withUsername("111111").password(passwordEncoder().encode("111111")).roles("NORMAL").build());
////        manager.createUser(User.withUserDetails());
//        manager.createUser(User.withUsername("222222").password(passwordEncoder().encode("222222")).roles("ADMIN").build());
//
//        return manager;
//    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
//        manager.setDataSource(dataSource);
//        if (!manager.userExists("javaboy")) {
//            manager.createUser(User.withUsername("javaboy").password("123").roles("admin").build());
//        }
//        if (!manager.userExists("江南一点雨")) {
//            manager.createUser(User.withUsername("江南一点雨").password("123").roles("user").build());
//        }
//        return manager;
//    }

//    @Bean
//    public MyUserDetailsService myUserDetailsService() {
//        return new MyUserDetailsService();
//    }

//    @Bean
//    public MyUserDetailsService myUserDetailsService1() {
//        return new MyUserDetailsService();
//    }

//
//    @Bean
//    public MyUserDetailsService2 myUserDetailsService2() {
//        return new MyUserDetailsService2();
//    }

    /**
     * 用户不存在，显示自定义异常，默认会显示 Bad credentials
     * setHideUserNotFoundExceptions(false)
     *
     * @return
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // 自定义认证异常信息， 默认抛出 UsernameNotFoundException 会被系统捕获进行重新封装，设置 setHideUserNotFoundExceptions(false)
        // 为 false，异常不进行封装
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        // 盐
//        ReflectionSaltSource saltSource = new ReflectionSaltSource();
//        saltSource.setUserPropertyToUse("salt");
//        authProvider.setSaltSource(saltSource);

        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 基于内存
//        auth.inMemoryAuthentication().withUser("aaaaaa").password(passwordEncoder().encode("aaaaaa")).roles("SUPERMANAGER1");
//        auth.inMemoryAuthentication().withUser("111111").password(passwordEncoder().encode("111111")).roles("SUPERMANAGER2");
//        auth.inMemoryAuthentication().withUser("111111").password(passwordEncoder().encode("111111")).roles("SUPERMANAGER3");

        // 基于数据库
        JdbcUserDetailsManagerConfigurer configurer = auth.jdbcAuthentication().dataSource(dataSource);

        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

        // configurer.passwordEncoder(passwordEncoder())

        if (!manager.userExists("666666")) {
            configurer.withUser("666666")
                    .password(passwordEncoder().encode("666666"))
                    // 角色
                    .roles("user", "manager")
                    // 权限
                    .authorities("createGoods", "updateGoods", "deleteGoods",
                            "createOrder", "updateOrder", "deleteOrder");
        }

        if (!manager.userExists("7777777")) {
            configurer.withUser("7777777")
                    .password(passwordEncoder().encode("7777777"))
                    .roles("user", "manager", "admin");
        }

        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("aaaaaa").password(passwordEncoder().encode("aaaaaa")).roles("SUPERMANAGER1")
                .and()
                .withUser("555555").password(passwordEncoder().encode("555555")).roles("SUPERMANAGER2");
//                .and()
//                .withUser("111111").password(passwordEncoder().encode("111111")).roles("SUPERMANAGER3");


        auth.authenticationProvider(daoAuthenticationProvider());
    }

//    @Autowired
//    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder
//                .authenticationProvider(daoAuthenticationProvider());
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserDetailsService()).passwordEncoder(passwordEncoder());
//    }

//    @Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserDetailsService()).passwordEncoder(passwordEncoder());
//
////        auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("USER");
////        auth.inMemoryAuthentication().withUser("admin").password("root123").roles("ADMIN");
////        auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN","DBA");
//    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        System.out.println("configure(AuthenticationManagerBuilder auth)");
    //////// 设置 roles() 为空
    ////// auth.inMemoryAuthentication().withUser("aaaaaa").password(passwordEncoder().encode("111111")).roles();
////        auth.inMemoryAuthentication().withUser("aaaaaa").password(passwordEncoder().encode("111111")).roles("admin");
//        // auth.inMemoryAuthentication().withUser("aaaaaa").password("$2a$10$9.UUvQVRxNXWcxIBCWANHuubwAnHG0wz4OtUAPitsERqasezQKm8e").roles("admin");
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password(myEncoder().encode("111111"))
//                .and()
//                .withUser("user").password(myEncoder().encode("111111"))
//                .and()
//                .passwordEncoder(passwordEncoder());
//    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

//    @Bean
//    public JwtLoginFilter jwtLoginFilter() {
//        return new JwtLoginFilter();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        LazyCsrfTokenRepository
//        CookieCsrfTokenRepository

//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//        http.sessionManagement().invalidSessionUrl("http://www.zahuo.com");

//        在 UsernamePasswordAuthenticationFilter 前添加 BeforeLoginFilter

//        http.addFilterBefore(new BeforeLoginFilter(), WebAsyncManagerIntegrationFilter.class);
//        http.addFilterBefore(new TokenFilter(), UsernamePasswordAuthenticationFilter.class);

//        http.addFilterBefore(new JwtAuthenticateFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
//                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

//        http.addFilter(new JwtAuthenticateFilter(authenticationManager()));

        http
                //默认都会产生一个hiden标签 里面有安全相关的验证 防止请求伪造 这边我们暂时不需要可禁用掉
//                .csrf().disable()
//                .csrf().csrfTokenRepository(new LazyCsrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .csrf().csrfTokenRepository(new HttpSessionCsrfTokenRepository())
                .csrf().csrfTokenRepository(new CookieCsrfTokenRepository())
                .and()
                .httpBasic()
                .realmName("hello")
                .and()
                .formLogin()
                .loginPage("/security/login.html")
                .loginProcessingUrl("/security/login")
                .permitAll()
//                .successHandler(
//                        new AuthenticationSuccessHandler() {
//                            @Override
//                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//
//                            }
//                        }
//
//                )
//                .successHandler((req, resp, authentication) -> {
//                    Object principal = authentication.getPrincipal();
//                    resp.setContentType("application/json;charset=UTF-8");
//                    PrintWriter out = resp.getWriter();
//                    out.write(new ObjectMapper().writeValueAsString(principal));
//                    out.flush();
//                    out.close();
//                })
                //.successHandler(new LoginSuccessHandler())
//                .failureHandler((req, resp, e) -> {
//                    resp.setContentType("application/json;charset=UTF-8");
//                    PrintWriter out = resp.getWriter();
//                    out.write(e.getMessage());
//                    out.flush();
//                    out.close();
//                })
                .failureHandler(new LoginFailureHandler())
                //必须允许所有用户访问我们的登录页（例如未验证的用户，否则验证流程就会进入死循环）
                //formLogin().permitAll()方法允许所有用户基于表单登录访问/security/login.html这个页
                .permitAll()
                .and()
                .logout()
                //禁用了csrf，也想要执行post请求才能退出功能，需要设置logoutRequestMatcher
                //.logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
                //.logoutSuccessUrl("http://www.baidu.com/")
                //.logoutUrl("/security/logout")
//                .deleteCookies("hello", "JSESSIONID", "password")
//                .invalidateHttpSession(false)
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(PERMIT_ALL_MAPPING).permitAll()
                .antMatchers("/api/admin/**")
                // 只有 ADMIN 才可以访问
                .hasAnyAuthority(ADMIN)
                .antMatchers("/hello/test1", "/hello/test2").permitAll()
                .antMatchers("/hello/test3").hasAuthority("read")
                .antMatchers("/hello/test4").hasAuthority("delete")
//              .antMatchers("/hello/test5").hasRole("ROLE_ADMIN")//错误 hasRole 方法，不能加ROLE_前缀
//              .antMatchers("/hello/test5").hasRole("ADMIN")
                .antMatchers("/hello/test5").hasAuthority("ROLE_ADMIN")
                .antMatchers("/hello/test7").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/hello/test9").permitAll()
                .antMatchers("/hello/test10").permitAll()

//                .antMatchers("/goods/create").hasAuthority("GOODS_CREATE")
//                .antMatchers("/goods/update").hasAuthority("GOODS_UPDATE")
//                .antMatchers("/goods/delete").hasAuthority("GOODS_DELETE")
//                .antMatchers("/goods/list").hasAuthority("GOODS_LIST")


                .anyRequest()
//              .permitAll()
                .authenticated()
//                .and()
//                .rememberMe()
//                // 需要指定 userDetailsService
//                .userDetailsService(myUserDetailsService())
//                // 持久化
//                .tokenRepository(persistentTokenRepository())
//                .rememberMeCookieName("sssssssssssssssss")
//                .tokenValiditySeconds(2 * 24 * 60 * 60)
                .and()
                .exceptionHandling()
                // 未认证，没有权限都属于异常
                // 未认证时，返回json，不重定向登录页
                .authenticationEntryPoint(new MyAuthenticationEntryPoint())
                // 未授权时，返回 json，不重定向未授权异常页，默认： /error/403.html 或 /error/4xx.html，有优先级
                .accessDeniedHandler(new MyAccessDeniedHandler());
//                .accessDeniedPage();

//                .authenticationEntryPoint((request, response, authException) -> {
//                    response.setContentType("application/json;charset=UTF-8");
//                    response.setStatus(HttpStatus.OK.value());
//
//                    Result result = new Result();
//                    result.setCode(ResultEnum.UNAUTHORIZED.getCode());
//                    result.setMsg(ResultEnum.UNAUTHORIZED.getMsg());
//
//                    PrintWriter writer = response.getWriter();
//
//                    ObjectMapper objectMapper = new ObjectMapper();
//                    String value = objectMapper.writeValueAsString(result);
//
//                    writer.write(value);
//                    //                    writer.write(new ObjectMapper().writeValueAsString(result));
//
//                    writer.flush();
//                    writer.close();
//
//                });
//                .and()
//                .exceptionHandling().accessDeniedPage("/error/404-1.html");

    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .formLogin()
//                .loginPage("/login.html")
//                .loginProcessingUrl("/login")
//                .permitAll()  // 表单相关的接口不需认证
//                .and()
//                //.logout()
////                .logoutUrl("/logout")
//               // .permitAll()
//                //.httpBasic()
//                //.and()
//                .authorizeRequests()
//                .antMatchers("/login.html", "/login", "/logout", "/logout.html", "/favicon.ico").permitAll()
//                .antMatchers("/img/**").permitAll()
////                .and()
////                .authorizeRequests()
//                .anyRequest()
//                .authenticated();
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //web.ignoring().antMatchers("/img/**");

        web.ignoring().antMatchers("/css/**", "/js/**", "/src/main/error/**");
    }
}
