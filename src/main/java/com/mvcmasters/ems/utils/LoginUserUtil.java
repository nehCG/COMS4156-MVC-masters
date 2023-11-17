package com.mvcmasters.ems.utils;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Utility class for operations related to login user data.
 * This class provides static methods to work with user authentication data,
 * such as extracting user information from cookies in HTTP requests.
 */
public final class LoginUserUtil {
    // Private constructor to prevent instantiation
    private LoginUserUtil() {
        // Private constructor to prevent instantiation
    }
    /**
     * Extracts the user ID from a cookie in the given HTTP request.
     * This method retrieves the value of a cookie named 'userIdStr' from the
     * provided HTTP request. It then decodes the user ID
     * and returns it as an integer.
     * If the cookie is not present, or its value is blank,
     * this method returns 0.
     *
     *
     * @param request The HTTP request containing the cookies.
     * @return The decoded user ID as an integer, or 0 if not present or blank.
     */
    public static int releaseUserIdFromCookie(final HttpServletRequest
                                                      request) {
        // Retrieve the 'userIdStr' cookie value from the request
        String userIdString = CookieUtil.getCookieValue(request, "userIdStr");
        // Check if the retrieved string is blank, returning 0 if true
        if (StringUtils.isBlank(userIdString)) {
            return 0;
        }

        // Decode the user ID from the base64 encoded string
        Integer userId = UserIDBase64.decoderUserID(userIdString);
        return userId;
    }
}
