package com.kcc.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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

//@Configuration
//@ConfigurationProperties(prefix = "kuaichacha.settings")
//@ConfigurationProperties(prefix = KuaichachaProperties.PREFIX)

//@Component
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@EnableConfigurationProperties({KuaichachaProperties.class})
public class KuaichachaProperties implements BeanNameAware {

    public static final String PREFIX = "kuaichacha.settings";
    //HttpEncodingAutoConfiguration
    // ServerProperties
    private String name;
    private String version;

    public KuaichachaProperties() {
        System.out.println("KuaichachaProperties 正在被创建... " + this);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("KuaichachaProperties bean name = " + name);
    }

    @Override
    public String toString() {
        return "KuaichachaProperties{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
