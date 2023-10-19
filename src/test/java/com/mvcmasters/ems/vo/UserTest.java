package com.mvcmasters.ems.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * This class contains unit tests for the User class.
 */
public class UserTest {

    /**
     * The User instance used for testing in the UserTest class.
     */
    private User user;

    /**
     * Initializes a new User instance before each test.
     */
    @BeforeEach
    public void setUp() {
        user = new User();
    }

    /**
     * Tests getters and setters for the User class.
     */
    @Test
    public void testGettersAndSetters() {
        // Set values using setters
        user.setId(1);
        user.setUserName("john_doe");
        user.setUserPwd("password123");
        user.setTrueName("John Doe");
        user.setEmail("john@example.com");
        user.setPhone("123-456-7890");
        user.setIsValid(1);

        // Verify values using getters
        assertEquals(1, user.getId());
        assertEquals("john_doe", user.getUserName());
        assertEquals("password123", user.getUserPwd());
        assertEquals("John Doe", user.getTrueName());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("123-456-7890", user.getPhone());
        assertEquals(1, user.getIsValid());
    }

    /**
     * Tests setters with null values for the User class.
     */
    @Test
    public void testSettersWithNull() {
        // Set values using setters with null values
        user.setUserName(null);
        user.setEmail(null);

        // Verify that null values are stored as-is
        assertNull(user.getUserName());
        assertNull(user.getEmail());
    }

    /**
     * Tests setters with leading and trailing spaces for the User class.
     */
    @Test
    public void testSettersWithTrim() {
        // Set values with leading and trailing spaces
        user.setUserName("  john_doe  ");
        user.setTrueName("  John Doe  ");

        // Verify that leading and trailing spaces are trimmed
        assertEquals("john_doe", user.getUserName());
        assertEquals("John Doe", user.getTrueName());
    }

    /**
     * Tests setUserPwd with a null value for the User class.
     */
    @Test
    public void testSetUserPwdWithNull() {
        user.setUserPwd(null);

        // Verify that null is stored as-is
        assertNull(user.getUserPwd());
    }

    /**
     * Tests setTrueName with a null value for the User class.
     */
    @Test
    public void testSetTrueNameWithNull() {
        user.setTrueName(null);

        // Verify that null is stored as-is
        assertNull(user.getTrueName());
    }

    /**
     * Tests setPhone with a null value for the User class.
     */
    @Test
    public void testSetPhoneWithNull() {
        user.setPhone(null);

        // Verify that null is stored as-is
        assertNull(user.getPhone());
    }

    /**
     * Tests setUserPwd with leading and trailing spaces for the User class.
     */
    @Test
    public void testSetUserPwdWithTrim() {
        user.setUserPwd("  password123  ");

        // Verify that leading and trailing spaces are trimmed
        assertEquals("password123", user.getUserPwd());
    }

    /**
     * Tests setTrueName with leading and trailing spaces for the User class.
     */
    @Test
    public void testSetTrueNameWithTrim() {
        user.setTrueName("  John Doe  ");

        // Verify that leading and trailing spaces are trimmed
        assertEquals("John Doe", user.getTrueName());
    }

    /**
     * Tests setPhone with leading and trailing spaces for the User class.
     */
    @Test
    public void testSetPhoneWithTrim() {
        user.setPhone("  123-456-7890  ");

        // Verify that leading and trailing spaces are trimmed
        assertEquals("123-456-7890", user.getPhone());
    }
}
