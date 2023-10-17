package com.mvcmasters.ems.base;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BaseControllerTest {

    private BaseController baseController;

    @BeforeEach
    public void setUp() {
        baseController = new BaseController();
    }

    @Test
    public void testPreHandler() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        String contextPath = "/testPath";

        when(request.getContextPath()).thenReturn(contextPath);

        baseController.preHandler(request);

        verify(request).setAttribute("ctx", contextPath);
    }

    @Test
    public void testSuccessNoArgs() {
        ResultInfo result = baseController.success();
        assertEquals("success", result.getMsg());
        assertEquals(null, result.getResult());
    }

    @Test
    public void testSuccessWithMsg() {
        String message = "Success!";
        ResultInfo result = baseController.success(message);

        assertEquals(message, result.getMsg());
        assertEquals(null, result.getResult());
    }

    @Test
    public void testSuccessWithMsgAndResult() {
        String message = "Success!";
        Object testResult = new Object();
        ResultInfo result = baseController.success(message, testResult);

        assertEquals(message, result.getMsg());
        assertEquals(testResult, result.getResult());
    }
}
