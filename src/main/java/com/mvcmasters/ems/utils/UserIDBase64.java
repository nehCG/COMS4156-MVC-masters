package com.mvcmasters.ems.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Base64;

/**
 * Utility class for encoding and decoding user IDs using Base64.
 * This class provides static methods to encode and decode user IDs,
 * which is useful for obfuscating user IDs in transport or storage.
 */
public final class UserIDBase64 {
    // Private constructor to prevent instantiation
    private UserIDBase64() {
        // Private constructor to prevent instantiation
    }
    /**
     * Constant representing value 4.
     */
    private static final int V1 = 4;
    /**
     * Constant representing value 6.
     */
    private static final int V2 = 6;
    /**
     * Constant representing value 8.
     */
    private static final int V3 = 8;
    /**
     * Decodes a Base64 encoded user ID string to its integer form.
     * The method reverses the encoded string, replaces certain characters
     * for proper Base64 decoding, and extracts the user ID portion to decode.
     * If the input is blank or decoding fails, returns null.
     *
     * @param encodedUserID The Base64 encoded user ID string.
     * @return The decoded user ID as an Integer,
     * or null if input is blank or in case of decoding failure.
     */
    public static Integer decoderUserID(final String encodedUserID) {
        if (StringUtils.isBlank(encodedUserID)) {
            return null;
        }
        try {
            String reversedString =
                    new StringBuffer(encodedUserID).reverse().toString();
            String base64String = reversedString.replaceAll("#", "=");
            int userIDPos = base64String.indexOf("==") + V2;
            String realBase64UserID = base64String.substring(userIDPos);
            String base64Encoded =
                    new String(Base64.getDecoder().
                            decode(realBase64UserID.getBytes()));
            return Integer.parseInt(base64Encoded);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * Encodes a user ID integer into a Base64 encoded string.
     * This method encodes the user ID and the current system time into Base64,
     * performs certain transformations for obfuscation,
     * and returns the encoded string.
     *
     * @param userID The user ID to be encoded.
     * @return The Base64 encoded string representation of the user ID.
     */
    public static String encoderUserID(final Integer userID) {
        String base64UserIDEncoded =
                Base64.getEncoder().encodeToString((userID + "").getBytes());
        String currentStringBase64Encoded =
                Base64.getEncoder().
                        encodeToString((System.currentTimeMillis() + "").
                                getBytes());
        String keyString = currentStringBase64Encoded
                + currentStringBase64Encoded.substring(V1, V3)
                + base64UserIDEncoded;
        byte[] codeBytes = keyString.getBytes();
        byte[] ordedBytes = new byte[codeBytes.length];
        for (int i = 0; i < codeBytes.length; i++) {
            ordedBytes[i] = codeBytes[codeBytes.length - i - 1];
        }
        return new String(ordedBytes).replaceAll("=", "#");
    }
}
