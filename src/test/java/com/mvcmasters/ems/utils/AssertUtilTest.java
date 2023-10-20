package com.mvcmasters.ems.utils;

import com.mvcmasters.ems.exceptions.ParamsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the {@link AssertUtil} class.
 */
public class AssertUtilTest {

    /**
     * Test when the flag is true.
     * This should throw a {@link ParamsException}.
     */
    @Test
    public void testIsTrueWhenFlagIsTrue() {
        assertThrows(ParamsException.class, () -> {
            AssertUtil.isTrue(true, "Expected exception");
        });
    }

    /**
     * Test when the flag is false.
     * This should not throw an exception.
     */
    @Test
    public void testIsTrueWhenFlagIsFalse() {
        assertDoesNotThrow(() -> {
            AssertUtil.isTrue(false, "No exception expected");
        });
    }

    /**
     * Test for verifying the exception message when
     * a {@link ParamsException} is thrown.
     */
    @Test
    public void testExceptionMessage() {
        ParamsException exception = assertThrows(ParamsException.class, () -> {
            AssertUtil.isTrue(true, "Expected exception with specific message");
        });
        assertEquals("Expected exception with specific message",
                exception.getMessage());
    }
}
