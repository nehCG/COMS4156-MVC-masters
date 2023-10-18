package com.mvcmasters.ems.service;

import com.mvcmasters.ems.model.SharedDataModel;
import com.mvcmasters.ems.model.UserModel;
import com.mvcmasters.ems.repository.SharedDataMapper;
import com.mvcmasters.ems.exceptions.CustomException;
import com.mvcmasters.ems.repository.UserMapper;
import com.mvcmasters.ems.utils.Md5Util;
import com.mvcmasters.ems.vo.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SharedDataServiceTest {

    @InjectMocks
    private SharedDataService sharedDataService;

    @Mock
    private SharedDataMapper sharedDataMapper;

    @BeforeEach
    public void setUp() {
        //MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddSharedData() {
        // Prepare a shared data model
        SharedDataModel sharedData = new SharedDataModel();
        sharedData.setCreatedTime(LocalDateTime.now());
        sharedData.setSubject("abc");
        sharedData.setContent("abc");

        doNothing().when(sharedDataMapper).insertSharedData(sharedData);

        sharedDataService.addSharedData(sharedData);
    }

    @Test
    public void testGetSharedDataByIdWithValidId() {
        // Prepare a shared data model
        SharedDataModel sharedData = new SharedDataModel();
        sharedData.setCreatedTime(LocalDateTime.now());
        sharedData.setSubject("abc");
        sharedData.setContent("abc");

        when(sharedDataMapper.selectSharedDataById(1)).thenReturn(sharedData);

        SharedDataModel response = sharedDataService.getSharedDataById(1);
        assertEquals(sharedData, response);
    }

    @Test
    public void testGetSharedDataByIdWithInvalidID() {
        Integer id = 10000;
        when(sharedDataMapper.selectSharedDataById(id)).thenReturn(null);
        Exception exception = assertThrows(CustomException.class, () -> {
            sharedDataService.getSharedDataById(id);
        });

        assertEquals("Record ID does not exist", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, ((CustomException) exception).getStatusCode());
    }

    @Test
    public void testGetAllSharedData() {
        when(sharedDataMapper.selectAllSharedData()).thenReturn(null);
        sharedDataService.getAllSharedData();
    }

    @Test
    public void testUpdateSharedData() {
        // Prepare a shared data model
        SharedDataModel sharedData = new SharedDataModel();
        sharedData.setCreatedTime(LocalDateTime.now());
        sharedData.setSubject("abc");
        sharedData.setContent("abc");

        // Prepare id
        Integer id = 1;

        // Prepare another shared data model for updated data
        SharedDataModel newSharedData = new SharedDataModel();
        newSharedData.setSubject("updated");
        newSharedData.setContent("updated");

        when(sharedDataMapper.selectSharedDataById(id)).thenReturn(sharedData);
        doNothing().when(sharedDataMapper).updateSharedData(newSharedData);

        sharedDataService.updateSharedData(id, newSharedData);
    }

    @Test
    public void testUpdateSharedDataWithNullData() {
        // Arrange
        int id = 1;
        SharedDataModel existingData = new SharedDataModel();
        when(sharedDataMapper.selectSharedDataById(id)).thenReturn(existingData);

        // Act and Assert
        CustomException thrown = assertThrows(CustomException.class, () -> {
            sharedDataService.updateSharedData(id, new SharedDataModel());
        });

        assertEquals("Updates can not be null", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }
    @Test
    public void testUpdateSharedDataWithInvalidId(){
        Integer id = 10000;
        when(sharedDataMapper.selectSharedDataById(id)).thenReturn(null);

        SharedDataModel newData = new SharedDataModel();
        newData.setSubject("New Subject");
        newData.setContent("New Content");

        CustomException thrown = assertThrows(CustomException.class, () -> {
            sharedDataService.updateSharedData(id, newData);
        });

        assertEquals("Record ID does not exist", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }

    @Test
    public void testDeleteSharedDataById() {
        // Prepare a shared data model
        SharedDataModel sharedData = new SharedDataModel();
        sharedData.setCreatedTime(LocalDateTime.now());
        sharedData.setSubject("abc");
        sharedData.setContent("abc");

        Integer id = 1;

        when(sharedDataMapper.selectSharedDataById(id)).thenReturn(sharedData);
        doNothing().when(sharedDataMapper).deleteSharedDataById(id);

        sharedDataService.deleteSharedDataById(id);
    }
    @Test
    public void testDeleteSharedDataWithInvalidId(){
        Integer id = 10000;
        when(sharedDataMapper.selectSharedDataById(id)).thenReturn(null);

        CustomException thrown = assertThrows(CustomException.class, () -> {
            sharedDataService.deleteSharedDataById(id);
        });

        assertEquals("Record ID does not exist", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }

    @Test
    public void testValidateSharedData() {
        // Prepare a shared data model
        SharedDataModel sharedData = new SharedDataModel();
        sharedData.setCreatedTime(LocalDateTime.now());
        sharedData.setSubject("abc");
        sharedData.setContent("abc");

        sharedDataService.validateSharedData(sharedData);
    }

    @Test
    public void testValidateSharedDataWithNullData() {
        CustomException thrown = assertThrows(
                CustomException.class,
                () -> sharedDataService.validateSharedData(null)
        );

        assertEquals("Shared data cannot be null", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }

    @Test
    public void testValidateSharedDataWithEmptySubject() {
        // Prepare data with empty subject
        SharedDataModel sharedData = new SharedDataModel();
        sharedData.setSubject("");

        CustomException thrown = assertThrows(
                CustomException.class,
                () -> sharedDataService.validateSharedData(sharedData)
        );

        assertEquals("Subject cannot be empty", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }

    @Test
    public void testValidateSharedDataWithEmptyContent() {
        // Prepare data with empty content
        SharedDataModel sharedData = new SharedDataModel();
        sharedData.setSubject("subjecttest");
        sharedData.setContent("");

        CustomException thrown = assertThrows(
                CustomException.class,
                () -> sharedDataService.validateSharedData(sharedData)
        );

        assertEquals("Content cannot be empty", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }
}