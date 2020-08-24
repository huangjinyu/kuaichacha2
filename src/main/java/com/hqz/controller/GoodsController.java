package com.hqz.controller;

import com.hqz.conf.GoodsServiceProperties;
import com.hqz.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {
//    private Logger log = LoggerFactory.getLogger(GoodsController.class);
    private final GoodsService goodsService;
    private final GoodsServiceProperties goodsServiceProperties;

    public GoodsController(GoodsService goodsService, GoodsServiceProperties goodsServiceProperties) {
        this.goodsService = goodsService;
        this.goodsServiceProperties = goodsServiceProperties;
    }

    @GetMapping("/list/{categoryId}")
    public String goodsList(@PathVariable("categoryId") Integer categoryId) {
        return "商品列表 categoryId = " + categoryId;
    }

    @GetMapping("/detail/{id}")
    public String goodsDetail(@PathVariable("id") Integer id) {
        log.info("@Slf4j日志");
        return "商品" + id + " ============= " + goodsService.toString() + " ============== " + goodsServiceProperties.toString();
    }
}