package com.eqshen.func;

import com.eqshen.func.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@SpringBootTest
class MultiFunctionSpringBootStarterApplicationTests {
    @Autowired
    private TestService testService;

    @Test
    void contextLoads() {
        System.out.println(testService.getUserInfo("10001"));
    }

}
