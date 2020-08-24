package com.kcc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kcc.core.Result;
import com.kcc.core.exception.BusinessException;
import com.kcc.core.security.MyUserDetails;
import com.kcc.core.security.MyUserDetailsService2;
import com.kcc.pojo.vo.Test1VO;
import com.kcc.pojo.vo.TestInclude1VO;
import com.kcc.pojo.vo.TestInclude2VO;
import com.kcc.service.HelloService;
import com.kcc.service.impl.HelloServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.env.RandomValuePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.UUID;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private HelloService helloService;
    @Value("${myrandom.value}")
    private String myrandom_value;
    @Value("${random.value}")
    private String random_value;
    @Value("${random.int}")
    private int random_int;
    @Value("${random.long}")
    private String random_long;
    @Value("${random.int(10)}")
    private int random_int_10;
    @Value("${random.int[10,20]}")
    private int random_int_10_20;
    @Autowired
    private Environment env;

    @GetMapping("/index")
    public String index(HttpServletResponse resp) {

        MyUserDetailsService2 myUserDetailsServiceImpl = new MyUserDetailsService2();

        System.out.println(myUserDetailsServiceImpl.loadUserByUsername("aaaaaa"));

        String result = "";

        Cookie helloCookie = new Cookie("hello", "world");
        resp.addCookie(helloCookie);

        Cookie usernameCookie = new Cookie("username", "aaaaaa");
        resp.addCookie(usernameCookie);

        Cookie passwordCookie = new Cookie("password", "111111");
        resp.addCookie(passwordCookie);

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
            UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            result = "hello " + ud.getUsername();

            System.out.println(ud.getUsername());
        }

        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return result;
    }

    @GetMapping("/test1")
    public String test1(HttpServletRequest req) {
        Enumeration<String> sessionNames = req.getSession().getAttributeNames();

        while (sessionNames.hasMoreElements()) {
            System.out.println("session key = " + sessionNames.nextElement());
        }

        req.getSession().setAttribute("test", "测试数据");
//        session.setAttribute("test", "测试数据");
        return "test1";
    }

    @GetMapping("/test2")
    public String test2(HttpServletRequest req) {

        Object test = req.getSession().getAttribute("test");

        if (test == null) {
            return "session 为空";
        } else {
            return (String) test;
        }
    }

    @RequestMapping("/test3")
    public String test3() {
        return "test3";
    }

    @RequestMapping("/test4")
    public String test4() {
        return "test4";
    }

    /**
     * 需要 ADMIN 角色 才能访问
     *
     * @return
     */
    @RequestMapping("/test5")
    public String test5() {
        return "test5";
    }

    @RequestMapping("/test6")
    @PreAuthorize("hasRole('ADMIN')")
    public String test6() {
//        fun1();
        return "test6";
    }

    @RequestMapping("/test7")
    public String test7() {
        return "test7";
    }

    @RequestMapping("/test8")
    @PreAuthorize("hasAuthorize('delete')")
    public String test8() {
        return "test8";
    }

    @RequestMapping(value = "/test9", produces = "text/html;charset=UTF-8")
    public void test9(HttpServletResponse response) throws IOException {
        response.setStatus(401);
        response.setContentType("text/html;charset=UTF-8");
//        response.setHeader("Content-type","text/html;charset=utf-8");
        response.setHeader("WWW-Authenticate", "Basic realm=\"\"");
        response.getWriter().print("对不起您没有权限！！");
        response.getWriter().close();
    }

    /**
     * 测试异常，跳转
     *
     * @return
     */
    @RequestMapping("/test10")
    public String test10() {
        int i = 100 / 0;
        return "test10";
    }

    @GetMapping("/write")
    @PreAuthorize("hasAuthority('write')")
    public String getWrite() {
        return "have a write authority";
    }

    @GetMapping("/read")
    @PreAuthorize("hasAuthority('read')")
    public String readDate() {
        return "have a read authority";
    }

    @GetMapping("/read-or-write")
    @PreAuthorize("hasAnyAuthority('read','write')")
    public String readWriteDate() {
        return "have a read or write authority";
    }

    @GetMapping("/admin-role")
    @PreAuthorize("hasRole('ADMIN')")
    public String readAdmin() {
        return "have a admin role";
    }

    @GetMapping("/user-role")
    @PreAuthorize("hasRole('USER')")
    public String readUser() {
        return "have a user role";
    }

    /**
     * 获取认证信息
     *
     * @param authentication
     * @return
     */
    @RequestMapping("test11")
    public String test11(Authentication authentication) {
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        System.out.println(userDetails.getNickname());
        System.out.println(userDetails.getUsername());

        return String.format("我是属性，用户名：%s， === 我是自定义属性，昵称：%s", userDetails.getUsername(), userDetails.getNickname());
    }

    /**
     * 获取认证信息
     *
     * @return
     */
    @RequestMapping("test12")
    public String test12() {
        String nickname;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof MyUserDetails) {
            nickname = "昵称：" + ((MyUserDetails) principal).getNickname();
        } else if (principal instanceof UserDetails) {
            nickname = "用户名：" + ((UserDetails) principal).getUsername();
        } else {
            nickname = principal.toString();
        }

        return nickname;
    }

    /**
     * 方法认证
     *
     * @return
     */
    @RequestMapping("/test13")
    public String test13() {
//        HelloServiceImpl hsi = new HelloServiceImpl();

//        helloService.test();

        helloService.test1();
        helloService.test2();

        return "test13";
    }

    @RequestMapping("/test14")
    public String test14() {
        HelloServiceImpl helloService = new HelloServiceImpl();

        helloService.test1();
        helloService.test2();

        return "test14";
    }

    //    @PreAuthorize("hasAuthority('write')")
