package com.mvcmasters.ems.external_integration.database;

import com.mvcmasters.ems.repository.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

/**
 * External Integration Test of PremissionMapper
 * with MySQL database.
 */
@SpringBootTest
public class PremissionRepositoryIntegrationTest {
    /**
     * Test for UserRoleMapper.
     */
    @Autowired
    private PermissionMapper permissionMapper;
    /**
     * Test for CountPermissionByRoleId method.
     */
    @Test
    public void testCountPermissionByRoleId() {
        Integer roleId = 1;
        Integer count = permissionMapper.countPermissionByRoleId(roleId);
        assertThat(count).isNotNull();
    }
    /**
     * Test for DeletePermissionByRoleId method.
     */
    @Test
    public void testDeletePermissionByRoleId() {
        Integer roleId = 1;
        permissionMapper.deletePermissionByRoleId(roleId);
        Integer countAfterDeletion = permissionMapper
                .countPermissionByRoleId(roleId);
        assertThat(countAfterDeletion).isEqualTo(0);
    }
    /**
     * Test for QueryRoleHasModuleIdsByRoleId method.
     */
    @Test
    public void testQueryRoleHasModuleIdsByRoleId() {
        Integer roleId = 1;
        List<Integer> moduleIds = permissionMapper
                .queryRoleHasModuleIdsByRoleId(roleId);
        assertThat(moduleIds).isNotNull();
    }
    /**
     * Test for QueryUserHasRoleHasPermissionByUserId method.
     */
    @Test
    public void testQueryUserHasRoleHasPermissionByUserId() {
        Integer userId = 1;
        List<String> permissions =
                permissionMapper.queryUserHasRoleHasPermissionByUserId(userId);
        assertThat(permissions).isNotNull();
    }
}
