package com.mvcmasters.ems.utils;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

public class Md5UtilTest {

    @Test
    public void testEncode() {
        String input = "123456";
        String expectedHash = "4QrcOUm6Wau+VuBX8g+IPg==";

        String actualHash = Md5Util.encode(input);

        assertEquals(expectedHash, actualHash);
    }

    @Test
    public void testEncodeWithEmptyString() {
        String input = "";
        String expectedHash = "1B2M2Y8AsgTpgAmY7PhCfg==";

        String actualHash = Md5Util.encode(input);

        assertEquals(expectedHash, actualHash);
    }

    @Test
    public void testEncodeWithNullInput() {
        String input = null;

        String actualHash = Md5Util.encode(input);

        assertNull(actualHash);
    }

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

    @Test
    public void testEncodeThrowsException() throws NoSuchAlgorithmException {
        try (MockedStatic<MessageDigest> mockedMessageDigest = Mockito.mockStatic(MessageDigest.class)) {
            // Make the MessageDigest's getInstance method throw an exception
            mockedMessageDigest.when(() -> MessageDigest.getInstance("MD5")).thenThrow(new RuntimeException("Mocked exception"));

            // Call the encode method
            String result = Md5Util.encode("test");

            // Assert the result is null and the exception is handled
            assertNull(result);
        }
    }
}
