package com.mvcmasters.ems.external_integration.database;
import com.mvcmasters.ems.repository.RoleMapper;
import com.mvcmasters.ems.vo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Map;

/**
 * External Integration Test of RoleMapper
 * with MySQL database.
 */
@SpringBootTest
public class RoleRepositoryIntegrationTest {
    /**
     * Test for roleMapper.
     */
    @Autowired
    private RoleMapper roleMapper;
    /**
     * Test for QueryAllRoles method.
     */
    @Test
    public void testQueryAllRoles() {
        Integer userId = 1;
        List<Map<String, Object>> roles = roleMapper.queryAllRoles(userId);
        assertThat(roles).isNotNull();
        assertThat(roles).isNotEmpty();
    }
    /**
     * Test for SelectByRoleName method.
     */
    @Test
    public void testSelectByRoleName() {
        String roleName = "admin";
        Role role = roleMapper.selectByRoleName(roleName);
        assertThat(role).isNotNull();
        assertThat(role.getRoleName()).isEqualTo(roleName);
    }
}

