package com.mvcmasters.ems.query;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserQueryTest {

    /**
     * The UserQuery instance used for testing.
     */
    private UserQuery userQuery;

    /**
     * Set up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        userQuery = new UserQuery();
    }

    /**
     * Test the setUserName and getUserName methods of UserQuery.
     */
    @Test
    public void testSetUserName() {
        String expectedUserName = "JohnDoe";
        userQuery.setUserName(expectedUserName);
        assertEquals(expectedUserName, userQuery.getUserName());
    }

    /**
     * Test the setEmail and getEmail methods of UserQuery.
     */
    @Test
    public void testSetEmail() {
        String expectedEmail = "john.doe@example.com";
        userQuery.setEmail(expectedEmail);
        assertEquals(expectedEmail, userQuery.getEmail());
    }

    /**
     * Test the setPhone and getPhone methods of UserQuery.
     */
    @Test
    public void testSetPhone() {
        String expectedPhone = "1234567890";
        userQuery.setPhone(expectedPhone);
        assertEquals(expectedPhone, userQuery.getPhone());
    }

}
