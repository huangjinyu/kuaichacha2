package com.kcc.conf;

import com.kcc.dao.GoodsDao;
import com.kcc.dao.impl.GoodsDaoImpl;
import com.kcc.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
//    @Value("${s1}")
//    private String str1;
//    @Value("${s2}")
//    private String str2;

    @Bean
    public GoodsServiceImpl goodsService(@Value("${s1}") String str1, @Value("${s2}") String str2, GoodsDao goodsDao) {
        System.out.println("GoodsServiceImpl goodsService str1 = " + str1);
        System.out.println("GoodsServiceImpl goodsService str2 = " + str2);


        GoodsServiceImpl goodsServiceImpl = new GoodsServiceImpl(goodsDao);

        System.out.println("GoodsService2");

        return goodsServiceImpl;
    }


//    @Bean
//    public GoodsServiceImpl goodsService() {
//        GoodsServiceImpl goodsServiceImpl = new GoodsServiceImpl(goodsDao());
//
//        System.out.println("GoodsService1");
//
//        return goodsServiceImpl;
//    }

    @Bean
    public GoodsDaoImpl goodsDao() {
        GoodsDaoImpl goodsDaoImpl = new GoodsDaoImpl();

        return goodsDaoImpl;
    }
}
