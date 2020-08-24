package com.kcc.conf;

import com.kcc.core.filter.TimeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FilterConfiguration {
//    @Bean
//    public Filter MyHiddenHttpMethodFilter(){
//        return new MyHiddenHttpMethodFilter();//自定义的过滤器
//    }
//    @Bean
//    public Filter tokenAuthorFilter(){
//        return new TokenAuthorFilter();//自定义的过滤器
//    }

    @Bean
    public FilterRegistrationBean filterRegistrationTimeFilterBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        filterRegistrationBean.setFilter(timeFilter);

        List<String> urls = new ArrayList<>();
        urls.add("/*");
        filterRegistrationBean.setUrlPatterns(urls);

        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return filterRegistrationBean;
    }

//    @Bean
//    public Filter myCorsFilter() {
//        return new MyCorsFilter();//自定义的过滤器
//    }
//
//    @Bean
//    public FilterRegistrationBean filterRegistrationMyCorsFilterBean() {
//        FilterRegistrationBean myCorsFilterBean = new FilterRegistrationBean();
//        myCorsFilterBean.setFilter(myCorsFilter());
////        myCorsFilterBean.setFilter(new MyCorsFilter());
//        myCorsFilterBean.addUrlPatterns("/*");
//        myCorsFilterBean.setOrder(0); // order的数值越小 则优先级越高
//        return myCorsFilterBean;
//    }


//    @Bean
//    public FilterRegistrationBean myCorsFilterRegistration() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
////        config.addAllowedOrigin("http://localhost:9000");
////        config.addAllowedOrigin("null");
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        config.addExposedHeader("qwer");
//        config.addExposedHeader("test");
//
//        source.registerCorsConfiguration("/**", config); // CORS 配置对所有接口都有效
//        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//        bean.setOrder(0);
//        return bean;
//    }


//    @Bean
//    public FilterRegistrationBean filterRegistrationBean1(){
//        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(MyHiddenHttpMethodFilter());
//        filterRegistrationBean.addUrlPatterns("/app/approval/*");
//        filterRegistrationBean.setOrder(6);//order的数值越小 则优先级越高
//        return filterRegistrationBean;
//    }
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean2(){
//        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(tokenAuthorFilter());
//        filterRegistrationBean.addUrlPatterns("/app/*");
//        filterRegistrationBean.setOrder(7);
//        return filterRegistrationBean;
//    }

}
