package com.mvcmasters.ems.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

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

    @Test
    public void testSettersWithNull() {
        // Set values using setters with null values
        user.setUserName(null);
        user.setEmail(null);

        // Verify that null values are stored as-is
        assertNull(user.getUserName());
        assertNull(user.getEmail());
    }

    @Test
    public void testSettersWithTrim() {
        // Set values with leading and trailing spaces
        user.setUserName("  john_doe  ");
        user.setTrueName("  John Doe  ");

        // Verify that leading and trailing spaces are trimmed
        assertEquals("john_doe", user.getUserName());
        assertEquals("John Doe", user.getTrueName());
    }

    @Test
    public void testSetUserPwdWithNull() {
        user.setUserPwd(null);

        // Verify that null is stored as-is
        assertNull(user.getUserPwd());
    }

    @Test
    public void testSetTrueNameWithNull() {
        user.setTrueName(null);

        // Verify that null is stored as-is
        assertNull(user.getTrueName());
    }

    @Test
    public void testSetPhoneWithNull() {
        user.setPhone(null);

        // Verify that null is stored as-is
        assertNull(user.getPhone());
    }

    @Test
    public void testSetUserPwdWithTrim() {
        user.setUserPwd("  password123  ");

        // Verify that leading and trailing spaces are trimmed
        assertEquals("password123", user.getUserPwd());
    }

    @Test
    public void testSetTrueNameWithTrim() {
        user.setTrueName("  John Doe  ");

        // Verify that leading and trailing spaces are trimmed
        assertEquals("John Doe", user.getTrueName());
    }

    @Test
    public void testSetPhoneWithTrim() {
        user.setPhone("  123-456-7890  ");

        // Verify that leading and trailing spaces are trimmed
        assertEquals("123-456-7890", user.getPhone());
    }
}
