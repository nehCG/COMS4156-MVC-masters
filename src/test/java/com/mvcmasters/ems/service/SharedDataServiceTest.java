package com.mvcmasters.ems.service;

import com.mvcmasters.ems.base.BaseQuery;
import com.mvcmasters.ems.model.SharedDataModel;
import com.mvcmasters.ems.repository.SharedDataMapper;
import com.mvcmasters.ems.exceptions.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;


@ExtendWith(MockitoExtension.class)
public class SharedDataServiceTest {

    /**
     * The service being tested, with dependencies mocked.
     */
    @InjectMocks
    private SharedDataService sharedDataService;

    /**
     * A mock of the shared-data data access object.
     */
    @Mock
    private SharedDataMapper sharedDataMapper;

    /**
     * Set up mockito annotations for each test.
     */
    @BeforeEach
    public void setUp() {
        //MockitoAnnotations.initMocks(this);
    }

    /**
     * Test for addSharedData.
     */
    @Test
    public void testAddSharedData() {
        // Prepare a shared data model
        SharedDataModel sharedData = new SharedDataModel();
        sharedData.setCreatedTime(LocalDateTime.now());
        sharedData.setSubject("abc");
        sharedData.setContent("abc");
        sharedData.setUid(1);

        doNothing().when(sharedDataMapper).insertSharedData(sharedData);

        sharedDataService.addSharedData(sharedData);
    }

    /**
     * Test for getSharedDataById with valid id.
     */
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

    /**
     * Test for getSharedDataById with invalid id.
     */
    @Test
    public void testGetSharedDataByIdWithInvalidID() {
        Integer id = 1;
        when(sharedDataMapper.selectSharedDataById(id)).thenReturn(null);
        Exception exception = assertThrows(CustomException.class, () -> {
            sharedDataService.getSharedDataById(id);
        });

        assertEquals("Record ID does not exist", exception.getMessage());
        assertEquals(
                HttpStatus.BAD_REQUEST,
                ((CustomException) exception).getStatusCode());
    }

    /**
     * Test for getAllSharedData.
     */
    @Test
    public void testGetAllSharedData() {
        when(sharedDataMapper.selectAllSharedData()).thenReturn(null);
        sharedDataService.getAllSharedData();
    }

    /**
     * Test for updateSharedData.
     */
    @Test
    public void testUpdateSharedData() {
        // Prepare a shared data model
        SharedDataModel sharedData = new SharedDataModel();
        sharedData.setCreatedTime(LocalDateTime.now());
        sharedData.setSubject("abc");
        sharedData.setContent("abc");
        sharedData.setUid(1);

        // Prepare id
        Integer id = 1;

        // Prepare another shared data model for updated data
        SharedDataModel newSharedData = new SharedDataModel();
        newSharedData.setSubject("updated");
        newSharedData.setContent("updated");
        newSharedData.setUid(1);

        when(sharedDataMapper.selectSharedDataById(id)).thenReturn(sharedData);
        doNothing().when(sharedDataMapper).updateSharedData(newSharedData);

        sharedDataService.updateSharedData(id, newSharedData);
    }

