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

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlobalExceptionResolverTest {

    private GlobalExceptionResolver exceptionResolver;

    @BeforeEach
    public void setUp() {
        exceptionResolver = new GlobalExceptionResolver();
    }

    @Test
    public void testResolveExceptionNonResponseBody() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        HandlerMethod handlerMethod = mock(HandlerMethod.class);

        ParamsException paramsException = new ParamsException(400, "Bad Request");

        when(handlerMethod.getMethod()).thenReturn(getMethodWithNoResponseBodyAnnotation());

        ModelAndView modelAndView = exceptionResolver.resolveException(request, response, handlerMethod, paramsException);

        assertEquals("error", modelAndView.getViewName());
        assertEquals(400, modelAndView.getModel().get("code"));
        assertEquals("Bad Request", modelAndView.getModel().get("msg"));
    }

    @Test
    public void testResolveExceptionWithResponseBody() throws UnsupportedEncodingException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        HandlerMethod handlerMethod = mock(HandlerMethod.class);
        ResponseBody responseBody = mock(ResponseBody.class);

        ParamsException paramsException = new ParamsException(400, "Bad Request");

        when(handlerMethod.getMethod()).thenReturn(getMethodWithResponseBodyAnnotation()); // Mock a method with ResponseBody annotation

        ModelAndView modelAndView = exceptionResolver.resolveException(request, response, handlerMethod, paramsException);

        assertNull(modelAndView);

        assertEquals(200, response.getStatus());
        assertEquals("application/json;charset=UTF-8", response.getHeader("Content-Type"));

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(400);
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
            return GlobalExceptionResolverTest.class.getMethod("dummyMethodWithResponseBody");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @ResponseBody
    public void dummyMethodWithResponseBody() {
        // Dummy method with ResponseBody annotation
    }

    public void dummyMethod() {
        // Dummy method without ResponseBody annotation
    }
}
