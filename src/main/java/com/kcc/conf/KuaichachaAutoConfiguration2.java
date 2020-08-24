package com.kcc.conf;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KuaichachaProperties2.class)
public class KuaichachaAutoConfiguration2 {
//    @Bean
//    //@Bean
//    public KuaichachaProperties kuaichachaProperties() {
//        KuaichachaProperties kuaichachaProperties = new KuaichachaProperties();
//
//        System.out.println("KuaichachaAutoConfiguration2 kuaichachaProperties() = " + kuaichachaProperties);
//
//        return kuaichachaProperties;
//    }

//    @Bean("kuaichachaProperties2")
//    //@Bean
//    public KuaichachaProperties getkuaichachaProperties() {
//        KuaichachaProperties kuaichachaProperties = new KuaichachaProperties();
//
//        kuaichachaProperties.setName("aaa");
//        kuaichachaProperties.setVersion("bbb");
//
//        System.out.println("KuaichachaAutoConfiguration2 getkuaichachaProperties() = " + kuaichachaProperties);
//
//        return kuaichachaProperties;
//    }
}