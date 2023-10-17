package com.mvcmasters.ems.utils;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

public class Md5UtilTest {

    @Test
    public void testConstructor() {
        Md5Util util = new Md5Util();
        assertNotNull(util);
    }

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
}
