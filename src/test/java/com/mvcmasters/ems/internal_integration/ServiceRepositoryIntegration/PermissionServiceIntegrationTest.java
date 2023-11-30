package com.mvcmasters.ems.internal_integration.ServiceRepositoryIntegration;
import com.mvcmasters.ems.repository.PermissionMapper;
import com.mvcmasters.ems.service.PermissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test Integration between ModuleService and
 * ModuleMapper.
 */
@SpringBootTest
public class PermissionServiceIntegrationTest {
    /**
     * ModuleService to be tested.
     */
    @Autowired
    private PermissionService permissionService;
    /**
     * Isolating external database using MockBean.
     */
    @MockBean
    private PermissionMapper permissionMapper;
    /**
     * test queryUserHasRoleHasPermissionByUserId method.
     */
    @Test
    public void testQueryUserHasRoleHasPermissionByUserId() {
        Integer userId = 1;
        List<String> expectedPermissions =
                Arrays.asList("PERMISSION_1", "PERMISSION_2");
        when(permissionMapper.
                queryUserHasRoleHasPermissionByUserId(userId)).
                thenReturn(expectedPermissions);
        List<String> actualPermissions =
                permissionService.queryUserHasRoleHasPermissionByUserId(userId);
        assertEquals(expectedPermissions, actualPermissions);
    }

}
