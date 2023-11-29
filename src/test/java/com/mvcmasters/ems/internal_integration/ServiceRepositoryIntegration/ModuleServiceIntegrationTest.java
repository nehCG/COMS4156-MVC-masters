package com.mvcmasters.ems.internal_integration.ServiceRepositoryIntegration;

import com.mvcmasters.ems.repository.ModuleMapper;
import com.mvcmasters.ems.model.TreeModel;
import com.mvcmasters.ems.repository.PermissionMapper;
import com.mvcmasters.ems.service.ModuleService;
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
public class ModuleServiceIntegrationTest {
    /**
     * ModuleService to be tested.
     */
    @Autowired
    private ModuleService moduleService;
    /**
     * Isolating external database using MockBean.
     */
    @MockBean
    private ModuleMapper moduleMapper;
    /**
     * Isolating external database using MockBean.
     */
    @MockBean
    private PermissionMapper permissionMapper;
    /**
     * Test for QueryAllModules method.
     */
    @Test
    public void testQueryAllModules() {
        Integer roleId = 1;
        TreeModel module1 = new TreeModel();
        module1.setId(1);
        module1.setpId(0);
        module1.setName("Module 1");
        module1.setChecked(false);

        TreeModel module2 = new TreeModel();
        module2.setId(2);
        module2.setpId(0);
        module2.setName("Module 2");
        module2.setChecked(false);

        List<TreeModel> expectedModules = Arrays.asList(module1, module2);
        List<Integer> permissionIds = Arrays.asList(1);

        when(moduleMapper.queryAllModules()).thenReturn(expectedModules);
        when(permissionMapper.
                queryRoleHasModuleIdsByRoleId(roleId)).
                thenReturn(permissionIds);

        List<TreeModel> actualModules = moduleService.queryAllModules(roleId);

        assertEquals(2, actualModules.size());
        assertEquals(true, actualModules.get(0).isChecked());
        assertEquals(false, actualModules.get(1).isChecked());
    }
}

