package com.kcc.controller;

//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;

import com.kcc.App;
import com.kcc.pojo.AaaVO;
import com.kcc.pojo.Apple;
import com.kcc.pojo.Plate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {
    @RequestMapping("/list")
    public String list(HttpServletResponse response) throws IOException {
//        response.getOutputStream();

//        PrintWriter pw = response.getWriter();
//        pw.write(" hello ");
//        pw.write(" springboot ");
//        pw.flush();
//        pw.print(" learning ");
//            response.getWriter().close();
        return "order/list";
    }

    @RequestMapping("/test1")
    @ResponseBody
    public String test1(String s1) {
        return String.format("测试1 test1 s1 = %s", s1);
    }

    @RequestMapping("/test2")
    @ResponseBody
    public AaaVO test2(String str1, @RequestBody AaaVO aaaVO, Integer int1, AaaVO aaaVO2) {
//        AaaVO aaaVO = new AaaVO();
//        aaaVO.setStr1("字符串1");
//        aaaVO.setInt1(2020);

        log.info(str1);
        log.info(int1.toString());

        Plate<Apple> plate = new Plate<Apple>(new Apple());

        log.info(aaaVO.toString());

        log.info(aaaVO2.toString());

        return aaaVO;

        //MappingJackson2HttpMessageConverter
    }

    @RequestMapping("/test3")
    @ResponseBody
    public AaaVO test3(AaaVO aaaVO) {
        log.info(String.format("str1 = %s", aaaVO.getStr1()));
        log.info(String.format("int1 = %d", aaaVO.getInt1()));

        return aaaVO;
    }

    @RequestMapping("/test4")
    @ResponseBody
    public AaaVO test4(String str1, Integer int1) {
        log.info(String.format("str1 = %s", str1));
        log.info(String.format("int1 = %d", int1));

        AaaVO aaaVO = new AaaVO();
        aaaVO.setStr1(str1);
        aaaVO.setInt1(int1);

        return aaaVO;
    }

    @RequestMapping("/test5")
    @ResponseBody
    public String test5(String str1, Integer int1) {
        log.info(String.format("str1 = %s", str1));
        log.info(String.format("int1 = %d", int1));

        return "test5";
    }

    @RequestMapping("/test6")
    @ResponseBody
    public String test6() {
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");


//        for (String s : list1) {
//            log.info(s);
//        }

        testList(list1);

//        List<Integer> list2 = new ArrayList<>();
//        list2.add(1);
//        list2.add(2);
//        testList(list2);

        List<Plate<?>> applePlates = new ArrayList<>();

        testPlateList(applePlates);
//
//        Plate<Apple> applePlate = new Plate<>(new Apple());


        return "test6";
    }

    private void testPlateList(List<Plate<?>> list) {

    }

//    private void <T> testList(List<T> list) {
////        list.add("c");
//
////        for (String item : list) {
////            log.info(item.toString());
////        }
//    }

    private void testList(List<? extends String> list) {
//        list.add("c");

        for (String item : list) {
            log.info(item.toString());
        }
    }


}
