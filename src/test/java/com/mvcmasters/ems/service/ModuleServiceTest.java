package com.mvcmasters.ems.service;

import com.mvcmasters.ems.model.TreeModel;
import com.mvcmasters.ems.repository.ModuleMapper;
import com.mvcmasters.ems.repository.PermissionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ModuleServiceTest {

    /**
     * A mock of ModuleMapper to simulate the data access layer for modules.
     */
    @Mock
    private ModuleMapper moduleMapper;

    /**
     * A mock of PermissionMapper to simulate the
     * data access layer for permissions.
     */
    @Mock
    private PermissionMapper permissionMapper;

    /**
     * An instance of ModuleService with injected mocks.
     */
    @InjectMocks
    private ModuleService moduleService;

    /**
     * Creates a mocked list of TreeModel objects for testing.
     *
     * @return A list of mocked TreeModel objects.
     */
    private List<TreeModel> createMockedTreeModelList() {
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

        return Arrays.asList(module1, module2);
    }

    /**
     * Sets up the test environment before each test method.
     * Initializes Mockito annotations.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the {@code queryAllModules} method of {@link ModuleService}.
     * Ensures that the method returns the correct list
     * of modules with the appropriate checked status
     * based on the given role ID.
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
        // Role has permission for Module 1
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

    /**
     * Tests the queryAllModules method of
     * ModuleService with null permissions.
     * Verifies that all modules are unchecked when
     * no permissions are present for the role.
     */
    @Test
    public void testQueryAllModulesWithNullPermissions() {
        Integer roleId = 1;
        List<TreeModel> expectedModules = createMockedTreeModelList();

        when(moduleMapper.queryAllModules()).thenReturn(expectedModules);
        when(permissionMapper.
                queryRoleHasModuleIdsByRoleId(roleId)).thenReturn(null);

        List<TreeModel> actualModules = moduleService.queryAllModules(roleId);

        assertFalse(actualModules.stream().anyMatch(TreeModel::isChecked));
    }

    /**
     * Tests the queryAllModules method of ModuleService with empty permissions.
     * Verifies that all modules are unchecked when an empty list of
     * permissions is provided for the role.
     */
    @Test
    public void testQueryAllModulesWithEmptyPermissions() {
        Integer roleId = 1;
        List<TreeModel> expectedModules = createMockedTreeModelList();

        when(moduleMapper.queryAllModules()).thenReturn(expectedModules);
        when(permissionMapper.queryRoleHasModuleIdsByRoleId(roleId)).
                thenReturn(new ArrayList<>());

        List<TreeModel> actualModules = moduleService.queryAllModules(roleId);

        assertFalse(actualModules.stream().anyMatch(TreeModel::isChecked));
    }

    /**
     * Tests the queryAllModules method of ModuleService with valid permissions.
     * Verifies that the correct modules are checked based
     * on the provided permissions for the role.
     */
    @Test
    public void testQueryAllModulesWithPermissions() {
        Integer roleId = 1;
        List<TreeModel> expectedModules = createMockedTreeModelList();
        // Role has permission for Module 1
        List<Integer> permissionIds = Arrays.asList(1);

        when(moduleMapper.queryAllModules()).thenReturn(expectedModules);
        when(permissionMapper.queryRoleHasModuleIdsByRoleId(roleId)).
                thenReturn(permissionIds);

        List<TreeModel> actualModules = moduleService.queryAllModules(roleId);

        assertEquals(expectedModules.size(), actualModules.size());
        // Module 1 should be checked
        assertTrue(actualModules.get(0).isChecked());
        // Module 2 should not be checked
        assertFalse(actualModules.get(1).isChecked());
    }
}
