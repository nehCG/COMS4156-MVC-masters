package com.mvcmasters.ems.external_integration.RESTFulAPI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.http.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * External Integration Test for index related interaction with
 * RESTFul API.
 */
@SpringBootTest
public class IndexRESTFulAPIIntegrationTest {
    /**
     * To make real HTTP request.
     */
    private final RestTemplate restTemplate;
    /**
     * Utility Function for Building HTTP requests.
     * @param builder Builder for RestTemplate
     */
    @Autowired
    public IndexRESTFulAPIIntegrationTest(
            final RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
    /**
     * Test the Index API Endpoint by making HTTP requests.
     */
    @Test
    public void testIndex() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/ems/index", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    /**
     * Test the Welcome API Endpoint by making HTTP requests.
     */
    @Test
    public void testWelcome() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/ems/welcome", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    /**
     * Test the Main API Endpoint by making HTTP requests.
     */
    @Test
    public void testMain() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", "yourUserIdCookie=123"); // Replace with your actual cookie format
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8080/ems/main",
                HttpMethod.GET,
                request,
                String.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
