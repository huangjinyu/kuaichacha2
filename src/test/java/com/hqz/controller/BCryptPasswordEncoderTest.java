package com.hqz.controller;

import com.kcc.App2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest(classes = App2.class)
public class BCryptPasswordEncoderTest {
    @Test
    public void testPassword() {
        String password = BCrypt.hashpw("111111", BCrypt.gensalt());

        Boolean checkpw = BCrypt.checkpw("111111", "$2a$10$rIvZenQTLvNTaA5KLnIoqeGtdbxJ9D3Rgx0S82GA51vJV8TNmE6ny");

        Boolean checkpw2 = BCrypt.checkpw("111111", "$2a$10$pp6VmD3nVmWJyriSEgd4kewOgR6JJkWj4UPum2MPT8g0p4869XPfO");

        System.out.println("111111 = " + password);

        System.out.println(checkpw);
        System.out.println(checkpw2);
    }

    @Test
    public void testPassword2() {
        String str = "admin";
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();

        // 加密
        String hashStr = bcryptPasswordEncoder.encode(str);

        System.out.println(hashStr);

        // 密码匹配
        boolean f = bcryptPasswordEncoder.matches("admin", hashStr);

        System.out.println(f);
    }
}
