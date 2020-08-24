package com.kcc.service.impl;

import com.kcc.dao.GoodsDao;
import com.kcc.service.GoodsService;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

public class GoodsServiceImpl implements GoodsService {

    private final GoodsDao goodsDao;

    @Value("拼大圈")
    private String goodsName;

    public GoodsServiceImpl(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    public String getGoodsName() {
        return goodsName;
    }

    @PostConstruct
    public void init() {
        System.out.println("GoodsServiceImpl goodsName = " + goodsName);
    }

    @Override
    public String toString() {
        return "GoodsServiceImpl{" +
                "goodsDao=" + goodsDao +
                '}';
    }
}
