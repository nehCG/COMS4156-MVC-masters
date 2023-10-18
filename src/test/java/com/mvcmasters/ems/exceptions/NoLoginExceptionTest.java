package com.mvcmasters.ems.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoLoginExceptionTest {
    /**
     * HTTP status code for Multiple Choices (300).
     */
    private static final int HTTP_MULTIPLE_CHOICES = 300;

    /**
     * HTTP status code for Bad Request (400).
     */
    private static final int HTTP_BAD_REQUEST = 400;

    /**
     * Test the NoLoginException default constructor.
     */
    @Test
    public void testNoLoginExceptionDefaultConstructor() {
        NoLoginException exception = new NoLoginException();

        assertEquals(HTTP_MULTIPLE_CHOICES, exception.getCode());
        assertEquals("User is not logged in!", exception.getMsg());
        assertEquals("User is not logged in!", exception.getMessage());
    }

    /**
     * Test the NoLoginException constructor with a custom message.
     */
    @Test
    public void testNoLoginExceptionMessageConstructor() {
        String customMessage = "Custom error message";
        NoLoginException exception = new NoLoginException(customMessage);

        assertEquals(HTTP_MULTIPLE_CHOICES, exception.getCode());
        assertEquals(customMessage, exception.getMsg());
        assertEquals(customMessage, exception.getMessage());
    }

    /**
     * Test the NoLoginException constructor with a custom code.
     */
    @Test
    public void testNoLoginExceptionCodeConstructor() {
        int customCode = HTTP_BAD_REQUEST;
        NoLoginException exception = new NoLoginException(customCode);

        assertEquals(customCode, exception.getCode());
        assertEquals("User is not logged in!", exception.getMsg());
        assertEquals("User is not logged in!", exception.getMessage());
    }

    /**
     * Test the NoLoginException constructor with a custom code and message.
     */
    @Test
    public void testNoLoginExceptionCodeAndMessageConstructor() {
        int customCode = HTTP_BAD_REQUEST;
        String customMessage = "Custom error message";
        NoLoginException exception =
                new NoLoginException(customCode, customMessage);

        assertEquals(customCode, exception.getCode());
        assertEquals(customMessage, exception.getMsg());
        assertEquals(customMessage, exception.getMessage());
    }

    /**
     * Test the setCode method of NoLoginException.
     */
    @Test
    public void testSetCode() {
        NoLoginException exception = new NoLoginException();

        exception.setCode(HTTP_BAD_REQUEST);

        assertEquals(HTTP_BAD_REQUEST, exception.getCode());
    }

    /**
     * Test the setMsg method of NoLoginException.
     */
    @Test
    public void testSetMsg() {
        NoLoginException exception = new NoLoginException();

        exception.setMsg("Custom error message");

        assertEquals("Custom error message", exception.getMsg());
    }
}
