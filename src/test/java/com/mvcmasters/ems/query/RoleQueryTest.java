package com.mvcmasters.ems.query;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the {@link RoleQuery} class.
 * This test class verifies the functionality of
 * setting and getting the role name
 * in the RoleQuery class. It ensures that the
 * role name is correctly managed and
 * retrieved as expected.
 */
public class RoleQueryTest {

    /**
     * The RoleQuery instance used for testing.
     */
    private RoleQuery roleQuery;

    /**
     * Sets up the testing environment before each test.
     * Initializes a new instance of {@link RoleQuery}
     * for use in each test method.
     */
    @Before
    public void setUp() {
        roleQuery = new RoleQuery();
    }

    /**
     * Tests the setting and getting of a role name in {@link RoleQuery}.
     * Ensures that after setting a role name, the same value is returned when
     * getRoleName is called. This test verifies the basic functionality of the
     * setRoleName and getRoleName methods.
     */
    @Test
    public void testSetAndGetRoleName() {
        String testRoleName = "Administrator";
        roleQuery.setRoleName(testRoleName);
        assertEquals("Retrieving role name should match the set value",
                testRoleName, roleQuery.getRoleName());
    }

    /**
     * Tests the default state of the role name in {@link RoleQuery}.
     * Verifies that the role name is initially null before any value is set.
     * This test checks the initial state of the roleName field in RoleQuery.
     */
    @Test
    public void testDefaultRoleName() {
        assertNull("Initially, role name should be null",
                roleQuery.getRoleName());
    }

}

