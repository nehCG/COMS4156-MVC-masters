package com.mvcmasters.ems.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResultInfoTest {

    private ResultInfo resultInfo;

    @BeforeEach
    public void setUp() {
        resultInfo = new ResultInfo();
    }

    @Test
    public void testDefaultValues() {
        assertEquals(200, resultInfo.getCode());
        assertEquals("success", resultInfo.getMsg());
        assertNull(resultInfo.getResult());
    }

    @Test
    public void testParameterizedConstructor() {
        Object mockResult = new Object();
        resultInfo = new ResultInfo(404, "not found", mockResult);

        assertEquals(404, resultInfo.getCode());
        assertEquals("not found", resultInfo.getMsg());
        assertEquals(mockResult, resultInfo.getResult());
    }

    @Test
    public void testSetCode() {
        resultInfo.setCode(400);
        assertEquals(400, resultInfo.getCode());
    }

    @Test
    public void testSetAll() {
        Object mockResult = new Object();
        resultInfo.setAll(500, "error", mockResult);

        assertEquals(500, resultInfo.getCode());
        assertEquals("error", resultInfo.getMsg());
        assertEquals(mockResult, resultInfo.getResult());
    }

    @Test
    public void testToString() {
        Object mockResult = new Object();
        resultInfo.setAll(400, "bad request", mockResult);

        String expectedString = "ResultInfo{code=400, msg='bad request', result=" + mockResult + "}";
        assertEquals(expectedString, resultInfo.toString());
    }

}
