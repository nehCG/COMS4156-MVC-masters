package com.mvcmasters.ems.external_integration.RESTFulAPI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * External Integration Test for Modulerelated interaction
 * with RESTFul API.
 */
@SpringBootTest
public class ModuleRESTFulAPIIntegrationTest {
    /**
     * To make real HTTP request.
     */
    private final RestTemplate restTemplate;
    /**
     * Utility Function for Building HTTP requests.
     * @param builder Builder for RestTemplate
     */
    @Autowired
    public ModuleRESTFulAPIIntegrationTest(
            final RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
    /**
     * Test the QueryAllModulesAPI Endpoint by making HTTP requests.
     */
    @Test
    public void testQueryAllModules() {
        int roleId = 1;
        ResponseEntity<List> response = restTemplate.getForEntity(
                "http://localhost:8080/ems/module/queryAllModules?roleId="
                        + roleId,
                List.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    /**
     * Test the ToAddGrantPage Endpoint by making HTTP requests.
     */
    @Test
    public void testToAddGrantPage() {
        int roleId = 1; // Example role ID
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:8080/ems/module/toAddGrantPage?roleId="
                        + roleId,
                String.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("role/grant"));
    }
}
