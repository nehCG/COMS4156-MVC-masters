package com.mvcmasters.ems.utils;

import com.mvcmasters.ems.exceptions.ParamsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssertUtilTest {

    @Test
    public void testConstructor() {
        AssertUtil util = new AssertUtil();
        assertNotNull(util);
    }

    @Test
    public void testIsTrueWhenFlagIsTrue() {
        assertThrows(ParamsException.class, () -> {
            AssertUtil.isTrue(true, "Expected exception");
        });
    }

    @Test
    public void testIsTrueWhenFlagIsFalse() {
        assertDoesNotThrow(() -> {
            AssertUtil.isTrue(false, "No exception expected");
        });
    }

    @Test
    public void testExceptionMessage() {
        ParamsException exception = assertThrows(ParamsException.class, () -> {
            AssertUtil.isTrue(true, "Expected exception with specific message");
        });
        assertEquals("Expected exception with specific message", exception.getMessage());
    }

}
