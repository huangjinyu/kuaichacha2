package com.kcc.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;

// @Data

@Getter
@Setter
//@ToString
//@Component
// @PropertySource(value = {"classpath:kuaichacha.properties"})
// 需要外部指定占位符 ${spring.profiles.active}
// java -jar xxx.jar --spring.profiles.active=dev
// application.properties 配置文件里配置 spring.profiles.active=dev
// VM Options:-Dspring.profiles.active=dev
// Environment variables:spring.profiles.active=dev
// Program arguments:--spring.profiles.active=dev
// @PropertySource(value = {"classpath:kuaichacha-${spring.profiles.active}.properties"})

@ConfigurationProperties(prefix = "kuaichacha.settings")
// @Component
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor

public class KuaichachaProperties2 implements BeanNameAware {
    public KuaichachaProperties2() {
        System.out.println("KuaichachaProperties2 构造函数 = " + this.hashCode());
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("KuaichachaProperties2 bean name = " + name);

        beanName = name;
    }

    //HttpEncodingAutoConfiguration
    // ServerProperties
    private String name;
    private String version;

    private String beanName;

}
