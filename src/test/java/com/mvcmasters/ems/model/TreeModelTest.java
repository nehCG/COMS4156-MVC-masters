package com.mvcmasters.ems.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Test class for {@link TreeModel}.
 * This class tests the functionality of getters
 * and setters in the TreeModel class.
 */
public class TreeModelTest {

    /**
     * An instance of TreeModel used for testing.
     * This instance is initialized in the setUp method before each test.
     */
    private TreeModel treeModel;

    /**
     * Sets up the test environment before each test method.
     * Initializes a new TreeModel instance.
     */
    @Before
    public void setUp() {
        treeModel = new TreeModel();
    }

    /**
     * Tests both the setId and getId methods of TreeModel.
     * Verifies if the ID set is correctly retrieved.
     */
    @Test
    public void testSetAndGetId() {
        Integer id = 1;
        treeModel.setId(id);
        assertEquals(id, treeModel.getId());
    }

    /**
     * Tests both the setpId and getpId methods of TreeModel.
     * Verifies if the parent ID set is correctly retrieved.
     */
    @Test
    public void testSetAndGetPId() {
        Integer pId = 2;
        treeModel.setpId(pId);
        assertEquals(pId, treeModel.getpId());
    }

    /**
     * Tests both the setName and getName methods of TreeModel.
     * Verifies if the name set is correctly retrieved.
     */
    @Test
    public void testSetAndGetName() {
        String name = "Test Name";
        treeModel.setName(name);
        assertEquals(name, treeModel.getName());
    }

    /**
     * Tests both the setChecked and isChecked methods of TreeModel.
     * Verifies if the checked status set is correctly retrieved.
     */
    @Test
    public void testSetAndGetChecked() {
        treeModel.setChecked(true);
        assertTrue(treeModel.isChecked());

        treeModel.setChecked(false);
        assertFalse(treeModel.isChecked());
    }
}
