package com.mvcmasters.ems.utils;

import com.mvcmasters.ems.exceptions.ParamsException;

/**
 * Utility class for making assertions.
 */
public final class AssertUtil {

    // Private constructor to prevent instantiation
    private AssertUtil() {
        // Empty private constructor
    }

    /**
     * Throws a ParamsException if the provided flag is true.
     *
     * @param flag The flag to check.
     * @param msg  The message for the exception.
     * @throws ParamsException if the provided flag is true.
     */
    public static void isTrue(final Boolean flag, final String msg) {
        if (flag) {
            throw new ParamsException(msg);
        }
    }
}
