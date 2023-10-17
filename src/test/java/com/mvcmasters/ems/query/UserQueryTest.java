package com.mvcmasters.ems.query;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserQueryTest {

    private UserQuery userQuery;

    @BeforeEach
    public void setUp() {
        userQuery = new UserQuery();
    }

    @Test
    public void testSetUserName() {
        String expectedUserName = "JohnDoe";
        userQuery.setUserName(expectedUserName);
        assertEquals(expectedUserName, userQuery.getUserName());
    }

    @Test
    public void testSetEmail() {
        String expectedEmail = "john.doe@example.com";
        userQuery.setEmail(expectedEmail);
        assertEquals(expectedEmail, userQuery.getEmail());
    }

    @Test
    public void testSetPhone() {
        String expectedPhone = "1234567890";
        userQuery.setPhone(expectedPhone);
        assertEquals(expectedPhone, userQuery.getPhone());
    }

}
