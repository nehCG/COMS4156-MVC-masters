package com.mvcmasters.ems.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoLoginExceptionTest {

    @Test
    public void testNoLoginExceptionDefaultConstructor() {
        NoLoginException exception = new NoLoginException();

        assertEquals(300, exception.getCode());
        assertEquals("User is not logged in!", exception.getMsg());
        assertEquals("User is not logged in!", exception.getMessage());
    }

    @Test
    public void testNoLoginExceptionMessageConstructor() {
        String customMessage = "Custom error message";
        NoLoginException exception = new NoLoginException(customMessage);

        assertEquals(300, exception.getCode());
        assertEquals(customMessage, exception.getMsg());
        assertEquals(customMessage, exception.getMessage());
    }

    @Test
    public void testNoLoginExceptionCodeConstructor() {
        int customCode = 400;
        NoLoginException exception = new NoLoginException(customCode);

        assertEquals(customCode, exception.getCode());
        assertEquals("User is not logged in!", exception.getMsg());
        assertEquals("User is not logged in!", exception.getMessage());
    }

    @Test
    public void testNoLoginExceptionCodeAndMessageConstructor() {
        int customCode = 400;
        String customMessage = "Custom error message";
        NoLoginException exception = new NoLoginException(customCode, customMessage);

        assertEquals(customCode, exception.getCode());
        assertEquals(customMessage, exception.getMsg());
        assertEquals(customMessage, exception.getMessage());
    }

    @Test
    public void testSetCode() {
        NoLoginException exception = new NoLoginException();

        exception.setCode(400);

        assertEquals(400, exception.getCode());
    }

    @Test
    public void testSetMsg() {
        NoLoginException exception = new NoLoginException();

        exception.setMsg("Custom error message");

        assertEquals("Custom error message", exception.getMsg());
    }
}
