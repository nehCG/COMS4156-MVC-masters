package com.mvcmasters.ems;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class EmsApplicationTests {
    @Test
    void mainApplicationStartTest() {
        assertDoesNotThrow(() -> EmsApplication.main(new String[] {}));
    }
    @Test
    void contextLoads() {
    }
}
