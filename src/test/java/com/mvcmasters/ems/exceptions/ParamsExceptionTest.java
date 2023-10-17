package com.mvcmasters.ems.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParamsExceptionTest {

    @Test
    public void testSetCode() {
        ParamsException exception = new ParamsException();

        exception.setCode(400);

        assertEquals(400, exception.getCode());
    }

    @Test
    public void testSetMsg() {
        ParamsException exception = new ParamsException();

        exception.setMsg("Custom error message");

        assertEquals("Custom error message", exception.getMsg());
    }

    @Test
    public void testConstructorWithCode() {
        int customCode = 400;
        ParamsException exception = new ParamsException(customCode);

        assertEquals(customCode, exception.getCode());

        assertEquals("Parameter exception!", exception.getMessage());
    }
}
