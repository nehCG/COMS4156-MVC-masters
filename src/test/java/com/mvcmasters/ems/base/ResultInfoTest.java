package com.mvcmasters.ems.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * This class represents a test for the ResultInfo class.
 */
public class ResultInfoTest {
    /**
     * HTTP status code for OK (200).
     */
    private static final int HTTP_OK = 200;

    /**
     * HTTP status code for Not Found (404).
     */
    private static final int HTTP_NOT_FOUND = 404;

    /**
     * HTTP status code for Bad Request (400).
     */
    private static final int HTTP_BAD_REQUEST = 400;

    /**
     * HTTP status code for Internal Server Error (500).
     */
    private static final int HTTP_INTERNAL_SERVER_ERROR = 500;

    /**
     * The ResultInfo instance for testing.
     */
    private ResultInfo resultInfo;

    /**
     * Set up the test by initializing the ResultInfo instance.
     */
    @BeforeEach
    public void setUp() {
        resultInfo = new ResultInfo();
    }

    /**
     * Test the default values of ResultInfo.
     */
    @Test
    public void testDefaultValues() {
        assertEquals(HTTP_OK, resultInfo.getCode());
        assertEquals("success", resultInfo.getMsg());
        assertNull(resultInfo.getResult());
    }

    /**
     * Test the parameterized constructor of ResultInfo.
     */
    @Test
    public void testParameterizedConstructor() {
        Object mockResult = new Object();
        resultInfo = new ResultInfo(HTTP_NOT_FOUND, "not found", mockResult);

        assertEquals(HTTP_NOT_FOUND, resultInfo.getCode());
        assertEquals("not found", resultInfo.getMsg());
        assertEquals(mockResult, resultInfo.getResult());
    }

    /**
     * Test setting the code of ResultInfo.
     */
    @Test
    public void testSetCode() {
        resultInfo.setCode(HTTP_BAD_REQUEST);
        assertEquals(HTTP_BAD_REQUEST, resultInfo.getCode());
    }

    /**
     * Test setting all properties of ResultInfo.
     */
    @Test
    public void testSetAll() {
        Object mockResult = new Object();
        resultInfo.setAll(HTTP_INTERNAL_SERVER_ERROR, "error", mockResult);

        assertEquals(HTTP_INTERNAL_SERVER_ERROR, resultInfo.getCode());
        assertEquals("error", resultInfo.getMsg());
        assertEquals(mockResult, resultInfo.getResult());
    }

    /**
     * Test the toString() method of ResultInfo.
     */
    @Test
    public void testToString() {
        Object mockResult = new Object();
        resultInfo.setAll(HTTP_BAD_REQUEST, "bad request", mockResult);

        String expectedString =
                "ResultInfo{code=400, msg='bad request', result="
                        + mockResult + "}";
        assertEquals(expectedString, resultInfo.toString());
    }
}
