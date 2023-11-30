package com.mvcmasters.ems.internal_integration.ControllerServiceIntegration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvcmasters.ems.query.RoleQuery;
import com.mvcmasters.ems.service.RoleService;
import com.mvcmasters.ems.vo.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Internal Integration Test for testing interaction
 * between RoleController and RoleService.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class RoleControllerIntegrationTest {

    /**
     * Mock of HTTP requests.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Mock of RoleService.
     */
    @MockBean
    private RoleService roleService;
    /**
     * Utility function of ObjectMapper for HTTP Request.
     */
    private final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * Test the QueryAllRoles Method.
     */
    @Test
    public void testQueryAllRoles() throws Exception {
        // Arrange
        Integer userId = 1;
        Map<String, Object> role = new HashMap<>();
        role.put("id", 1);
        role.put("name", "Admin");

        given(roleService.queryAllRoles(userId))
                .willReturn(Arrays.asList(role));

        // Act & Assert
        mockMvc.perform(get("/role/queryAllRoles")
                        .param("userId", userId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(role.get("id")))
                .andExpect(jsonPath("$[0].name").value(role.get("name")));

        verify(roleService).queryAllRoles(userId);
    }
    /**
     * Test the SelectByParams Method.
     */
    @Test
    public void testSelectByParams() throws Exception {
        // Arrange
        RoleQuery roleQuery = new RoleQuery();
        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("key", "value");

        given(roleService.queryByParamsForTable(any(RoleQuery.class)))
                .willReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(get("/role/list")
                        .param("param1", "value1")
                        .param("param2", "value2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("value"));

        verify(roleService).queryByParamsForTable(any(RoleQuery.class));
    }
    /**
     * Test the Index Method.
     */
    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/role/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("role/role"));
    }
    /**
     * Test the AddRole Method.
     */
    @Test
    public void testAddRole() throws Exception {
        // Arrange
        Role role = new Role();
        doNothing().when(roleService).addRole(any(Role.class));

        // Act & Assert
        mockMvc.perform(post("/role/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(role)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Role added successfully!"));

        verify(roleService).addRole(any(Role.class));
    }
    /**
     * Test the ToAddOrUpdateRolePageWithRoleId Method.
     */
    @Test
    public void testToAddOrUpdateRolePageWithRoleId() throws Exception {
        Integer roleId = 1;
        Role role = new Role();
        given(roleService.selectByPrimaryKey(roleId)).willReturn(role);

        mockMvc.perform(get("/role/toAddOrUpdateRolePage")
                        .param("roleId", roleId.toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("role/add_update"));

        verify(roleService).selectByPrimaryKey(roleId);
    }
    /**
     * Test the ToAddOrUpdateRolePageWithoutRoleId Method.
     */
    @Test
    public void testToAddOrUpdateRolePageWithoutRoleId() throws Exception {
        mockMvc.perform(get("/role/toAddOrUpdateRolePage"))
                .andExpect(status().isOk())
                .andExpect(view().name("role/add_update"))
                .andExpect(model().attributeDoesNotExist("role"));
    }
    /**
     * Test the UpdateRole Method.
     */
    @Test
    public void testUpdateRole() throws Exception {
        // Arrange
        Role role = new Role();
        doNothing().when(roleService).updateRole(any(Role.class));

        // Act & Assert
        mockMvc.perform(post("/role/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(role)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg")
                        .value("Role edited successfully!"));

        verify(roleService).updateRole(any(Role.class));
    }
    /**
     * Test the DeleteRole Method.
     */
    @Test
    public void testDeleteRole() throws Exception {
        // Arrange
        Integer roleId = 1;
        doNothing().when(roleService).deleteRole(roleId);

        // Act & Assert
        mockMvc.perform(post("/role/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(roleId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg")
                        .value("Role deleted successfully!"));
    }
    /**
     * Test the AddGrant Method.
     */
    @Test
    public void testAddGrant() throws Exception {
        // Arrange
        Integer roleId = 1;
        Integer[] mIds = new Integer[]{1, 2};
        doNothing().when(roleService).addGrant(roleId, mIds);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("roleId", roleId);
        requestBody.put("mIds", mIds);

        // Act & Assert
        mockMvc.perform(post("/role/addGrant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg")
                        .value("Role authorization successful!"));
    }
}
