package com.kcc.conf;

//import org.springframework.boot.context.properties.EnableConfigurationProperties;

//import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.annotation.PropertySource;

@Configuration
//@ConfigurationProperties(prefix = "kuaichacha.settings")
//@EnableConfigurationProperties({KuaichachaProperties.class})
//@PropertySource(value = {"classpath:kuaichacha.properties"})
//@PropertySource(value = {"classpath:kuaichacha.properties", "classpath:pinduopinduo.properties"})
//@ConditionalOnClass(Aaa.class)
//@ConditionalOnMissingClass("Aaa.class")
public class KuaichachaAutoConfiguration {
    @Bean
    // 会覆盖 KuaichachaProperties 类上定义的@ConfigurationProperties注解
    @ConfigurationProperties(prefix = "kuaichacha.settings")
    public KuaichachaProperties kuaichachaProperties() {

        KuaichachaProperties kuaichachaProperties = new KuaichachaProperties();

//        kuaichachaProperties.setName("aaa");
//        kuaichachaProperties.setVersion("bbb");

        return kuaichachaProperties;
        // return new KuaichachaProperties();
    }

//    @Bean
//    @Profile("dev")
//    public Brand brand() {
//        return new Brand();
//    }

//    @Bean
//    @Profile("prod")
//    public Brand brand() {
//        return new Brand();
//    }
}