    /**
     * Test for updateSharedData with null data.
     */
    @Test
    public void testUpdateSharedDataWithNullData() {
        // Arrange
        int id = 1;
        SharedDataModel existingData = new SharedDataModel();
        when(sharedDataMapper.selectSharedDataById(id)).
                thenReturn(existingData);

        // Act and Assert
        CustomException thrown = assertThrows(CustomException.class, () -> {
            sharedDataService.updateSharedData(id, new SharedDataModel());
        });

        assertEquals("Updates can not be null", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }

    /**
     * Test for updateSharedData with invalid id.
     */
    @Test
    public void testUpdateSharedDataWithInvalidId() {
        Integer id = 1;
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

    /**
     * Test for updateSharedData with null content and subject.
     */
    @Test
    public void testUpdateSharedDataWithNullContentAndSubject() {
        Integer id = 1;
        when(sharedDataMapper.selectSharedDataById(id)).
                thenReturn(new SharedDataModel());

        SharedDataModel newData = new SharedDataModel();

        CustomException thrown = assertThrows(CustomException.class, () -> {
            sharedDataService.updateSharedData(id, newData);
        });

        assertEquals("Updates can not be null", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }

    /**
     * Test for updateSharedData with not null content.
     */
    @Test
    public void testUpdateSharedDataWithNonNullContent() {
        Integer id = 1;
        when(sharedDataMapper.selectSharedDataById(id)).
                thenReturn(new SharedDataModel());

        SharedDataModel newData = new SharedDataModel();
        newData.setContent("Some content");
        newData.setUid(1);

        assertDoesNotThrow(() -> {
            sharedDataService.updateSharedData(id, newData);
        });
    }

    /**
     * Test for updateSharedData with null subject.
     */
    @Test
    public void testUpdateSharedDataWithNonNullSubject() {
        Integer id = 1;
        when(sharedDataMapper.selectSharedDataById(id)).
                thenReturn(new SharedDataModel());

        SharedDataModel newData = new SharedDataModel();
        newData.setSubject("Some subject");
        newData.setUid(1);

        assertDoesNotThrow(() -> {
            sharedDataService.updateSharedData(id, newData);
        });
    }

    /**
     * Test for updateSharedData with null uid.
     */
    @Test
    public void testUpdateSharedDataWithNullUid() {
        Integer id = 1;
        when(sharedDataMapper.selectSharedDataById(id)).
                thenReturn(new SharedDataModel());

        SharedDataModel newData = new SharedDataModel();
        newData.setSubject("New Subject");
        newData.setContent("New Content");

        CustomException thrown = assertThrows(CustomException.class, () -> {
            sharedDataService.updateSharedData(id, newData);
        });

        assertEquals("User ID cannot be null", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }

    /**
     * Test for deleteSharedDataById.
     */
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

    /**
     * Test for deleteSharedData with invalid id.
     */
    @Test
    public void testDeleteSharedDataWithInvalidId() {
        Integer id = 1;
        when(sharedDataMapper.selectSharedDataById(id)).thenReturn(null);

        CustomException thrown = assertThrows(CustomException.class, () -> {
            sharedDataService.deleteSharedDataById(id);
        });

        assertEquals("Record ID does not exist", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }

    /**
     * Test for validateSharedData.
     */
    @Test
    public void testValidateSharedData() {
        // Prepare a shared data model
        SharedDataModel sharedData = new SharedDataModel();
        sharedData.setCreatedTime(LocalDateTime.now());
        sharedData.setSubject("abc");
        sharedData.setContent("abc");
        sharedData.setUid(1);

        sharedDataService.validateSharedData(sharedData);
    }

    /**
     * Test for validateSharedData with null data.
     */
    @Test
    public void testValidateSharedDataWithNullData() {
        CustomException thrown = assertThrows(
                CustomException.class,
                () -> sharedDataService.validateSharedData(null)
        );

        assertEquals("Shared data cannot be null", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }

    /**
     * Test for validateSharedData with empty subject.
     */
    @Test
    public void testValidateSharedDataWithEmptySubject() {
        // Prepare data with empty subject
        SharedDataModel sharedData = new SharedDataModel();
        sharedData.setSubject("");
        sharedData.setUid(1);

        CustomException thrown = assertThrows(
                CustomException.class,
                () -> sharedDataService.validateSharedData(sharedData)
        );

        assertEquals("Subject cannot be empty", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }

    /**
     * Test for validateSharedData with null subject.
     */
    @Test
    public void testValidateSharedDataWithNullSubject() {
        // Prepare data with null content
        SharedDataModel sharedData = new SharedDataModel();
        sharedData.setContent("subjecttest");
        sharedData.setSubject(null); // set content to null here
        sharedData.setUid(1);

        CustomException thrown = assertThrows(
                CustomException.class,
                () -> sharedDataService.validateSharedData(sharedData)
        );

        assertEquals("Subject cannot be empty", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }

    /**
     * Test for validateSharedData with empty content.
     */
    @Test
    public void testValidateSharedDataWithEmptyContent() {
        // Prepare data with empty content
        SharedDataModel sharedData = new SharedDataModel();
        sharedData.setSubject("subjecttest");
        sharedData.setContent("");
        sharedData.setUid(1);

        CustomException thrown = assertThrows(
                CustomException.class,
                () -> sharedDataService.validateSharedData(sharedData)
        );

        assertEquals("Content cannot be empty", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }

    /**
     * Test for validateSharedData with null content.
     */
    @Test
    public void testValidateSharedDataWithNullContent() {
        // Prepare data with null content
        SharedDataModel sharedData = new SharedDataModel();
        sharedData.setSubject("subjecttest");
        sharedData.setContent(null); // set content to null here
        sharedData.setUid(1);

        CustomException thrown = assertThrows(
                CustomException.class,
                () -> sharedDataService.validateSharedData(sharedData)
        );

        assertEquals("Content cannot be empty", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }

    /**
     * Test for validateSharedData with empty uid.
     */
    @Test
    public void testValidateSharedDataWithEmptyUid() {
        // Prepare data with empty content
        SharedDataModel sharedData = new SharedDataModel();
        sharedData.setSubject("subjecttest");
        sharedData.setContent("");

        CustomException thrown = assertThrows(
                CustomException.class,
                () -> sharedDataService.validateSharedData(sharedData)
        );

        assertEquals("User ID cannot be null", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }

    /**
     * Test for validateSharedData with not empty id.
     */
    @Test
    public void testValidateSharedDataWithNotEmptyId() {
        // Prepare data with empty content
        SharedDataModel sharedData = new SharedDataModel();
        sharedData.setSubject("subjecttest");
        sharedData.setContent("");
        sharedData.setId(1);

        CustomException thrown = assertThrows(
                CustomException.class,
                () -> sharedDataService.validateSharedData(sharedData)
        );

        assertEquals("ID will be automatically assigned", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }

    /**
     * Test for queryByParamsForTable.
     */
    @Test
    public void testQueryByParamsForTable() {
        Map<String, Object> expectResult = new HashMap<>();
        expectResult.put("code", 0);
        expectResult.put("msg", "");

        BaseQuery query = new BaseQuery();
        List<SharedDataModel> sharedDataList = new ArrayList<>();

        when(sharedDataMapper.selectAllSharedData()).
                thenReturn(sharedDataList);
        Map<String, Object> response = sharedDataService.
                queryByParamsForTable(query);

        assertEquals(expectResult.get("code"), response.get("code"));
        assertEquals(expectResult.get("msg"), response.get("msg"));
    }

}
