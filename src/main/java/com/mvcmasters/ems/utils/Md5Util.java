package com.mvcmasters.ems.utils;

import java.security.MessageDigest;
import java.util.Base64;

/**
 * Utility class for encoding messages using MD5.
 */
public final class Md5Util {

    // Private constructor to prevent instantiation
    private Md5Util() {
        // Utility classes should not be instantiated
    }

    /**
     * Encodes a message using MD5 and returns
     * the result as a Base64-encoded string.
     *
     * @param msg The message to encode.
     * @return The MD5 hash of the message as a Base64-encoded string.
     */
    public static String encode(final String msg) {
        if (msg == null) {
            return null;
        }

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            return Base64.getEncoder().
                          encodeToString(messageDigest.digest(msg.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Main method to demonstrate the usage of the Md5Util class.
     *
     * @param args The command-line arguments (not used in this example).
     */
    public static void main(final String[] args) {
        System.out.println(encode("123456"));
    }
}
