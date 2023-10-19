package com.mvcmasters.ems.base;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BaseControllerTest {
    /**
     * The BaseController instance used for testing.
     */
    private BaseController baseController;

    /**
     * Set up the test by initializing the BaseController instance.
     */
    @BeforeEach
    public void setUp() {
        baseController = new BaseController();
    }

    /**
     * Test the preHandler method.
     */
    @Test
    public void testPreHandler() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        String contextPath = "/testPath";

        when(request.getContextPath()).thenReturn(contextPath);

        baseController.preHandler(request);

        verify(request).setAttribute("ctx", contextPath);
    }

    /**
     * Test the success method with no arguments.
     */
    @Test
    public void testSuccessNoArgs() {
        ResultInfo result = baseController.success();
        assertEquals("success", result.getMsg());
        assertNull(result.getResult());
    }

    /**
     * Test the success method with a message argument.
     */
    @Test
    public void testSuccessWithMsg() {
        String message = "Success!";
        ResultInfo result = baseController.success(message);

        assertEquals(message, result.getMsg());
        assertNull(result.getResult());
    }

    /**
     * Test the success method with a message and result argument.
     */
    @Test
    public void testSuccessWithMsgAndResult() {
        String message = "Success!";
        Object testResult = new Object();
        ResultInfo result = baseController.success(message, testResult);

        assertEquals(message, result.getMsg());
        assertEquals(testResult, result.getResult());
    }
}
