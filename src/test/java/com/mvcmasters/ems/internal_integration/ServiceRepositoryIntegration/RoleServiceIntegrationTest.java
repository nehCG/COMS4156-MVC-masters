package com.mvcmasters.ems.internal_integration.ServiceRepositoryIntegration;

import com.mvcmasters.ems.repository.ModuleMapper;
import com.mvcmasters.ems.repository.PermissionMapper;
import com.mvcmasters.ems.repository.RoleMapper;
import com.mvcmasters.ems.service.RoleService;
import com.mvcmasters.ems.vo.Module;
import com.mvcmasters.ems.vo.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RoleServiceIntegrationTest {
    /**
     * ModuleService to be tested.
     */
    @Autowired
    private RoleService roleService;
    /**
     * Isolating external database using MockBean.
     */
    @MockBean
    private RoleMapper roleMapper;
    /**
     * Isolating external database using MockBean.
     */
    @MockBean
    private PermissionMapper permissionMapper;
    /**
     * Isolating external database using MockBean.
     */
    @MockBean
    private ModuleMapper moduleMapper;
    /**
     * Test for QueryAllRoles method.
     */
    @Test
    public void testQueryAllRoles() {
        Integer userId = 1;
        List<Map<String, Object>> expectedRoles = new ArrayList<>();
        when(roleMapper.queryAllRoles(userId)).thenReturn(expectedRoles);
        List<Map<String, Object>> roles = roleService.queryAllRoles(userId);
        assertEquals(expectedRoles, roles);
        verify(roleMapper).queryAllRoles(userId);
    }
    /**
     * Test for AddRole method.
     */
    @Test
    public void testAddRole() {
        Role newRole = new Role();
        newRole.setRoleName("Test Role");
        when(roleMapper
                .selectByRoleName(newRole.getRoleName())).thenReturn(null);
        when(roleMapper.insertSelective(newRole)).thenReturn(1);
    }
    /**
     * Test for UpdateRole method.
     */
    @Test
    public void testUpdateRole() {
        Role updtaeRole = new Role();
        updtaeRole.setId(1);
        updtaeRole.setRoleName("Updated role name");
        when(roleMapper
                .selectByPrimaryKey(updtaeRole.getId())).thenReturn(updtaeRole);
        when(roleMapper
                .selectByRoleName(updtaeRole.getRoleName())).thenReturn(null);
        when(roleMapper
                .updateByPrimaryKeySelective(updtaeRole)).thenReturn(1);
    }
    /**
     * Test for DeleteRole method.
     */
    @Test
    public void testDeleteRole() {
        Integer roleId = 1;
        Role role = new Role();
        role.setId(roleId);
        when(roleMapper.selectByPrimaryKey(roleId)).thenReturn(role);
        when(roleMapper.updateByPrimaryKeySelective(role)).thenReturn(1);
    }
    /**
     * Test for AddGrant method.
     */
    @Test
    public void testAddGrant() {
        Integer roleId = 1;
        Integer[] moduleIds = {1, 2};
        when(permissionMapper.countPermissionByRoleId(roleId)).thenReturn(0);
        for (Integer mId : moduleIds) {
            Module module = new Module();
            module.setId(mId);
            module.setOptValue("Test Value");
            when(moduleMapper.selectByPrimaryKey(mId)).thenReturn(module);
        }
        when(permissionMapper
                .insertBatch(anyList())).thenReturn(moduleIds.length);
    }
}