//    @PreAuthorize("hasRole('ROLE_write')")
    @PreAuthorize("hasAuthority('write')")
    public String testPreAuthorize() {
        return "testPreAuthorize";
    }

    public String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        if (principal instanceof Principal) {
            return ((Principal) principal).getName();
        }

        return String.valueOf(principal);
    }

    public String getCurrentUsername2() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public String test15() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        }

        return "test15";
    }

    /**
     * 测试 随机
     *
     * @return
     */
    @RequestMapping("/test16")
    public String test16() {

        RandomValuePropertySource random = new RandomValuePropertySource("myrandom");
        // 随机生成一个整数
        //log.info("random int:{}", random.getProperty("random.int"));

        System.out.println("@Value 注解注入 myrandom_value = " + myrandom_value);
        System.out.println("@Value 注解注入 random_value = " + random_value);
        System.out.println("@Value 注解注入 random_int_10_20 = " + random_int_10_20);


        System.out.println("RandomStringUtils.randomNumeric(6) = " + RandomStringUtils.randomNumeric(6));
        System.out.println("UUID.randomUUID().toString() = " + UUID.randomUUID().toString());
        return "test16";
    }

    @RequestMapping("/test17")
    public String test17() {
        //替换字符串的中间字符
        String strs1 = "yanshao@gamil.com";
        System.out.println("abbreviateMiddle>>>>" + org.apache.commons.lang3.StringUtils.abbreviateMiddle(strs1, "****", 10));

        System.out.println("StringUtils.isNumeric(\"asdf\") = " + org.apache.commons.lang3.StringUtils.isNumeric("asdf"));
        System.out.println("StringUtils.hasText(null) = " + StringUtils.hasText(null));
        return "test17";
    }

    @RequestMapping("/test18")
    public String test18() {
        System.out.println("env.getProperty(\"myrandom.value\") = " + env.getProperty("myrandom.value"));
        return "test18";
    }


    @RequestMapping("/test19")
    public String test19() {
        int a = 1 / 0;
        return "test19";
    }

    @RequestMapping("/test20")
    public Result test20() {
        return Result.success();
    }

    @RequestMapping("/test21")
    public Result test21() {
        if (true) {
            throw new BusinessException("业务逻辑异常");
        }

        return Result.success();
    }

    @JsonView(Test1VO.IDView.class)
    @RequestMapping("/test22")
    public Test1VO test22() {
        Test1VO user = new Test1VO();
        user.setID(1);
        user.setName("好圈子");
        return user;
    }

    @JsonView(Test1VO.IDAndNameView.class)
    @RequestMapping("/test23")
    public Test1VO test23() {
        Test1VO user = new Test1VO();
        user.setID(1);
        user.setName("好圈子");
        return user;
    }

    @JsonView(Test1VO.AView.class)
    @RequestMapping("/test24")
    public Test1VO test24() {
        Test1VO user = new Test1VO();
        user.setID(1);
        user.setName("好圈子");
        user.setAge(20);
        user.setCity("浙江温州");
        user.setAddress("乐清");
        return user;
    }

    @JsonView(Test1VO.BView.class)
    @RequestMapping("/test25")
    public Test1VO test25() {
        Test1VO user = new Test1VO();
        user.setID(2);
        user.setName("快查查");
        user.setAge(26);
        user.setCity("浙江温州");
        user.setAddress("瑞安");
        return user;
    }

    @JsonView(TestInclude1VO.class)
    @RequestMapping("/test26")
    public Test1VO test26() {
        Test1VO user = new Test1VO();
        user.setID(3);
        user.setName("建筑查");
        user.setAge(30);
        user.setCity("浙江温州");
        user.setAddress("乐清");
        return user;
    }

    @JsonView(TestInclude2VO.class)
    @RequestMapping("/test27")
    public Test1VO test27() {
        Test1VO user = new Test1VO();
        user.setID(3);
        user.setName("建筑查");
        user.setAge(30);
        user.setCity("浙江温州");
        user.setAddress("乐清");
        return user;
    }
}
