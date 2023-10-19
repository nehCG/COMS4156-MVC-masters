package com.mvcmasters.ems.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * This class contains unit tests for the SharedData class.
 */
public class SharedDataTest {

    /**
     * The SharedData instance used for testing in the SharedDataTest class.
     */
    private SharedData sharedData;

    /**
     * Initializes a new SharedData instance before each test.
     */
    @BeforeEach
    public void setUp() {
        sharedData = new SharedData();
    }

    /**
     * Tests getters and setters for the SharedData class.
     */
    @Test
    public void testGettersAndSetters() {
        // Set values using setters
        sharedData.setId(1);
        sharedData.setContent("abc");
        sharedData.setSubject("a");
        sharedData.setCreatedTime("2023-05-30");
        sharedData.setLastModifiedTime("2023-05-31");
        sharedData.setUid(2);
        sharedData.setLastModifiedBy(1);


        // Verify values using getters
        assertEquals(1, sharedData.getId());
        assertEquals("abc", sharedData.getContent());
        assertEquals("a", sharedData.getSubject());
        assertEquals("2023-05-30", sharedData.getCreatedTime());
        assertEquals("2023-05-31", sharedData.getLastModifiedTime());
        assertEquals(2, sharedData.getUid());
        assertEquals(1, sharedData.getLastModifiedBy());
    }

    /**
     * Tests setters with null values for the SharedData class.
     */
    @Test
    public void testSettersWithNull() {
        // Set values using setters with null values
        sharedData.setLastModifiedBy(null);
        sharedData.setUid(null);
        sharedData.setSubject(null);
        sharedData.setContent(null);

        // Verify that null values are stored as-is
        assertNull(sharedData.getLastModifiedBy());
        assertNull(sharedData.getUid());
        assertNull(sharedData.getSubject());
        assertNull(sharedData.getContent());
    }

    /**
     * Tests setters with trim for the SharedData class.
     */
    @Test
    public void testSettersWithTrim() {
        // Set values with leading and trailing spaces
        sharedData.setContent("  abc  ");
        sharedData.setSubject("  dce  ");

        // Verify that leading and trailing spaces are trimmed
        assertEquals("abc", sharedData.getContent());
        assertEquals("dce", sharedData.getSubject());
    }

    /**
     * Tests setLastModifiedTime with null values for the SharedData class.
     */
    @Test
    public void testSetLastModifiedTimeWithNull() {
        sharedData.setLastModifiedTime(null);

        // Verify that null is stored as-is
        assertNull(sharedData.getLastModifiedTime());
    }

    /**
     * Tests setCreatedTime with null values for the SharedData class.
     */
    @Test
    public void testSetCreatedTimeWithNull() {
        sharedData.setCreatedTime(null);

        // Verify that null is stored as-is
        assertNull(sharedData.getCreatedTime());
    }

    /**
     * Tests setLastModifiedTime with trim for the SharedData class.
     */
    @Test
    public void testSetLastModifiedTimeWithTrim() {
        sharedData.setLastModifiedTime("  2013-07/02  ");

        // Verify that leading and trailing spaces are trimmed
        assertEquals("2013-07/02", sharedData.getLastModifiedTime());
    }

    /**
     * Tests setCreatedTime with trim for the SharedData class.
     */
    @Test
    public void testSetCreatedTimeWithTrim() {
        sharedData.setCreatedTime("  2023-08/03  ");

        // Verify that leading and trailing spaces are trimmed
        assertEquals("2023-08/03", sharedData.getCreatedTime());
    }
}
