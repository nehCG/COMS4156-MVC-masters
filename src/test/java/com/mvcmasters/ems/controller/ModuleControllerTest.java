package com.mvcmasters.ems.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.mvcmasters.ems.model.TreeModel;
import com.mvcmasters.ems.service.ModuleService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for {@link ModuleController}.
 * This class uses Mockito to mock the {@link ModuleService}
 * and tests the functionality of the controller.
 */
public class ModuleControllerTest {

    /**
     * Mock of ModuleService used for handling module operations.
     */
    @Mock
    private ModuleService moduleService;

    /**
     * Instance of ModuleController under test.
     */
    @InjectMocks
    private ModuleController moduleController;

    /**
     * Initializes Mockito annotations before each test case.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Tests the {@code queryAllModules} method of {@link ModuleController}.
     * Ensures that the method returns the expected
     * list of modules for a given role ID.
     */
    @Test
    public void testQueryAllModules() {
        Integer roleId = 1; // Example role ID
        List<TreeModel> expectedModules = new ArrayList<>();
        // Populate expectedModules as needed for testing

        when(moduleService.queryAllModules(roleId)).thenReturn(expectedModules);

        List<TreeModel> actualModules =
                moduleController.queryAllModules(roleId);
        assertEquals(expectedModules, actualModules);
    }

    /**
     * Tests the {@code toAddGrantPage} method of {@link ModuleController}.
     * Ensures that the method returns the correct
     * view path and sets the appropriate attributes.
     */
    @Test
    public void testToAddGrantPage() {
        Integer roleId = 1; // Example role ID
        MockHttpServletRequest request = new MockHttpServletRequest();

        String viewPath = moduleController.toAddGrantPage(roleId, request);
        assertEquals("role/grant", viewPath);
        assertEquals(roleId, request.getAttribute("roleId"));
    }
}

