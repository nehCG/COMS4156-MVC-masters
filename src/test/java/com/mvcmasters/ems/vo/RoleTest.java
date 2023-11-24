package com.mvcmasters.ems.vo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;

/**
 * This class contains unit tests for the Role class.
 */
public class RoleTest {

    /**
     * The Role instance used for testing in the RoleTest class.
     */
    private Role role;

    /**
     * Initializes a new Role instance before each test.
     */
    @Before
    public void setUp() {
        role = new Role();
    }

    /**
     * Tests the getter and setter for the role ID.
     * Verifies that setting an ID is correctly retrieved
     * by the corresponding getter.
     */
    @Test
    public void testIdGetterSetter() {
        Integer expectedId = 1;
        role.setId(expectedId);
        assertEquals("Getter for ID should retrieve what was set by setter",
                expectedId, role.getId());
    }

    /**
     * Tests the getter and setter for the role name with trimming logic.
     * Ensures that the role name is correctly trimmed when set
     * and accurately retrieved.
     */
    @Test
    public void testRoleNameGetterSetter() {
        String expectedRoleName = "Administrator";
        role.setRoleName("  Administrator  ");
        assertEquals(
        "Getter for role name should retrieve the trimmed string set by setter",
                expectedRoleName, role.getRoleName());
    }

    /**
     * Tests the getter and setter for the role remark with trimming logic.
     * Checks that the role remark is properly trimmed
     * when set and precisely retrieved.
     */
    @Test
    public void testRoleRemarkGetterSetter() {
        String expectedRoleRemark = "Primary Admin Role";
        role.setRoleRemark("  Primary Admin Role  ");
        assertEquals(
        "Getter for role remark should retrieve "
                + "the trimmed string set by setter",
                expectedRoleRemark, role.getRoleRemark());
    }

    /**
     * Tests the getter and setter for the role creation date.
     * Ensures that the creation date is correctly set and retrieved.
     */
    @Test
    public void testCreateDateGetterSetter() {
        Date expectedDate = new Date();
        role.setCreateDate(expectedDate);
        assertEquals("Getter for create date should "
                        + "retrieve what was set by setter",
                expectedDate, role.getCreateDate());
    }

    /**
     * Tests the getter and setter for the role update date.
     * Verifies that the update date is accurately set and retrieved.
     */
    @Test
    public void testUpdateDateGetterSetter() {
        Date expectedDate = new Date();
        role.setUpdateDate(expectedDate);
        assertEquals("Getter for update date should "
                + "retrieve what was set by setter",
                expectedDate, role.getUpdateDate());
    }

    /**
     * Tests the getter and setter for the role validity status.
     * Confirms that setting a validity status is
     * correctly reflected when retrieved.
     */
    @Test
    public void testIsValidGetterSetter() {
        Integer expectedValid = 1;
        role.setIsValid(expectedValid);
        assertEquals("Getter for isValid should retrieve"
                + " what was set by setter",
                expectedValid, role.getIsValid());
    }

    /**
     * Tests the role remark setter with a null input.
     * Verifies that setting a null role remark
     * results in a null value when retrieved.
     */
    @Test
    public void testSetRoleRemarkWithNull() {
        role.setRoleRemark(null);
        assertNull("Setting role remark as null should"
                + " result in a null value",
                role.getRoleRemark());
    }

    /**
     * Tests the role remark setter with non-null input.
     * Checks that a non-null remark is properly
     * trimmed and accurately retrieved.
     */
    @Test
    public void testSetRoleRemarkWithNonNull() {
        String remark = "  Primary Admin Role  ";
        role.setRoleRemark(remark);
        assertEquals("Setting a non-null role remark "
                + "should trim the input",
                "Primary Admin Role", role.getRoleRemark());
    }

    /**
     * Tests the role name setter with a null input.
     * Ensures that setting a null role name results
     * in a null value when retrieved.
     */
    @Test
    public void testSetRoleNameWithNull() {
        role.setRoleName(null);
        assertNull("Setting role name as null should "
                + "result in a null value", role.getRoleName());
    }

    /**
     * Tests the role name setter with non-null input.
     * Validates that a non-null name is correctly trimmed and retrieved.
     */
    @Test
    public void testSetRoleNameWithNonNull() {
        String name = "  Administrator  ";
        role.setRoleName(name);
        assertEquals("Setting a non-null role name should"
                + " trim the input", "Administrator", role.getRoleName());
    }
}
