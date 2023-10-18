package com.mvcmasters.ems.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParamsExceptionTest {
    /**
     * HTTP status code for Bad Request (400).
     */
    private static final int HTTP_BAD_REQUEST = 400;

    /**
     * Test the setCode method of ParamsException.
     */
    @Test
    public void testSetCode() {
        ParamsException exception = new ParamsException();

        exception.setCode(HTTP_BAD_REQUEST);

        assertEquals(HTTP_BAD_REQUEST, exception.getCode());
    }

    /**
     * Test the setMsg method of ParamsException.
     */
    @Test
    public void testSetMsg() {
        ParamsException exception = new ParamsException();

        exception.setMsg("Custom error message");

        assertEquals("Custom error message", exception.getMsg());
    }

    /**
     * Test the constructor of ParamsException with a custom code.
     */
    @Test
    public void testConstructorWithCode() {
        int customCode = HTTP_BAD_REQUEST;
        ParamsException exception = new ParamsException(customCode);

        assertEquals(customCode, exception.getCode());

        assertEquals("Parameter exception!", exception.getMessage());
    }
}
