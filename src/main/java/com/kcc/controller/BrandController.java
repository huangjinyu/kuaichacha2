package com.kcc.controller;

import com.kcc.conf.KuaichachaProperties;
import com.kcc.pojo.BrandVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/brand")
@Order(2)
//@EnableConfigurationProperties({KuaichachaProperties.class})
public class BrandController {
    @Autowired
    private  GoodsController goodsController;

    @Autowired
    //@Qualifier("kuaichachaProperties2")
    private final KuaichachaProperties kuaichachaProperties;
    private Logger logger = LoggerFactory.getLogger(BrandController.class);

    public BrandController(KuaichachaProperties kuaichachaProperties) {
        logger.info("BrandController 被创建");
        this.kuaichachaProperties = kuaichachaProperties;
    }

    //    @RequestMapping(value = "/list", produces = "text/html")
//    @RequestMapping(value = "/list", produces = "text/plain")
    @RequestMapping(value = "/list")
    public BrandVO list() {
        logger.info("kuaichachaProperties: " + kuaichachaProperties);

        BrandVO brandVO = new BrandVO();
//        BrandVO brandVO = BrandVO.builder().build();

        brandVO.setName("美好日记");
        brandVO.setDesc("美好日记记录你的每一天");

        System.out.println(GoodsController.serverPort);

        return brandVO;
//        return "<a href='#'>list</a>";
    }

    @RequestMapping(value = "/list2", consumes = "a/b", produces = "application/json")
    public String list2(@ModelAttribute String name) {

        return name;
    }
}
