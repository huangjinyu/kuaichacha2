package com.hqz;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App4 {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App4.class, args);
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println("beanName = " + beanName);
        }

        //System.out.println(context.getBean("goodsService1"));
    }
}
