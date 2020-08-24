package com.kcc.service.impl;

import com.kcc.dao.GoodsDao;
import com.kcc.service.GoodsService;

public class HotGoodsServiceImpl implements GoodsService {
    private final GoodsDao goodsDao;

    public HotGoodsServiceImpl(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Override
    public String toString() {
        return "HotGoodsServiceImpl{" +
                "goodsDao=" + goodsDao +
                '}';
    }
}
