package com.mvcmasters.ems.controller;

import com.mvcmasters.ems.base.BaseQuery;
import com.mvcmasters.ems.base.ResultInfo;
import com.mvcmasters.ems.service.SharedDataService;
import com.mvcmasters.ems.model.SharedDataModel;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.mock;

/**
 * This class represents a test for the SharedSpaceController class.
 */
public class SharedSpaceControllerTest {

    /**
     * Mocked SharedDataService for testing SharedSpaceController.
     */
    @Mock
    private SharedDataService sharedDataService;

    /**
     * The SharedSpaceController instance to be tested.
     */
    @InjectMocks
    private SharedSpaceController sharedSpaceController;

    /**
     * Set up the test by initializing Mockito annotations.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test the addSharedData method.
     */
    @Test
    public void testAddSharedData() {
        SharedDataModel sharedData = new SharedDataModel();
        doNothing().when(sharedDataService).addSharedData(sharedData);

        ResultInfo responseEntity =
                sharedSpaceController.addSharedData(sharedData);

        assertEquals("Announcement added successfully!",
                responseEntity.getMsg());
        verify(sharedDataService, times(1)).addSharedData(sharedData);
    }

    /**
     * Test the getSharedDataById method.
     */
    @Test
    public void testGetSharedDataById() {
        Integer id = 1;
        SharedDataModel sharedData = new SharedDataModel();
        when(sharedDataService.getSharedDataById(id)).thenReturn(sharedData);
        ResponseEntity<SharedDataModel> responseEntity =
                sharedSpaceController.getSharedDataById(id);

        assertEquals(
                new ResponseEntity<>(
                        sharedData, HttpStatus.OK), responseEntity);
        verify(sharedDataService, times(1)).getSharedDataById(id);
    }

    /**
     * Test the getAllSharedData method.
     */
    @Test
    public void testGetAllSharedData() {
        BaseQuery query = new BaseQuery();
        Map<String, Object> expectedResult = new HashMap<>();
        when(sharedDataService.queryByParamsForTable(query)).
                thenReturn(expectedResult);
        Map<String, Object> response =
                sharedSpaceController.getAllSharedData(query);

        assertEquals(expectedResult, response);
        verify(sharedDataService, times(1)).queryByParamsForTable(query);
    }

    /**
     * Test the updateSharedData method.
     */
    @Test
    public void testUpdateSharedData() {
        Integer id = 1;
        SharedDataModel sharedData = new SharedDataModel();
        doNothing().when(sharedDataService).updateSharedData(id, sharedData);

        ResultInfo responseEntity =
                sharedSpaceController.updateSharedData(id, sharedData);

        assertEquals("Announcement updated successfully!",
                responseEntity.getMsg());
        verify(sharedDataService, times(1)).updateSharedData(id, sharedData);
    }

    /**
     * Test the deleteSharedDataById method.
     */
    @Test
    public void testDeleteSharedDataById() {
        Integer id = 1;
        doNothing().when(sharedDataService).deleteSharedDataById(id);

        ResultInfo responseEntity =
                sharedSpaceController.deleteSharedDataById(id);

        assertEquals("Announcement deleted successfully!",
                responseEntity.getMsg());
        verify(sharedDataService, times(1)).deleteSharedDataById(id);
    }

    /**
     * Test the index method.
     */
    @Test
    public void testIndex() {
        String viewName = sharedSpaceController.index();
        assertEquals("announcement/announcement", viewName);
    }

    /**
     * Test the toAddOrUpdateUserPage method.
     */
    @Test
    public void testToAddOrUpdateUserPage() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        Integer id = 1;
        SharedDataModel sharedData = new SharedDataModel();
        when(sharedDataService.getSharedDataById(id)).thenReturn(sharedData);

        String viewName = sharedSpaceController.
                toAddOrUpdateUserPage(id, request);
        assertEquals("announcement/add_update", viewName);

        // Test the scenario where id is null
        viewName = sharedSpaceController.toAddOrUpdateUserPage(null, request);
        assertEquals("announcement/add_update", viewName);
        // Ensure sharedDataService is not called when id is null
        verify(sharedDataService, times(0)).selectByPrimaryKey(null);

    }
}
