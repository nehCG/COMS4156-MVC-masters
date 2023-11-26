package com.mvcmasters.ems.vo;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;

/**
 * This class contains unit tests to ensure that the
 * getters and setters of the UserRole class.
 */
public class UserRoleTest {

    /**
     * Constant representing value 123.
     */
    private static final int C1 = 100;

    /**
     * Constant representing value 123.
     */
    private static final int C2 = 200;

    /**
     * The UserRole instance used for testing.
     */
    private UserRole userRole;

    /**
     * Initializes a new UserRole instance before each test.
     */
    @Before
    public void setUp() {
        userRole = new UserRole();
    }

    /**
     * Tests the getter and setter for the UserRole ID.
     * Verifies that the ID set by the setter is
     * correctly retrieved by the getter.
     */
    @Test
    public void testIdGetterSetter() {
        Integer expectedId = 1;
        userRole.setId(expectedId);
        assertEquals(expectedId, userRole.getId());
    }

    /**
     * Tests the getter and setter for the UserRole's user ID.
     * Ensures that the user ID set by the setter is
     * accurately retrieved by the getter.
     */
    @Test
    public void testUserIdGetterSetter() {
        Integer expectedUserId = C1;
        userRole.setUserId(expectedUserId);
        assertEquals(expectedUserId, userRole.getUserId());
    }

    /**
     * Tests the getter and setter for the UserRole's role ID.
     * Checks that the role ID set by the setter is
     * properly retrieved by the getter.
     */
    @Test
    public void testRoleIdGetterSetter() {
        Integer expectedRoleId = C2;
        userRole.setRoleId(expectedRoleId);
        assertEquals(expectedRoleId, userRole.getRoleId());
    }

    /**
     * Tests the getter and setter for the UserRole's creation date.
     * Confirms that the creation date set by the setter is
     * correctly retrieved by the getter.
     */
    @Test
    public void testCreateDateGetterSetter() {
        Date expectedDate = new Date();
        userRole.setCreateDate(expectedDate);
        assertEquals(expectedDate, userRole.getCreateDate());
    }

    /**
     * Tests the getter and setter for the UserRole's update date.
     * Verifies that the update date set by the setter is
     * accurately retrieved by the getter.
     */
    @Test
    public void testUpdateDateGetterSetter() {
        Date expectedDate = new Date();
        userRole.setUpdateDate(expectedDate);
        assertEquals(expectedDate, userRole.getUpdateDate());
    }
}
