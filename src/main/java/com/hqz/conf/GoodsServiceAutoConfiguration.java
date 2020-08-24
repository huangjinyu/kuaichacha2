package com.hqz.conf;

import com.hqz.service.GoodsService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableConfigurationProperties(GoodsServiceProperties.class)
public class GoodsServiceAutoConfiguration {
    @Bean("goodsService2")
    public GoodsService goodsService2() {
        System.out.println("goodsService2.....................");
        GoodsService goodsService = new GoodsService();
        goodsService.setName("商品2");
        return goodsService;
    }

    @Primary
    @Bean("goodsService1")
    public GoodsService goodsService1() {
        System.out.println("goodsService1.....................");
        GoodsService goodsService = new GoodsService();
        goodsService.setName("商品1");
        return goodsService;
    }
}