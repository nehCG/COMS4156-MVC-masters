package com.mvcmasters.ems.controller;

import com.mvcmasters.ems.base.ResultInfo;
import com.mvcmasters.ems.service.SharedDataService;
import com.mvcmasters.ems.model.SharedDataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

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
        ResultInfo expectedResponse = new ResultInfo();
        expectedResponse.setMsg("Announcement added successfully!");


        assertEquals(expectedResponse, responseEntity);
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
//    @Test
//    public void testGetAllSharedData() {
//        List<SharedDataModel> sharedDataList = new ArrayList<>();
//        when(sharedDataService.getAllSharedData()).thenReturn(sharedDataList);
//        ResponseEntity<List<SharedDataModel>> expectedResponse =
//                new ResponseEntity<>(sharedDataList, HttpStatus.OK);
//        ResponseEntity<List<SharedDataModel>> responseEntity =
//                sharedSpaceController.getAllSharedData();
//
//        assertEquals(expectedResponse, responseEntity);
//        verify(sharedDataService, times(1)).getAllSharedData();
//    }

    /**
     * Test the updateSharedData method.
     */
    @Test
    public void testUpdateSharedData() {
        Integer id = 1;
        SharedDataModel sharedData = new SharedDataModel();
        doNothing().when(sharedDataService).updateSharedData(id, sharedData);

        ResponseEntity<String> expectedResponse =
                new ResponseEntity<>(
                        "Shared data updated successfully!", HttpStatus.OK);
        ResponseEntity<String> responseEntity =
                sharedSpaceController.updateSharedData(id, sharedData);

        assertEquals(expectedResponse, responseEntity);
        verify(sharedDataService, times(1)).updateSharedData(id, sharedData);
    }

    /**
     * Test the deleteSharedDataById method.
     */
    @Test
    public void testDeleteSharedDataById() {
        Integer id = 1;
        doNothing().when(sharedDataService).deleteSharedDataById(id);

        ResponseEntity<String> expectedResponse =
                new ResponseEntity<>(
                        "Shared data deleted successfully!", HttpStatus.OK);
        ResponseEntity<String> responseEntity =
                sharedSpaceController.deleteSharedDataById(id);

        assertEquals(expectedResponse, responseEntity);
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
}
