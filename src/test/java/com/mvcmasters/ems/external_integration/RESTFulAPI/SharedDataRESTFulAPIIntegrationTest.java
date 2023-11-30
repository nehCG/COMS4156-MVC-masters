package com.mvcmasters.ems.external_integration.RESTFulAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvcmasters.ems.model.SharedDataModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * External Integration Test for shared data related interaction
 * with RESTFul API.
 */
@SpringBootTest
public class SharedDataRESTFulAPIIntegrationTest {
    /**
     * For HTTP request.
     */
    private final RestTemplate restTemplate;
    /**
     * Utility Function for Building HTTP requests.
     * @param builder Builder for RestTemplate
     */
    @Autowired
    public SharedDataRESTFulAPIIntegrationTest(
            final RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    // Utility method for converting an object to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Test the Add Shared Data API Endpoint by
     * making Post HTTP requests.
     */
    @Test
    public void testAddSharedDataAPIEndPoint() {
        SharedDataModel sharedData = new SharedDataModel();
        sharedData.setSubject("Test Subject");
        sharedData.setContent("Test Content");
        sharedData.setUid(1); // Assuming this user ID exists in your system

        // Set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request entity
        HttpEntity<String> request =
                new HttpEntity<>(asJsonString(sharedData), headers);

        // Send POST request to the external API
        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:8080/ems/announcement/post",
                request,
                String.class);

        // Assertions to validate the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    /**
     * Test the get Shared Data by id API Endpoint by
     * making get HTTP requests.
     */
    @Test
    public void testGetSharedDataByIdAPIEndPoint() {
        int validId = 2;

        // Send GET request to the external API
        ResponseEntity<SharedDataModel> response = restTemplate.getForEntity(
                "http://localhost:8080/ems/announcement/" + validId,
                SharedDataModel.class);

        // Assertions to validate the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    /**
     * Test the get all Shared DataAPI Endpoint by
     * making get HTTP requests.
     */
    @Test
    public void testGetAllSharedDataAPIEndPoint() {
        // Send GET request to the external API
        ResponseEntity<List<SharedDataModel>> response = restTemplate.exchange(
                "http://localhost:8080/ems/announcement/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SharedDataModel>>() { });

        // Assertions to validate the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
    }
    /**
     * Test the update Shared Data by id API Endpoint by
     * making put HTTP requests.
     */
    @Test
    public void testUpdateSharedDataAPIEndpoint() {
        int validId = 2; // Replace with a valid ID
        SharedDataModel sharedDataToUpdate = new SharedDataModel();
        sharedDataToUpdate.setSubject("updated Subject");
        sharedDataToUpdate.setContent("updated Content");
        sharedDataToUpdate.setUid(1);
        String jsonPayload = asJsonString(sharedDataToUpdate);

        // Set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request entity
        HttpEntity<String> request = new HttpEntity<>(jsonPayload, headers);

        // Send PUT request to the external API
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8080/ems/announcement/update/" + validId,
                HttpMethod.PUT,
                request,
                String.class);

        // Assertions to validate the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Shared data updated successfully!", response.getBody());
    }
    /**
     * Test the delete Shared Data by id API Endpoint by
     * making delete HTTP requests.
     */
    @Test
    public void testDeleteSharedDataByIdAPIEndpoint() {
        int validId = 1; // Replace with a valid ID that can be safely deleted

        // Send DELETE request to the external API
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8080/ems/announcement/delete/" + validId,
                HttpMethod.DELETE,
                null,
                String.class);

        // Assertions to validate the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Shared data deleted successfully!", response.getBody());
    }
}
