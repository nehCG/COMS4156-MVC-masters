package com.mvcmasters.ems.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserIDBase64Test {

    /**
     * Constant representing value 123.
     */
    private static final int V1 = 123;

    /**
     * Test encoding and then decoding a user ID.
     */
    @Test
    public void testEncodeAndDecodeUserID() {
        Integer originalUserID = V1;
        String encodedUserID = UserIDBase64.encoderUserID(originalUserID);
        assertNotNull(encodedUserID, "Encoded user ID should not be null");

        Integer decodedUserID = UserIDBase64.decoderUserID(encodedUserID);
        assertNotNull(decodedUserID, "Decoded user ID should not be null");
        assertEquals(originalUserID, decodedUserID,
                "Decoded user ID should match the original");
    }

    /**
     * Test decoding with a null input.
     */
    @Test
    public void testDecodeNullInput() {
        Integer decodedUserID = UserIDBase64.decoderUserID(null);
        assertNull(decodedUserID, "Decoding a null input should return null");
    }

    /**
     * Test decoding with a blank input.
     */
    @Test
    public void testDecodeBlankInput() {
        Integer decodedUserID = UserIDBase64.decoderUserID("");
        assertNull(decodedUserID, "Decoding a blank input should return null");
    }

    /**
     * Test encoding with a null input.
     */
    @Test
    public void testEncodeNullInput() {
        String encodedResult = UserIDBase64.encoderUserID(null);
        assertNotNull(encodedResult,
                "Encoding a null input should not return null");
        // Optionally, add more assertions to
        // check the pattern of the encodedResult
    }

    /**
     * Test decoding with an invalid input.
     */
    @Test
    public void testDecoderWithInvalidInput() {
        String invalidEncodedUserID = "invalidString";
        Integer result = UserIDBase64.decoderUserID(invalidEncodedUserID);
        assertNull(result,
        "Decoding an invalid input should return null due to an exception");
    }
}
