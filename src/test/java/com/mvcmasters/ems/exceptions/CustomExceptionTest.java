package com.mvcmasters.ems.exceptions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

/**
 * A test class to verify the behavior of the CustomException class.
 */
public class CustomExceptionTest {

    /**
     * Test case to verify that when a CustomException is constructed
     * with a message and a status code,
     * it retains these properties correctly.
     */
    @Test
    public void messageAndStatusCodeRetentions() {
        String message = "Test message";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        CustomException customException = new CustomException(message, status);

        assertEquals(message, customException.getMessage());
        assertEquals(status, customException.getStatusCode());
    }

    /**
     * Test case to verify that when a CustomException is constructed
     * with a message, a cause, and a status code,
     * it retains these properties correctly.
     */
    @Test
    public void messageCauseAndStatusCodeRetention() {
        String message = "Test message";
        Throwable cause = new RuntimeException("Cause");
        HttpStatus status = HttpStatus.BAD_REQUEST;

        CustomException customException =
                new CustomException(message, cause, status);

        assertEquals(message, customException.getMessage());
        // assertSame is used because we want to verify that the
        // exact instance (cause) is present in the customException.
        assertSame(cause, customException.getCause());
        assertEquals(status, customException.getStatusCode());
    }

    /**
     * Test case to verify that the
     * {@link CustomException#setStatusCode(HttpStatus)}
     * method correctly sets the
     * status code of a CustomException instance.
     */
    @Test
    public void testSetStatusCode() {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomException customException =
                new CustomException("Test message",
                        HttpStatus.INTERNAL_SERVER_ERROR);

        customException.setStatusCode(status);

        assertEquals(status, customException.getStatusCode());
    }
}
