package com.mvcmasters.ems.service;

import com.mvcmasters.ems.repository.PermissionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Test class for PermissionService.
 * This class uses Mockito to mock the PermissionMapper
 * and tests the functionality of the PermissionService.
 */
public class PermissionServiceTest {

    /**
     * A mock of PermissionMapper to simulate
     * the data access layer for permissions.
     */
    @Mock
    private PermissionMapper permissionMapper;

    /**
     * An instance of PermissionService with injected mocks.
     * This object is the subject under test.
     */
    @InjectMocks
    private PermissionService permissionService;

    /**
     * Sets up the test environment before each test method.
     * Initializes Mockito annotations for mock and injectMocks usage.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the queryUserHasRoleHasPermissionByUserId
     * method of PermissionService.
     * Verifies that the method correctly
     * returns a list of permissions for a given user ID.
     */
    @Test
    public void testQueryUserHasRoleHasPermissionByUserId() {
        Integer userId = 1;
        List<String> expectedPermissions =
                Arrays.asList("PERMISSION_1", "PERMISSION_2");

        // Setup mock behavior
        when(permissionMapper.
                queryUserHasRoleHasPermissionByUserId(userId)).
                thenReturn(expectedPermissions);

        // Execute the method being tested
        List<String> actualPermissions =
                permissionService.queryUserHasRoleHasPermissionByUserId(userId);

        // Assert the expected results
        assertEquals(expectedPermissions, actualPermissions);
    }
}

