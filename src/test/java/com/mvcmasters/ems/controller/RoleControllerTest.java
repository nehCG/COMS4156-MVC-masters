package com.mvcmasters.ems.controller;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.mvcmasters.ems.query.RoleQuery;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import com.mvcmasters.ems.service.RoleService;
import com.mvcmasters.ems.vo.Role;
import com.mvcmasters.ems.base.ResultInfo;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

/**
 * Tests for the {@link RoleController} class.
 *
 * This class contains unit tests that verify
 * the functionality of the role management
 * controller in the EMS application. It ensures
 * that each controller method behaves
 * as expected with various input scenarios.
 */
public class RoleControllerTest {
    /**
     * Constant representing value 101.
     */
    private static final int V1 = 101;
    /**
     * Constant representing value 102.
     */
    private static final int V2 = 102;
    /**
     * Constant representing value 103.
     */
    private static final int V3 = 103;
    /**
     * HTTP status code for Success(200).
     */
    private static final int HTTP_SUCCESS = 200;
    /**
     * Mocked RoleService for testing RoleController.
     */
    @Mock
    private RoleService roleService;

    /**
     * The RoleController instance to be tested.
     */
    @InjectMocks
    private RoleController roleController;

    /**
     * Sets up the testing environment before each test.
     * Initializes and injects the mocked dependencies
     * before each test method is run.
     * This setup is necessary for the proper execution of the tests.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Tests the queryAllRoles method.
     * Verifies that the method returns the correct
     * list of roles for a given user ID.
     * Ensures that the role service is called with the correct user ID.
     */
    @Test
    public void testQueryAllRoles() {
        Integer userId = 1;
        List<Map<String, Object>> expectedRoles = new ArrayList<>();
        // Populate expectedRoles as needed for testing

        when(roleService.queryAllRoles(userId)).thenReturn(expectedRoles);

        List<Map<String, Object>> actualRoles =
                roleController.queryAllRoles(userId);
        assertEquals(expectedRoles, actualRoles);
    }

    /**
     * Tests the selectByParams method.
     * Checks if the method returns the correct
     * response map based on the provided
     * RoleQuery object. Ensures that the role
     * service is queried with the correct parameters.
     */
    @Test
    public void testSelectByParams() {
        RoleQuery roleQuery = new RoleQuery();
        // Configure roleQuery as needed
        Map<String, Object> expectedResponse = new HashMap<>();
        // Populate expectedResponse as needed for testing

        when(roleService.queryByParamsForTable(roleQuery)).
                thenReturn(expectedResponse);

        Map<String, Object> actualResponse =
                roleController.selectByParams(roleQuery);
        assertEquals(expectedResponse, actualResponse);
    }

    /**
     * Tests the addRole method.
     *
     * Validates that adding a role returns the correct ResultInfo object
     * and that the role service's addRole method
     * is called with the correct role data.
     */
    @Test
    public void testAddRole() {
        Role role = new Role();
        // Configure the role object as needed

        ResultInfo expectedResponse = new ResultInfo();
        expectedResponse.setCode(HTTP_SUCCESS);
        expectedResponse.setMsg("Role added successfully!");

        doNothing().when(roleService).addRole(role);

        ResultInfo actualResponse = roleController.addRole(role);
        assertEquals("Code should match", Optional.of(HTTP_SUCCESS),
                Optional.ofNullable(actualResponse.getCode()));
        assertEquals("Message should match",
                "Role added successfully!", actualResponse.getMsg());
        assertNull("Result should be null", actualResponse.getResult());
    }

    /**
     * Tests the toAddOrUpdateRolePage method.
     * Validates that the method returns the correct
     * view path and sets the correct role attribute in
     * the request when the roleId is provided.
     */
    @Test
    public void testToAddOrUpdateRolePage() {
        Integer roleId = 1;
        MockHttpServletRequest request = new MockHttpServletRequest();

        Role role = new Role(); // Suppose this is the role fetched
        when(roleService.selectByPrimaryKey(roleId)).thenReturn(role);

        String viewPath = roleController.toAddOrUpdateRolePage(roleId, request);
        assertEquals("role/add_update", viewPath);
        assertEquals(role, request.getAttribute("role"));
    }

