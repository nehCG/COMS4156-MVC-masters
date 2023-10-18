package com.mvcmasters.ems.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SharedDataTest {

    private SharedData sharedData;

    @BeforeEach
    public void setUp() {
        sharedData = new SharedData();
    }

    @Test
    public void testGettersAndSetters() {
        // Set values using setters
        sharedData.setId(1);
        sharedData.setContent("abc");
        sharedData.setSubject("a");
        sharedData.setCreatedTime("2023-05-30");
        sharedData.setLastModifiedTime("2023-05-31");
        sharedData.setUserId(2);
        sharedData.setLastModifiedUserID(3);


        // Verify values using getters
        assertEquals(1, sharedData.getId());
        assertEquals("abc", sharedData.getContent());
        assertEquals("a", sharedData.getSubject());
        assertEquals("2023-05-30", sharedData.getCreatedTime());
        assertEquals("2023-05-31", sharedData.getLastModifiedTime());
        assertEquals(2, sharedData.getUserID());
        assertEquals(3, sharedData.getLastModifiedUserID());
    }

    @Test
    public void testSettersWithNull() {
        // Set values using setters with null values
        sharedData.setLastModifiedUserID(null);
        sharedData.setUserId(null);

        // Verify that null values are stored as-is
        assertNull(sharedData.getLastModifiedUserID());
        assertNull(sharedData.getUserID());
    }

    @Test
    public void testSettersWithTrim() {
        // Set values with leading and trailing spaces
        sharedData.setContent("  abc  ");
        sharedData.setSubject("  dce  ");

        // Verify that leading and trailing spaces are trimmed
        assertEquals("abc", sharedData.getContent());
        assertEquals("dce", sharedData.getSubject());
    }

    @Test
    public void testSetLastModifiedTimeWithNull() {
        sharedData.setLastModifiedTime(null);

        // Verify that null is stored as-is
        assertNull(sharedData.getLastModifiedTime());
    }

    @Test
    public void testSetCreatedTimeWithNull() {
        sharedData.setCreatedTime(null);

        // Verify that null is stored as-is
        assertNull(sharedData.getCreatedTime());
    }

    @Test
    public void testSetLastModifiedTimeWithTrim() {
        sharedData.setLastModifiedTime("  2013-07/02  ");

        // Verify that leading and trailing spaces are trimmed
        assertEquals("2013-07/02", sharedData.getLastModifiedTime());
    }

    @Test
    public void testSetCreatedTimeWithTrim() {
        sharedData.setCreatedTime("  2023-08/03  ");

        // Verify that leading and trailing spaces are trimmed
        assertEquals("2023-08/03",sharedData.getCreatedTime());
    }
}
