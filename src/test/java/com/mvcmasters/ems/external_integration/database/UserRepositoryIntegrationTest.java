package com.mvcmasters.ems.external_integration.database;

import com.mvcmasters.ems.repository.UserMapper;
import com.mvcmasters.ems.vo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * External Integration Test of UserMapper
 * with MySQL database.
 */
@SpringBootTest
public class UserRepositoryIntegrationTest {
    /**
     * Test for userMapper.
     */
    @Autowired
    private UserMapper userMapper;
    /**
     * Test of QueryUserByName method.
     */
    @Test
    public void testQueryUserByName() {
        String userName = "admin";
        User result = userMapper.queryUserByName(userName);
        assertThat(result).isNotNull();
        assertThat(result.getUserName()).isEqualTo(userName);
    }
}
