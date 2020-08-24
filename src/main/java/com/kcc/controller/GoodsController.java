package com.kcc.controller;

//import com.alibaba.fastjson.JSONArray;
import com.kcc.dao.GoodsDao;
import com.kcc.pojo.BrandVO;
import com.kcc.pojo.MyDataSource;
import com.kcc.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.SysexMessage;
import javax.sql.DataSource;
import java.util.Date;

//import org.springframework.jdbc.core.JdbcTemplate;


@RestController
@RequestMapping("/goods")
@PropertySource(value = "classpath:jdbc.properties")
@Order(1)
public class GoodsController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);


    public static String serverPort;
    private final GoodsService goodsService;
    private final GoodsDao goodsDao;
    private final MyDataSource myDataSource;
    @Autowired
    DataSource dataSource;
    //    @Autowired
//    JdbcTemplate jdbcTemplate;
    @Value("${kcc.env-name}")
    private String envName;
    @Value("${kcc.a1}")
    private String a1;
    @Value("${kcc.a2}")
    private String a2;
    @Value("#{kuaichachaProperties.name}")
    private String refBeanValue;
    @Value("#{1}")
    private Integer number; // 获取数字 1
    @Value("#{'abc'}") // 获取字符串常量
    private String str;
    @Value("#{dataSource.url}") // 获取bean的属性
    private String jdbcUrl;
    //    @Value("#{'${connect.timeout}'}")
//    private String otherConnectTimeout;
    @Value("${connect.timeout:HaHa}")
    private String connectTimeout;
    //    @Autowired
//    @Qualifier("kuaichachaProperties2")
//    private KuaichachaProperties kuaichachaProperties;
    @Autowired
    private Environment env;

    public GoodsController(GoodsService goodsService, GoodsDao goodsDao, MyDataSource myDataSource) {

        logger.info("GoodsController 被创建");

        this.goodsService = goodsService;
        this.goodsDao = goodsDao;
        this.myDataSource = myDataSource;
    }

    @Value("${connect.timeout:123}")
    public void setServerPort(String a1, String a2, String a3, Integer i1) {
        System.out.println(" serveraPort a1 = " + a1);
        System.out.println(" serveraPort a2 = " + a2);
        System.out.println(" serveraPort a3 = " + a3);
        System.out.println(" serveraPort i1 = " + i1);
        this.serverPort = a1;
    }

    /*API接口要求返回的格式是 application/json，我们知道网页返回的格式一般是 text/html，因此，Spring Boot为写接口，提供了两种实现方式: 类注解 和 方法注解。

    类注解 @RestController
    我们只需要在类上写上注解 @RestController，那么此Controller返回格式就都是text/json。如下图
————————————————
    版权声明: 本文为CSDN博主「冯文议」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
    原文链接: https://blog.csdn.net/qq_28336351/article/details/79327357*/

    @RequestMapping(value = "/list", produces = "text/json;charset=UTF-8")
    public String list() {
        logger.info("info list aaa");
        logger.debug("debug list aaa");
        logger.error("error list aaa");
        return "{\"name\": \"a\"}";
    }

    // @RequestMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    // @RequestMapping(value = "/update", produces = "application/json;charset=UTF-8")
    @RequestMapping(value = "/update")
    public String update(HttpServletResponse rsp) {
        // rsp.setHeader("Content-Type", "qwerasdf");

        // MediaType.APPLICATION_JSON


        logger.info(envName);

        //logger.info("web version: " + kuaichachaProperties.getVersion());

        return "{\"name\": \"你好\"}";
        // return "update";
    }

    @RequestMapping(value = "/delete")
//    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BrandVO delete(HttpServletResponse response) throws Exception {
        // System.gc();
        // System.gc();

//        logger.info("===============kuaichachaProperties: " + kuaichachaProperties.toString());
//
//        logger.info("kuaichacha.settings.version: " + kuaichachaProperties.getVersion());

        logger.info("当前环境: " + envName);

        logger.info("siteName: " + System.getProperty("siteName"));

        logger.info("siteName: " + env.getProperty("siteName"));


        // -DsiteName=拼多拼多

        logger.info("a1 = " + a1);
        logger.info("a2 = " + a2);

        logger.info(goodsService.toString());
        logger.info(goodsDao.toString());

        logger.info(myDataSource.toString());

        logger.warn("我是警告信息");

        logger.trace("我是跟踪信息");

        System.out.println("dataSourcea = " + dataSource);
        System.out.println("dataSourcea.getClass = " + dataSource.getClass());
        System.out.println("refBeanValue = " + refBeanValue);


        System.out.println(number);

        System.out.println(str);

        System.out.println(jdbcUrl);

        System.out.println(connectTimeout);

        System.out.println("env getProperty connect.timeout = " + env.getProperty("connect.timeout"));

        System.out.println(String.format("server port = %s", serverPort));

//        System.out.println(otherConnectTimeout);

//        Connection data = dataSourcea.getConnection();
//       System.out.println("------ dataSourcea " + dataSourcea.getClass());
//        System.out.println("------ data " + data.getClass());
//        com.zaxxer.hikari.HikariDataSource
//        logger.info(jdbcTemplate.toString());

//        jdbcTemplate.update("insert into [goods]([name],[money]) VALUES('a', '1')");
//        jdbcTemplate.update("insert into [goods]([name],[money]) VALUES('a', '2')");
//
//        Integer count = jdbcTemplate.queryForObject("select count(*) from goods", Integer.class);
//
//        System.out.println("总记录数：" + count);
//
//        jdbcTemplate.update("insert into [goods]([name],[money]) VALUES('a', a)");
//


        System.out.println(response.getClass());

//        org.apache.catalina.connector.ResponseFacade

//        response.getOutputStream().print("a");

//        response.getWriter().write(" c ");
//
//        response.reset();
//
//        response.getOutputStream().print(" b ");

//        response.getWriter().close();
//        response.getOutputStream().close();

//        response.getOutputStream().close();

//        response.getWriter().write(" d ");
//        response.getOutputStream().print(" e ");
//        response.getWriter().close();
//        pw.print(" hello ");

        // response.getWriter().write("你好");

//        response.getOutputStream().print("abcdefg12345678");

        // response.getWriter().print("abcdefg12345678");

//        response.reset();


//        com.alibaba.fastjson.JSONArray.toJSONString()

        String result = "exist";

        BrandVO vo = new BrandVO();
//        BrandVO vo = BrandVO.builder().name("拼大圈").build();

//        vo.setName("你好");
        vo.setDesc("哈哈");
        vo.setCreatedDate(new Date());

//        System.out.println(JSONArray.toJSONString(result));

        return vo;

//        return JSONArray.toJSONString(result);
    }
}
