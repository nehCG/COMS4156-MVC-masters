package com.mvcmasters.ems.service;

import com.mvcmasters.ems.exceptions.ParamsException;
import com.mvcmasters.ems.repository.RoleMapper;
import com.mvcmasters.ems.vo.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class RoleServiceTest {

    /**
     * A mock of the role access object.
     */
    @Mock
    private RoleMapper roleMapper;

    /**
     * The service being tested, with dependencies mocked.
     */
    @InjectMocks
    private RoleService roleService;

    /**
     * Set up mockito annotations for each test.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for querying all roles associated with a user.
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
     * Test case for adding a new role.
     */
    @Test
    public void testAddRole() {
        Role newRole = new Role();
        newRole.setRoleName("New Role");

        when(roleMapper.selectByRoleName(newRole.getRoleName())).
                thenReturn(null);
        when(roleMapper.insertSelective(newRole)).thenReturn(1);

        roleService.addRole(newRole);

        verify(roleMapper).insertSelective(newRole);
    }

    /**
     * Test case for adding a new role when the role name already exists.
     */
    @Test
    public void testAddRoleFailureDueToExistingName() {
        Role newRole = new Role();
        newRole.setRoleName("Existing Role");

        // Simulate the role name already exists
        when(roleMapper.selectByRoleName(newRole.getRoleName())).
                thenReturn(new Role());

        Exception exception = assertThrows(ParamsException.class, () -> {
            roleService.addRole(newRole);
        });

        assertEquals("Role name already exist, please try another one.",
                exception.getMessage());
    }

    /**
     * Test case for adding a new role successfully.
     */
    @Test
    public void testAddRoleSuccess() {
        Role newRole = new Role();
        newRole.setRoleName("New Role");

        // Simulate the role name does not exist
        when(roleMapper.selectByRoleName(newRole.getRoleName())).
                thenReturn(null);
        when(roleMapper.insertSelective(newRole)).thenReturn(1);

        roleService.addRole(newRole);

        verify(roleMapper).insertSelective(newRole);
    }

    /**
     * Test case for adding a new role when insertion fails.
     */
    @Test
    public void testAddRoleFailureDueToInsertionError() {
        Role newRole = new Role();
        newRole.setRoleName("New Role");

        when(roleMapper.selectByRoleName(newRole.getRoleName())).
                thenReturn(null);
        // Simulate the insertion failure
        when(roleMapper.insertSelective(newRole)).thenReturn(0);

        Exception exception = assertThrows(ParamsException.class, () -> {
            roleService.addRole(newRole);
        });

        assertEquals("Failed to add a new role!", exception.getMessage());
    }
    /**
     * Test case for deleting a role.
     */
    @Test
    public void testDeleteRole() {
        Integer roleId = 1;
        Role existingRole = new Role();
        existingRole.setId(roleId);

        when(roleMapper.selectByPrimaryKey(roleId)).thenReturn(existingRole);
        when(roleMapper.updateByPrimaryKeySelective(existingRole)).
                thenReturn(1);

        roleService.deleteRole(roleId);

        verify(roleMapper).updateByPrimaryKeySelective(existingRole);
    }

    /**
     * Test case for deleting a role when the role ID is null.
     */
    @Test
    public void testDeleteRoleRoleIdIsNull() {
        Integer roleId = null;

        Exception exception = assertThrows(ParamsException.class, () -> {
            roleService.deleteRole(roleId);
        });

        assertEquals("Records to be deleted do not exist!",
                exception.getMessage());
    }

    /**
     * Test case for deleting a role when the role is not found.
     */
    @Test
    public void testDeleteRoleRoleNotFound() {
        Integer roleId = 1;

        // Simulate no role found for the given roleId
        when(roleMapper.selectByPrimaryKey(roleId)).thenReturn(null);

        Exception exception = assertThrows(ParamsException.class, () -> {
            roleService.deleteRole(roleId);
        });

        assertEquals("Records to be deleted do not exist!",
                exception.getMessage());
    }

    /**
     * Test case for deleting a role when the update operation fails.
     */
    @Test
    public void testDeleteRoleFailureToUpdate() {
        Integer roleId = 1;
        Role existingRole = new Role();
        existingRole.setId(roleId);

        // Simulate role found for the given roleId
        when(roleMapper.selectByPrimaryKey(roleId)).thenReturn(existingRole);

        // Simulate failure in updating the role
        when(roleMapper.updateByPrimaryKeySelective(existingRole)).
                thenReturn(0);

        Exception exception = assertThrows(ParamsException.class, () -> {
            roleService.deleteRole(roleId);
        });

        assertEquals("Failed to delete a role", exception.getMessage());
    }

    /**
     * Test case for deleting a role successfully.
     */
    @Test
    public void testDeleteRoleSuccess() {
        Integer roleId = 1;
        Role existingRole = new Role();
        existingRole.setId(roleId);

        // Simulate role found for the given roleId
        when(roleMapper.selectByPrimaryKey(roleId)).thenReturn(existingRole);

        // Simulate successful update
        when(roleMapper.updateByPrimaryKeySelective(existingRole)).
                thenReturn(1);

        roleService.deleteRole(roleId);

        verify(roleMapper).updateByPrimaryKeySelective(existingRole);
    }

    /**
     * Test case for updating a role when the role ID is null.
     */
    @Test
    public void testUpdateRoleWhenRoleIdIsNull() {
        Role role = new Role();

        Exception exception = assertThrows(ParamsException.class, () -> {
            roleService.updateRole(role);
        });

        assertEquals("Role to be updated does not exist!",
                exception.getMessage());
    }

    /**
     * Test case for updating a role when the role does not exist.
     */
    @Test
    public void testUpdateRoleWhenRoleDoesNotExist() {
        Role role = new Role();
        role.setId(1);

        when(roleMapper.selectByPrimaryKey(role.getId())).thenReturn(null);

        Exception exception = assertThrows(ParamsException.class, () -> {
            roleService.updateRole(role);
        });

        assertEquals("Role to be updated does not exist!",
                exception.getMessage());
    }

    /**
     * Test case for updating a role when the role name is blank.
     */
    @Test
    public void testUpdateRoleWhenRoleNameIsBlank() {
        Role role = new Role();
        role.setId(1);
        role.setRoleName("");

        when(roleMapper.selectByPrimaryKey(role.getId())).thenReturn(role);

        Exception exception = assertThrows(ParamsException.class, () -> {
            roleService.updateRole(role);
        });

        assertEquals("Role name cannot be empty!", exception.getMessage());
    }

    /**
     * Test case for updating a role when the role name already exists.
     */
    @Test
    public void testUpdateRoleWhenRoleNameAlreadyExists() {
        Role role = new Role();
        role.setId(1);
        role.setRoleName("Existing Role");
        Role differentRole = new Role();
        differentRole.setId(2); // Different role ID

        when(roleMapper.selectByPrimaryKey(role.getId())).thenReturn(role);
        when(roleMapper.selectByRoleName(role.getRoleName())).
                thenReturn(differentRole);

        Exception exception = assertThrows(ParamsException.class, () -> {
            roleService.updateRole(role);
        });

        assertEquals("Failed to edit a role!", exception.getMessage());
    }

    /**
     * Test case for updating a role with a role name conflict.
     */
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void testUpdateRoleRoleNameConflict() {
        // Create a sample role
        Role sampleRole = new Role();
        sampleRole.setId(1); // Set the ID to simulate an existing role
        sampleRole.setRoleName("NewRoleName");

        // Mock the behavior of roleMapper.selectByPrimaryKey
        // to return the sample role
        when(roleMapper.selectByPrimaryKey(sampleRole.getId())).
                thenReturn(sampleRole);

        // Mock the behavior of roleMapper.selectByRoleName
        // to return null (no conflict)
        when(roleMapper.selectByRoleName(sampleRole.getRoleName())).
                thenReturn(null);

        // Mock the behavior of roleMapper.updateByPrimaryKeySelective
        // to indicate success
        when(roleMapper.updateByPrimaryKeySelective(sampleRole)).thenReturn(1);

        // Call the updateRole method
        roleService.updateRole(sampleRole);

        // Verify that the update method was called with the expected role
        verify(roleMapper).updateByPrimaryKeySelective(sampleRole);
    }


    /**
     * Test case for updating a role with a successful update.
     */
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void testUpdateRoleUpdateSuccess() {
        // Create a sample role
        Role sampleRole = new Role();
        sampleRole.setId(1); // Set the ID to simulate an existing role
        sampleRole.setRoleName("NewRoleName");

        // Mock the behavior of roleMapper.selectByPrimaryKey
        // to return the sample role
        when(roleMapper.selectByPrimaryKey(sampleRole.getId())).
                thenReturn(sampleRole);

        // Mock the behavior of roleMapper.selectByRoleName
        // to return null (no conflict)
        when(roleMapper.selectByRoleName(sampleRole.getRoleName())).
                thenReturn(null);

        // Mock the behavior of roleMapper.updateByPrimaryKeySelective
        // to indicate success
        when(roleMapper.updateByPrimaryKeySelective(sampleRole)).
                thenReturn(1);

        // Call the updateRole method
        roleService.updateRole(sampleRole);

        // Verify that the update method was called with the expected role
        verify(roleMapper).updateByPrimaryKeySelective(sampleRole);
    }
}
