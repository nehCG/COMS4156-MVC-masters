package com.mvcmasters.ems.utils;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Unit tests for LoginUserUtil.
 * Tests different scenarios for extracting a
 * user ID from cookies in an HttpServletRequest.
 */
public class LoginUserUtilTest {

    /**
     * Constant representing value 123.
     */
    private static final int V1 = 123;


    /**
     * Test extracting the user ID from a cookie when the
     * cookie with the specified key has a valid encoded user ID.
     * This test ensures that
     * {@link LoginUserUtil#releaseUserIdFromCookie(HttpServletRequest)}
     * correctly decodes and returns the user ID when a
     * valid encoded value is present in the cookie.
     */
    @Test
    public void testReleaseUserIdFromCookieWithValidUserId() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Cookie cookie = new Cookie("userIdStr", "validEncodedUserId");
        Cookie[] cookies = {cookie};
        when(request.getCookies()).thenReturn(cookies);

        try (MockedStatic<UserIDBase64> mockedStatic =
                     Mockito.mockStatic(UserIDBase64.class)) {
            mockedStatic.when(() ->
                    UserIDBase64.
                            decoderUserID("validEncodedUserId")).
                    thenReturn(V1);

            int result = LoginUserUtil.releaseUserIdFromCookie(request);

            assertEquals(V1, result);
        }
    }

    /**
     * Test extracting the user ID from a cookie when
     * no cookies are present in the request.
     * This test verifies that
     * {@link LoginUserUtil#releaseUserIdFromCookie(HttpServletRequest)}
     * returns 0 when the request contains no cookies.
     */
    @Test
    public void testReleaseUserIdFromCookieWithNoUserId() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Cookie[] cookies = new Cookie[0];
        when(request.getCookies()).thenReturn(cookies);

        int result = LoginUserUtil.releaseUserIdFromCookie(request);

        assertEquals(0, result);
    }

    /**
     * Test extracting the user ID from a cookie when
     * the cookie with the specified key is blank.
     * This test checks that
     * {@link LoginUserUtil#releaseUserIdFromCookie(HttpServletRequest)}
     * returns 0 when the cookie exists but its value is blank.
     */
    @Test
    public void testReleaseUserIdFromCookieWithBlankUserId() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Cookie cookie = new Cookie("userIdStr", "");
        Cookie[] cookies = {cookie};
        when(request.getCookies()).thenReturn(cookies);

        int result = LoginUserUtil.releaseUserIdFromCookie(request);

        assertEquals(0, result);
    }
}
