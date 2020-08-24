package com.kcc.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Jwt 认证
 */
@EnableWebSecurity(debug = true)
//@Order(1)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class JwtWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityConfiguration securityConfiguration;

    @Autowired
    private JwtLoginFilter jwtLoginFilter;

    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public Md5PasswordEncoder passwordEncoder() {
//        return new Md5PasswordEncoder();
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new JwtUserDetailsService();
    }

//    @Bean
//    public UserDetailsService myUserDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//
//        JwtUser user1 = JwtUser.builder()
//                .username("111111")
//                .password(passwordEncoder().encode("111111"))
//                .roles("USER")
//                .secret("ffasfwoeflasdfj341234==fasdfasdf81jalsdjfas;dfjasd9g1sdfjlasdjfa;lsdfjlasdfjlasdjflasdjfasdjfo2fasjdflasjdflasdfjalsdfhgihieriqwevfaf4089vV$G#$GDFGSDAfasdf3u49")
//                .accountNonExpired(true)
//                .accountNonLocked(true)
//                .credentialsNonExpired(true)
//                .enabled(true)
//                .build();
//
//        manager.createUser(user1);
//
//        manager.createUser(User.withUsername("111111").password(passwordEncoder().encode("111111")).roles("user").build());
////        manager.createUser(User.withUserDetails());
//        manager.createUser(User.withUsername("222222").password(passwordEncoder().encode("222222")).roles("admin").build());
//
//        return manager;
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用缓存
        http.headers().cacheControl();

        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                // 跨域预检请求，为了安全起见，控制器方法，不允许 OPTIONS 方式请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/hello2/testcors").permitAll()
                .antMatchers("/hello2/testcors2").permitAll()
                .antMatchers("/api/test/**").permitAll()
                .anyRequest()
                .authenticated()
//                .and()
//                .httpBasic()
                .and()

//                .addFilterAt(new JwtLoginFilter(authenticationManager(), securityConfiguration), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtAuthorizationFilter, JwtLoginFilter.class)
//                .addFilterBefore(new JwtLoginFilter(authenticationManager(), securityConfiguration), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtLoginFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

//    @Bean
//    public JwtLoginFilter jwtLoginFilter() throws Exception {
//        return new JwtLoginFilter(authenticationManager());
//    }

//    @Bean
//    public JwtAuthorizationFilter jwtAuthorizationFilter() {
//        return new JwtAuthorizationFilter();
//    }


//    @Bean
//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
