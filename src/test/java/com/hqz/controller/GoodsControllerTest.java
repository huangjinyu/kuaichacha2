package com.hqz.controller;

import com.hqz.App4;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

// 如果不指定classes，有多@SpringBootApplication，会出错
@SpringBootTest(classes = {App4.class})
public class GoodsControllerTest {
//    @Test
//    void contextLoads() {
//        System.out.println("a");
//        Assertions.assertEquals("a", "a");
//    }


    @Test
    void testGoodsDetail() {
        // Assertions.
        List<String> list = new ArrayList<>();

        list.add("拼多拼多");
        list.add("美好日记");

        list.forEach(str -> {
            String a = "a";
            System.out.println(a);

        });

        System.out.println("testGoodsDetail");
    }

    @Test
    void testGoodsList() {
        System.out.println("testGoodsList");
    }
}
