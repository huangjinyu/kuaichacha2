package com.kcc.conf;

//import com.alibaba.druid.pool.DruidDataSource;

//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;

@Configuration
//@Order(-10000)
public class DataSourceConfiguration {
//    @Bean
//    @ConfigurationProperties("spring.datasource.one")
//    public DataSource dataSourceOne() {
//        return DataSourceBuilder.create().build();
//    }

//    @Bean
//    @ConfigurationProperties("spring.datasource.two")
//    public DataSource dataSourceTwo() {
//        return DataSourceBuilder.create().build();
//    }

//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource druidDataSource() {
//        return new DruidDataSource();
//    }

//    @ConfigurationProperties(prefix = "spring.datasource")
//    @Bean
//    public DataSource druid() {
//        return new DruidDataSource();
//    }

//    @Bean("dataSource")
//    @Profile("testa")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource testDataSource() {
//
//        HikariConfig hc = new HikariConfig();
//        hc.setJdbcUrl("aa");
//        new HikariDataSource(hc);
//
//        HikariDataSource ds = new HikariDataSource();
//        ds.setJdbcUrl("jdbc:sqlserver://localhost:1433;DatabaseName=biaoa");
////        com.zaxxer.hikari.HikariDataSource
//        //HikariDataSource
//        return ds;
//    }
//
//    @Bean("dataSource")
//    @Profile("proda")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource prodDataSource() {
//        HikariDataSource ds = new HikariDataSource();
//        ds.setJdbcUrl("jdbc:sqlserver://localhost:1433;DatabaseName=biaoa");
//        return ds;
//    }
}
