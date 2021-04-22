package com.eqshen.func;

import com.eqshen.func.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class MultiFunctionTest {
    @Autowired
    private TestService testService;

    @Test
    void contextLoads() {
    }

    @Test
    void testWhitelist() {
        System.out.println(testService.getUserInfo("1007","tom"));
        System.out.println(testService.getUserInfo("1008","tom"));
    }

    @Test
    void testRateLimit() throws InterruptedException {
        for (int i = 0; i < 30 ; i++) {
            System.out.println(testService.getUserInfo("1007","tom"));
            TimeUnit.MILLISECONDS.sleep(300);
        }
    }

}
