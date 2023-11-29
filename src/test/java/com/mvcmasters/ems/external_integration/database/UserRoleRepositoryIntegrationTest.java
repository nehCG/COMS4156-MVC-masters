package com.mvcmasters.ems.external_integration.database;

import com.mvcmasters.ems.repository.UserRoleMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * External Integration Test of UserRoleMapper
 * with MySQL database.
 */
@SpringBootTest
public class UserRoleRepositoryIntegrationTest {
    /**
     * Test for userRoleMapper.
     */
    @Autowired
    private UserRoleMapper userRoleMapper;
    /**
     * Test for CountUserRoleByUserId method.
     */
    @Test
    public void testCountUserRoleByUserId() {
        Integer userId = 1;
        Integer count = userRoleMapper.countUserRoleByUserId(userId);
        assertThat(count).isNotNull();
    }
    /**
     * Test for DeleteUserRoleByUserId method.
     */
    @Test
    public void testDeleteUserRoleByUserId() {
        Integer userId = 1;
        Integer initialCount = userRoleMapper.countUserRoleByUserId(userId);
        Integer deletedCount = userRoleMapper.deleteUserRoleByUserId(userId);
        Integer finalCount = userRoleMapper.countUserRoleByUserId(userId);

        assertThat(deletedCount).isEqualTo(initialCount);
        assertThat(finalCount).isEqualTo(0);
    }
}
