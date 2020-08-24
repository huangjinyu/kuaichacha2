package com.kcc;

import com.kcc.conf.JdbcConfiguration;
import com.kcc.pojo.MyDataSource;
import com.kcc.service.impl.BrandServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.ImageBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan({"com.hqz.service", "com.kcc", "com.thh"})
//@PropertySource(value = {"classpath:kuaichacha.properties"})
//@EnableConfigurationProperties(KuaichachaProperties.class)
@Slf4j
//@Import(BrandServiceImpl.class)
@EnableCaching
public class App2 {
    // private final static Logger logger = LoggerFactory.getLogger(App2.class);

//    @Bean
//    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
//
//        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
//            //嵌入式的Servlet容器相关的规则
//            @Override
//            public void customize(ConfigurableWebServerFactory factory) {
//                factory.setPort(8088);
//                factory.ses
//            }
//        };
//    }

    public static void main(String[] args) {

//        logger.info("App2 正在启动 ......");
//
//        logger.info("===================================================");
//        logger.info("args 参数：");
//        for (String s : args) {
//            logger.info(s);
//        }
//        logger.info("===================================================");
//
////        Properties ps = System.getProperties();
////
////        System.out.println(ps);
//
//        System.out.println(System.getProperty("server.port"));

        // HaoquanziConfiguration j = new HaoquanziConfiguration();

//        System.out.println("\033[32m test ");
        //springApplication.run(App2.class, args);

        SpringApplication springApplication = new SpringApplication(App2.class);
        // springApplication.setRegisterShutdownHook();
        // springApplication.getClass()
        // springApplication.setDefaultProperties();

//        Banner imageBanner = new ImageBanner(new ClassPathResource("banner1.gif"));
//
//        springApplication.setBanner(imageBanner);

        ConfigurableApplicationContext context = springApplication.run(args);

//        KuaichachaProperties2 kuaichachaProperties2 = context.getBean(KuaichachaProperties2.class);

        String[] beanNames = context.getBeanDefinitionNames();

//        System.out.println("============================================");

        for (String beanName : beanNames) {
            //System.out.println("beanName = " + beanName);
            log.info("beanName = " + beanName);
        }


//        System.out.println("============================================");
//
//        System.out.println("KuaichachaProperties2 toString() = " + kuaichachaProperties2.toString());
//
//        System.out.println("KuaichachaProperties2 hashCode() = " + Integer.toHexString(kuaichachaProperties2.hashCode()));
//
//        System.out.println("KuaichachaProperties2 beanName = " + kuaichachaProperties2.getBeanName());


//        springApplication.setBanner(new Banner() {
//            @Override
//            public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
//
//            }
//        });


    }
}
