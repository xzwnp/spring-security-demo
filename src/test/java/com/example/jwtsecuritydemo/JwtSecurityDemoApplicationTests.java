package com.example.jwtsecuritydemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


class JwtSecurityDemoApplicationTests {

    @Test
    void contextLoads() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("root"));
        System.out.println(bCryptPasswordEncoder.encode("user"));
        System.out.println(bCryptPasswordEncoder.encode("vip"));

    }

}
