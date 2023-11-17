package com.mvcmasters.ems.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Utility class for handling cookies in HTTP requests.
 * This class provides static methods for cookie-related operations,
 * facilitating the retrieval of cookie values from HttpServletRequest objects.
 */
public final class CookieUtil {
    // Private constructor to prevent instantiation
    private CookieUtil() {
        // Private constructor to prevent instantiation
    }
    /**
     * Retrieves the value of a specific cookie from an HttpServletRequest.
     * This method scans the request for a cookie matching the specified key
     * and returns its value, if found.
     * The value is URL-decoded using UTF-8 encoding.
     * If the cookie is not found, or if an UnsupportedEncodingException occurs,
     * the method returns null.
     *
     * @param request The HttpServletRequest from which to retrieve the cookie.
     * @param key The name of the cookie whose value is to be retrieved.
     * @return The decoded value of the cookie,
     *         or null if the cookie is not found
     *         or if an UnsupportedEncodingException occurs.
     */
    public static String getCookieValue(final HttpServletRequest request,
                                        final String key) {
        // Retrieve all cookies from the request
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        // Check if cookies are present in the request
        if (cookies != null) {
            // Iterate through cookies to find the one that matches the key
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(key)) {
                    cookie = cookies[i];
                }
            }
        }
        // Check if the target cookie is found
        if (cookie != null) {
            try {
                // Decode the cookie value using UTF-8 encoding and return it
                return URLDecoder.decode(cookie.getValue(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // Log the exception and return null if an encoding issue occurs
                e.printStackTrace();
                return null;
            }
        }
        // Return null if the cookie is not found or in case of an exception
        return null;
    }
}
