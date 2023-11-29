package com.mvcmasters.ems.external_integration.RESTFulAPI;

import com.mvcmasters.ems.query.RoleQuery;
import com.mvcmasters.ems.base.ResultInfo;
import com.mvcmasters.ems.vo.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
/**
 * External Integration Test for Role related interaction
 * with RESTFul API.
 */
@SpringBootTest
public class RoleRESTFulAPIIntegrationTest {
    /**
     * To make real HTTP request.
     */
    private final RestTemplate restTemplate;
    /**
     * Utility Function for Building HTTP requests.
     * @param builder Builder for RestTemplate
     */
    @Autowired
    public RoleRESTFulAPIIntegrationTest(
            final RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
    /**
     * Test the QueryAllRoles API Endpoint by making HTTP requests.
     */
    @Test
    public void testQueryAllRoles() {
        int userId = 1;
        ResponseEntity<List> response = restTemplate.getForEntity(
                "http://localhost:8080/ems/role/queryAllRoles?userId=" + userId,
                List.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    /**
     * Test the ListRoles Endpoint by making HTTP requests.
     */
    @Test
    public void testListRoles() {
        RoleQuery roleQuery = new RoleQuery();
        ResponseEntity<Map> response = restTemplate.getForEntity(
                "http://localhost:8080/ems/role/list",
                Map.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    /**
     * Test the AddRole Endpoint by making HTTP requests.
     */
    @Test
    public void testAddRole() {
        Role role = new Role();
        role.setRoleName("TestRole");
        HttpEntity<Role> request = new HttpEntity<>(role);
        ResponseEntity<ResultInfo> response = restTemplate.postForEntity(
                "http://localhost:8080/ems/role/add",
                request,
                ResultInfo.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    /**
     * Test the UpdateRole Endpoint by making HTTP requests.
     */
    @Test
    public void testUpdateRole() {
        Role role = new Role();
        HttpEntity<Role> request = new HttpEntity<>(role);
        ResponseEntity<ResultInfo> response = restTemplate.exchange(
                "http://localhost:8080/ems/role/update",
                HttpMethod.POST,
                request,
                ResultInfo.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    /**
     * Test the DeleteRole Endpoint by making HTTP requests.
     */
    @Test
    public void testDeleteRole() {
        int roleId = 1; // Example role ID to delete
        ResponseEntity<ResultInfo> response = restTemplate.exchange(
                "http://localhost:8080/ems/role/delete?roleId=" + roleId,
                HttpMethod.POST,
                null,
                ResultInfo.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    /**
     * Test the AddGrant Endpoint by making HTTP requests.
     */
    @Test
    public void testAddGrant() {
        Integer roleId = 1; // Example role ID
        Integer[] mIds = new Integer[]{1, 2, 3}; // Example module IDs
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> requestMap = Map.of("roleId", roleId, "mIds", mIds);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestMap, headers);
        ResponseEntity<ResultInfo> response = restTemplate.postForEntity(
                "http://localhost:8080/ems/role/addGrant",
                request,
                ResultInfo.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    /**
     * Test the Index Endpoint by making HTTP requests.
     */
    @Test
    public void testIndex() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:8080/ems/role/index",
                String.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    /**
     * Test the ToAddOrUpdateRolePage Endpoint by making HTTP requests.
     */
    @Test
    public void testToAddOrUpdateRolePage() {
        int roleId = 1; // Example role ID
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:8080/ems/role/toAddOrUpdateRolePage?roleId=" + roleId,
                String.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
