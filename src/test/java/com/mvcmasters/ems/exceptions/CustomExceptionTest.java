package com.mvcmasters.ems.exceptions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class CustomExceptionTest {

    @Test
    public void whenConstructedWithMessageAndStatusCode_thenRetainProperties() {
        String message = "Test message";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        CustomException customException = new CustomException(message, status);

        assertEquals(message, customException.getMessage());
        assertEquals(status, customException.getStatusCode());
    }

    @Test
    public void whenConstructedWithMessageCauseAndStatusCode_thenRetainProperties() {
        String message = "Test message";
        Throwable cause = new RuntimeException("Cause");
        HttpStatus status = HttpStatus.BAD_REQUEST;

        CustomException customException = new CustomException(message, cause, status);

        assertEquals(message, customException.getMessage());
        assertSame(cause, customException.getCause()); // assertSame is used because we want to verify that the exact instance (cause) is present in the customException.
        assertEquals(status, customException.getStatusCode());
    }

    @Test
    public void testSetStatusCode() {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomException customException = new CustomException("Test message", HttpStatus.INTERNAL_SERVER_ERROR);

        customException.setStatusCode(status);

        assertEquals(status, customException.getStatusCode());
    }
}
