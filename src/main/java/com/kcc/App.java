package com.kcc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    private final static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("App 正在启动 ......");

        // java 命令启动
        // Terminal 终端
        // java -jar demo-1.0-SNAPSHOT.jar --server.port=1234
        // java -jar demo-1.0-SNAPSHOT.jar -Dspring.profiles.active=dev

        // SpringApplication.run(App.class, args);

        SpringApplication springApplication = new SpringApplication(App.class);
        //禁用命令行参数
        // java -jar demo-1.0-SNAPSHOT.jar --server.port=7788
        // --server.port=7788 参数无效
        springApplication.setAddCommandLineProperties(false);

        // 禁用 banner
        springApplication.setBannerMode(Banner.Mode.OFF);


        springApplication.run(args);
    }
}