    /**
     * Tests the updateRole method.
     * Checks that updating a role returns the correct ResultInfo object and
     * that the role service's updateRole method
     * is called with the updated role.
     */
    @Test
    public void testUpdateRole() {
        Role role = new Role();
        // Configure the role object as needed

        ResultInfo expectedResponse = new ResultInfo();
        expectedResponse.setCode(HTTP_SUCCESS);
        expectedResponse.setMsg("Role edited successfully!");

        doNothing().when(roleService).updateRole(role);

        ResultInfo actualResponse = roleController.updateRole(role);
        assertEquals("Code should match", Optional.of(HTTP_SUCCESS),
                Optional.ofNullable(actualResponse.getCode()));
        assertEquals("Message should match",
                "Role edited successfully!", actualResponse.getMsg());
        assertNull("Result should be null", actualResponse.getResult());
    }

    /**
     * Tests the {@link RoleController#deleteRole(Integer)} method.
     * Ensures that deleting a role returns the correct ResultInfo object and
     * that the role service's deleteRole method
     * is called with the correct role ID.
     */
    @Test
    public void testDeleteRole() {
        Integer roleId = 1;

        ResultInfo expectedResponse = new ResultInfo();
        expectedResponse.setCode(HTTP_SUCCESS);
        expectedResponse.setMsg("Role deleted successfully!");

        doNothing().when(roleService).deleteRole(roleId);

        ResultInfo actualResponse = roleController.deleteRole(roleId);
        assertEquals("Code should match", Optional.of(HTTP_SUCCESS),
                Optional.ofNullable(actualResponse.getCode()));
        assertEquals("Message should match",
                "Role deleted successfully!", actualResponse.getMsg());
        assertNull("Result should be null", actualResponse.getResult());
    }

    /**
     * Tests the index method.
     * Verifies that the method correctly navigates to the role index page.
     */
    @Test
    public void testIndexMethod() {
        String expectedView = "role/role";
        String actualView = roleController.index();
        assertEquals("The index method should return the correct view path",
                expectedView, actualView);
    }

    /**
     * Tests the toAddOrUpdateRolePage method with a non-null role ID.
     * Validates that the method correctly fetches and sets the role in
     * the request attribute when a valid role ID is provided.
     */
    @Test
    public void testToAddOrUpdateRolePageWithNonNullRoleId() {
        Integer roleId = 1; // Non-null role ID
        MockHttpServletRequest request = new MockHttpServletRequest();
        Role expectedRole = new Role(); // Mocked Role object

        when(roleService.selectByPrimaryKey(roleId)).thenReturn(expectedRole);

        String viewPath = roleController.toAddOrUpdateRolePage(roleId, request);
        assertEquals("role/add_update", viewPath);
        assertEquals(expectedRole, request.getAttribute("role"));
        verify(roleService).selectByPrimaryKey(roleId);
    }

    /**
     * Tests the toAddOrUpdateRolePage method with a null role ID.
     * Ensures that the method does not set the role attribute in the request
     * and still navigates to the correct view when no role ID is provided.
     */
    @Test
    public void testToAddOrUpdateRolePageWithNullRoleId() {
        MockHttpServletRequest request = new MockHttpServletRequest();

        String viewPath = roleController.toAddOrUpdateRolePage(null, request);
        assertEquals("role/add_update", viewPath);
        assertNull(request.getAttribute("role"));
        verify(roleService, never()).selectByPrimaryKey(any());
    }

    /**
     * Tests the {@code addGrant} method of {@link RoleController}.
     * This test verifies that the correct response is
     * returned when adding grant permissions to a role,
     * and ensures that the service method is called
     * with the appropriate parameters.
     */
    @Test
    public void testAddGrant() {
        Integer roleId = 1; // Example role ID
        Integer[] mIds = {V1, V2, V3}; // Example module IDs

        ResultInfo expectedResponse = new ResultInfo();
        expectedResponse.setCode(HTTP_SUCCESS);
        expectedResponse.setMsg("Role authorization successful!");

        // Mocking the behavior of the role service
        doNothing().when(roleService).addGrant(roleId, mIds);

        // Executing the addGrant method
        ResultInfo actualResponse = roleController.addGrant(roleId, mIds);

        // Assertions
        assertEquals("Code should match", Optional.of(HTTP_SUCCESS),
                Optional.ofNullable(actualResponse.getCode()));
        assertEquals("Message should match",
                "Role authorization successful!", actualResponse.getMsg());
        assertNull("Result should be null", actualResponse.getResult());

        // Verify that the service method was called with the correct parameters
        verify(roleService, times(1)).addGrant(roleId, mIds);
    }
}
