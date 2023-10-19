package com.mvcmasters.ems.utils;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for the {@link Md5Util} utility class.
 */
public class Md5UtilTest {
    /**
     * Test for {@link Md5Util#encode(String)} with valid input.
     * It checks if the MD5 encoding produces the expected hash.
     */
    @Test
    public void testEncode() {
        String input = "123456";
        String expectedHash = "4QrcOUm6Wau+VuBX8g+IPg==";

        String actualHash = Md5Util.encode(input);

        assertEquals(expectedHash, actualHash);
    }

    /**
     * Test for {@link Md5Util#encode(String)} with an empty input string.
     * It checks if the MD5 encoding produces the
     * expected hash for an empty string.
     */
    @Test
    public void testEncodeWithEmptyString() {
        String input = "";
        String expectedHash = "1B2M2Y8AsgTpgAmY7PhCfg==";

        String actualHash = Md5Util.encode(input);

        assertEquals(expectedHash, actualHash);
    }

    /**
     * Test for {@link Md5Util#encode(String)} with null input.
     * It checks if the method returns null when given a null input.
     */
    @Test
    public void testEncodeWithNullInput() {
        String input = null;

        String actualHash = Md5Util.encode(input);

        assertNull(actualHash);
    }

    /**
     * Test for the main method of {@link Md5Util}.
     * It verifies if the main method correctly prints the MD5 hash of "123456".
     */
    @Test
    public void testMainMethod() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String[] args = {};
        Md5Util.main(args);

        System.setOut(System.out);

        String printedResult = outputStream.toString().trim();

        String expectedHash = Md5Util.encode("123456");

        assertEquals(expectedHash, printedResult);
    }

    /**
     * Test for {@link Md5Util#encode(String)} when
     * {@link MessageDigest#getInstance(String)} throws an exception.
     * It simulates an exception thrown by the MessageDigest
     * instance creation and checks if the method handles it by returning null.
     */
    @Test
    public void testEncodeThrowsException() throws NoSuchAlgorithmException {
        try (MockedStatic<MessageDigest> mockedMessageDigest =
                     Mockito.mockStatic(MessageDigest.class)) {
            // Make the MessageDigest's getInstance method throw an exception
            mockedMessageDigest.when(()
                    -> MessageDigest.getInstance("MD5")).
                    thenThrow(new RuntimeException("Mocked exception"));

            // Call the encode method
            String result = Md5Util.encode("test");

            // Assert the result is null and the exception is handled
            assertNull(result);
        }
    }
}
