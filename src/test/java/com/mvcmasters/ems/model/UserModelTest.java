package com.mvcmasters.ems.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserModelTest {

    @Test
    public void testUserIdGetterSetter() {
        UserModel user = new UserModel();

        user.setUserId(1);

        assertEquals(1, user.getUserId());
    }

    @Test
    public void testUserNameGetterSetter() {
        UserModel user = new UserModel();

        user.setUserName("JohnDoe");

        assertEquals("JohnDoe", user.getUserName());
    }

    @Test
    public void testTrueNameGetterSetter() {
        UserModel user = new UserModel();

        user.setTrueName("John Doe");

        assertEquals("John Doe", user.getTrueName());
    }
}
