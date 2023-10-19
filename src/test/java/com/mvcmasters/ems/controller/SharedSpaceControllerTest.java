package com.mvcmasters.ems.controller;

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
import static org.mockito.Mockito.*;

public class SharedSpaceControllerTest {

    @Mock
    private SharedDataService sharedDataService;

    @InjectMocks
    private SharedSpaceController sharedSpaceController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddSharedData() {
        SharedDataModel sharedData = new SharedDataModel();
        doNothing().when(sharedDataService).addSharedData(sharedData);

        ResponseEntity<String> responseEntity = sharedSpaceController.addSharedData(sharedData);
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Shared data added successfully!", HttpStatus.OK);

        assertEquals(expectedResponse, responseEntity);
        verify(sharedDataService, times(1)).addSharedData(sharedData);
    }

    @Test
    public void testGetSharedDataById() {
        Integer id = 123;
        SharedDataModel sharedData = new SharedDataModel();
        when(sharedDataService.getSharedDataById(id)).thenReturn(sharedData);
        ResponseEntity<SharedDataModel> responseEntity = sharedSpaceController.getSharedDataById(id);
        ResponseEntity<SharedDataModel> expectedResponse = new ResponseEntity<>(sharedData, HttpStatus.OK);;

        assertEquals(expectedResponse, responseEntity);
        verify(sharedDataService, times(1)).getSharedDataById(id);
    }

    @Test
    public void testGetAllSharedData() {
        List<SharedDataModel> sharedDataList = new ArrayList<>();
        when(sharedDataService.getAllSharedData()).thenReturn(sharedDataList);
        ResponseEntity<List<SharedDataModel>> expectedResponse = new ResponseEntity<>(sharedDataList, HttpStatus.OK);
        ResponseEntity<List<SharedDataModel>> responseEntity = sharedSpaceController.getAllSharedData();

        assertEquals(expectedResponse, responseEntity);
        verify(sharedDataService, times(1)).getAllSharedData();
    }

    @Test
    public void testUpdateSharedData() {
        Integer id = 1;
        SharedDataModel sharedData = new SharedDataModel();
        doNothing().when(sharedDataService).updateSharedData(id, sharedData);

        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Shared data updated successfully!", HttpStatus.OK);
        ResponseEntity<String> responseEntity = sharedSpaceController.updateSharedData(id, sharedData);

        assertEquals(expectedResponse, responseEntity);
        verify(sharedDataService, times(1)).updateSharedData(id, sharedData);
    }

    @Test
    public void testDeleteSharedDataById() {
        Integer id = 1;
        doNothing().when(sharedDataService).deleteSharedDataById(id);

        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Shared data deleted successfully!", HttpStatus.OK);
        ResponseEntity<String> responseEntity = sharedSpaceController.deleteSharedDataById(id);

        assertEquals(expectedResponse, responseEntity);
        verify(sharedDataService, times(1)).deleteSharedDataById(id);
    }

}
