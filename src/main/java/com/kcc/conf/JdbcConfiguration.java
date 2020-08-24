package com.kcc.conf;

import com.kcc.pojo.AaaVO;
import com.kcc.pojo.BbbVO;
import com.kcc.pojo.MyDataSource;
import com.kcc.service.impl.BrandServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@EnableConfigurationProperties(JdbcProperties.class)

@Slf4j
public class JdbcConfiguration {

    @Bean
    public AaaVO getAaaaVO() {
        return new AaaVO();
    }

    @Bean
    public BbbVO getBbbVO() {
        return new BbbVO();
    }

    @Bean
//    @ConditionalOnMissingClass("com.kcc.App")
    //@ConditionalOnClass(name = "com.kcc.App")
//    @ConditionalOnMissingBean(BrandServiceImpl.class)
//    @ConditionalOnMissingBean()
    @ConditionalOnClass(BrandServiceImpl.class)
    public MyDataSource myDataSource2() {
//        WebMvcConfigurationSupport
        log.info("创建 JdbcConfiguration myDataSource2 bean");

//        KfakaTemplate.class

        MyDataSource myDataSource = new MyDataSource();
        return new MyDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "jdbc")
    public MyDataSource myDataSource() {
        log.info("创建 JdbcConfiguration myDataSource bean");

        MyDataSource myDataSource = new MyDataSource();
        myDataSource.setPassword("A");
        return myDataSource;
    }

//    @Bean
//    public MyDataSource dataSource(JdbcProperties jdbcProperties) {
//        MyDataSource dataSource = new MyDataSource();
//        dataSource.setDriverClassName(jdbcProperties.getDriverClassName());
//        dataSource.setUrl(jdbcProperties.getUrl());
//        dataSource.setUsername(jdbcProperties.getUsername());
//        dataSource.setPassword(jdbcProperties.getPassword());
//        return dataSource;
//    }

//    @Bean
//    public DataSource dataSource(JdbcProperties jdbcProperties) {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(jdbcProperties.getDriverClassName());
//        dataSource.setUrl(jdbcProperties.getUrl());
//        dataSource.setUsername(jdbcProperties.getUsername());
//        dataSource.setPassword(jdbcProperties.getPassword());
//        return dataSource;
//    }

//    @Bean
//    @ConfigurationProperties(prefix = "jdbc")
//    public DataSource dataSource() {
//        return new DruidDataSource();
//    }
}
