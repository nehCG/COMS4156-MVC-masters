package com.mvcmasters.ems.service;

import com.mvcmasters.ems.model.SharedDataModel;
import com.mvcmasters.ems.model.UserModel;
import com.mvcmasters.ems.repository.SharedDataMapper;
import com.mvcmasters.ems.repository.UserMapper;
import com.mvcmasters.ems.utils.Md5Util;
import com.mvcmasters.ems.vo.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    public void testGetSharedDataById() {
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

        when(sharedDataMapper.selectSharedDataById(id)).thenReturn(sharedData);
        doNothing().when(sharedDataMapper).updateSharedData(sharedData);

        sharedDataService.updateSharedData(id, sharedData);

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
    public void testValidateSharedData() {
        // Prepare a shared data model
        SharedDataModel sharedData = new SharedDataModel();
        sharedData.setCreatedTime(LocalDateTime.now());
        sharedData.setSubject("abc");
        sharedData.setContent("abc");

        sharedDataService.validateSharedData(sharedData);
    }
}