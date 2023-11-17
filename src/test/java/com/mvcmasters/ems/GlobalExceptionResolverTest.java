package com.mvcmasters.ems;

import com.mvcmasters.ems.base.ResultInfo;
import com.mvcmasters.ems.exceptions.ParamsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests for the GlobalExceptionResolver class.
 */
public class GlobalExceptionResolverTest {
    /**
     * HTTP status code for Bad Request (400).
     */
    private static final int HTTP_BAD_REQUEST = 400;

    /**
     * HTTP status code for OK Request (200).
     */
    private static final int HTTP_OK_REQUEST = 200;

    /**
     * HTTP status code for Internal Server Error (500).
     */
    private static final int SERVER_ERROR_CODE = 500;

    /**
     * The GlobalExceptionResolver instance to be tested.
     */
    private GlobalExceptionResolver exceptionResolver;

    /**
     * Sets up the test environment by initializing
     * the exceptionResolver instance.
     * This method is executed before each test case.
     */
    @BeforeEach
    public void setUp() {
        exceptionResolver = new GlobalExceptionResolver();
    }

    /**
     * Test the resolveException method when the handler method does not have
     * the ResponseBody annotation and a ParamsException is thrown.
     */
    @Test
    public void testResolveExceptionNonResponseBody() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        HandlerMethod handlerMethod = mock(HandlerMethod.class);

        ParamsException paramsException =
                new ParamsException(HTTP_BAD_REQUEST, "Bad Request");

        when(handlerMethod.getMethod()).
                thenReturn(getMethodWithNoResponseBodyAnnotation());

        ModelAndView modelAndView =
                exceptionResolver.
                        resolveException(request, response,
                                handlerMethod, paramsException);

        assertEquals("error", modelAndView.getViewName());
        assertEquals(HTTP_BAD_REQUEST, modelAndView.getModel().get("code"));
        assertEquals("Bad Request", modelAndView.getModel().get("msg"));
    }

    /**
     * Test the resolveException method when the handler method has
     * the ResponseBody annotation and a ParamsException is thrown.
     * This test verifies that the response is correctly formatted as JSON.
     */
    @Test
    public void testResolveExceptionWithResponseBody()
            throws UnsupportedEncodingException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        HandlerMethod handlerMethod = mock(HandlerMethod.class);
        ResponseBody responseBody = mock(ResponseBody.class);

        ParamsException paramsException =
                new ParamsException(HTTP_BAD_REQUEST, "Bad Request");

        when(handlerMethod.getMethod()).
                thenReturn(getMethodWithResponseBodyAnnotation());

        ModelAndView modelAndView =
                exceptionResolver.
                        resolveException(request, response,
                                handlerMethod, paramsException);

        assertNull(modelAndView);

        assertEquals(HTTP_OK_REQUEST, response.getStatus());
        assertEquals("application/json;charset=UTF-8",
                response.getHeader("Content-Type"));

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(HTTP_BAD_REQUEST);
        resultInfo.setMsg("Bad Request");

        String expectedJson = "{\"code\":400,\"msg\":\"Bad Request\"}";
        assertEquals(expectedJson, response.getContentAsString());
    }

    private Method getMethodWithNoResponseBodyAnnotation() {
        try {
            return GlobalExceptionResolverTest.class.getMethod("dummyMethod");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private Method getMethodWithResponseBodyAnnotation() {
        try {
            return GlobalExceptionResolverTest.class.
                    getMethod("dummyMethodWithResponseBody");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test the resolveException method when the handler method has
     * the ResponseBody annotation and a generic exception
     * (not ParamsException) is thrown.
     * This test verifies that a server error response
     * is correctly formatted as JSON.
     */
    @Test
    public void testResolveExceptionWithResponseBodyAndNonParamsException()
            throws UnsupportedEncodingException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        HandlerMethod handlerMethod = mock(HandlerMethod.class);

        Exception genericException =
                new Exception("Generic exception"); // Not a ParamsException

        when(handlerMethod.getMethod()).
                thenReturn(getMethodWithResponseBodyAnnotation());

        ModelAndView modelAndView =
                exceptionResolver.
                        resolveException(request, response,
                                handlerMethod, genericException);

        assertNull(modelAndView);

        assertEquals(HTTP_OK_REQUEST, response.getStatus());
        assertEquals("application/json;charset=UTF-8",
                response.getHeader("Content-Type"));

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(SERVER_ERROR_CODE);
        resultInfo.setMsg("Server exception. Please try again...");

        String expectedJson =
                "{\"code\":500,\"msg\":\"Server exception. "
                        + "Please try again...\"}";
        assertEquals(expectedJson, response.getContentAsString());
    }

    /**
     * Test the resolveException method when an IOException occurs.
     * This test verifies that the method handles an IOException gracefully.
     */
    @Test
    public void testResolveExceptionWithIOException()
            throws UnsupportedEncodingException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = mock(MockHttpServletResponse.class);

        HandlerMethod handlerMethod = mock(HandlerMethod.class);
        ParamsException paramsException =
                new ParamsException(HTTP_BAD_REQUEST, "Bad Request");

        when(handlerMethod.getMethod()).
                thenReturn(getMethodWithResponseBodyAnnotation());

        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).
                thenThrow(new IOException("Simulated IOException for testing"));

        ModelAndView modelAndView =
                exceptionResolver.
                        resolveException(request, response,
                                handlerMethod, paramsException);

        assertNull(modelAndView);
    }

    /**
     * Test the resolveException method when the
     * handler method is not a HandlerMethod
     * and a generic exception is thrown.
     * This test verifies that the response is
     * correctly formatted as a server error.
     */
    @Test
    public void testResolveExceptionWithNonHandlerMethod() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        Object nonHandlerMethod = new Object(); // This is not a HandlerMethod

        Exception genericException = new Exception("Generic exception");

        ModelAndView modelAndView =
                exceptionResolver.
                        resolveException(request, response,
                                nonHandlerMethod, genericException);

        assertEquals("error", modelAndView.getViewName());
        assertEquals(SERVER_ERROR_CODE, modelAndView.getModel().get("code"));
        assertEquals("Server exception. Please try again...",
                modelAndView.getModel().get("msg"));
    }

    /**
     * Dummy method with the ResponseBody annotation for testing.
     */
    @ResponseBody
    public void dummyMethodWithResponseBody() {
    }

    /**
     * Dummy method without the ResponseBody annotation for testing.
     */
    public void dummyMethod() {
    }
}
