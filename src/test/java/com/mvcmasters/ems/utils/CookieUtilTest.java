package com.mvcmasters.ems.utils;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

/**
 * Unit tests for CookieUtil class.
 * This class tests different scenarios for retrieving
 * cookie values from an HttpServletRequest.
 */
public class CookieUtilTest {

    /**
     * Test retrieving the value of a cookie when the
     * cookie with the specified key exists.
     * This test ensures that
     * {@link CookieUtil#getCookieValue(HttpServletRequest, String)}
     * correctly returns the value of the cookie when
     * it is present in the request.
     */
    @Test
    public void testGetCookieValueWithValidCookie() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Cookie[] cookies = {new Cookie("testKey", "testValue")};
        when(request.getCookies()).thenReturn(cookies);

        String value = CookieUtil.getCookieValue(request, "testKey");
        assertEquals("testValue", value);
    }

    /**
     * Test retrieving the value of a cookie when no
     * cookies are present in the request.
     * This test verifies that
     * {@link CookieUtil#getCookieValue(HttpServletRequest, String)}
     * returns null when the request does not contain any cookies.
     */
    @Test
    public void testGetCookieValueWithNoCookies() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getCookies()).thenReturn(null);

        String value = CookieUtil.getCookieValue(request, "testKey");
        assertNull(value);
    }

    /**
     * Test retrieving the value of a cookie when no
     * cookie matches the specified key.
     * This test confirms that
     * {@link CookieUtil#getCookieValue(HttpServletRequest, String)}
     * returns null when there is no cookie in the
     * request that matches the given key.
     */
    @Test
    public void testGetCookieValueWithNoMatchingKey() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Cookie[] cookies = {new Cookie("differentKey", "testValue")};
        when(request.getCookies()).thenReturn(cookies);

        String value = CookieUtil.getCookieValue(request, "testKey");
        assertNull(value);
    }
}
