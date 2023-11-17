package com.mvcmasters.ems.exceptions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link GlobalExceptionHandler}.
 * This class contains unit tests to verify that the GlobalExceptionHandler
 * correctly handles exceptions and returns the appropriate ResponseEntity.
 */
public class GlobalExceptionHandlerTest {

    /**
     * Test handling of {@link CustomException} by
     * {@link GlobalExceptionHandler}.
     * This test ensures that the GlobalExceptionHandler correctly converts
     * a CustomException into a ResponseEntity with the expected status code
     * and message from the exception.
     */
    @Test
    public void handleCustomExceptionTest() {
        // Create a mock CustomException
        CustomException customException = Mockito.mock(CustomException.class);
        String expectedMessage = "Test exception message";
        HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;

        // Configure the mock to return predefined values
        when(customException.getMessage()).thenReturn(expectedMessage);
        when(customException.getStatusCode()).thenReturn(expectedStatus);

        // Create an instance of GlobalExceptionHandler
        GlobalExceptionHandler handler = new GlobalExceptionHandler();

        // Call the method under test
        ResponseEntity<String> response = handler.
                handleCustomException(customException);

        // Assert that the response contains the expected status and message
        assertEquals(expectedStatus, response.getStatusCode());
        assertEquals(expectedMessage, response.getBody());
    }
}
