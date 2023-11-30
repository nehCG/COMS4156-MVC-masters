package com.mvcmasters.ems.internal_integration.ServiceRepositoryIntegration;

import com.mvcmasters.ems.exceptions.CustomException;
import com.mvcmasters.ems.model.SharedDataModel;
import com.mvcmasters.ems.repository.SharedDataMapper;
import com.mvcmasters.ems.service.SharedDataService;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/**
 * Test shared Data service layer and its interaction
 * with the Spring context while mocking away the database layer.
 */
@SpringBootTest
public class SharedDataServiceIntegrationTest {
    /**
     * SharedDataService to be tested.
     */
    @Autowired
    private SharedDataService sharedDataService;
    /**
     * Isolating external database using MockBean
     */
    @MockBean
    private SharedDataMapper sharedDataMapper;

    /**
     * Test the AddSharedData Method
     * Verifies if the shared data is successfully added by
     * using real service layer.
     */
    @Test
    public void testAddSharedDataIntegration() {
        SharedDataModel sharedData = new SharedDataModel();
        sharedData.setSubject("Test Subject");
        sharedData.setContent("Test Content");
        sharedData.setUid(1); // Assuming this user ID exists in your system

        // Act
        sharedDataService.addSharedData(sharedData);

        verify(sharedDataMapper).insertSharedData(sharedData);
        assertNotNull(sharedData.getCreatedTime());
    }

    /**
     * Test the GetSharedDatabyID Method
     * Verifies if the shared data is successfully retrieved by
     * using real service class.
     */
    @Test
    public void testGetSharedDatabyIDIntegration() {
        // Arrange
        Integer id = 1; // Example ID
        SharedDataModel expectedData = new SharedDataModel();
        expectedData.setId(id);
        expectedData.setSubject("Test Subject");
        expectedData.setContent("Test Content");

        when(sharedDataMapper.selectSharedDataById(id))
                .thenReturn(expectedData);

        // Act
        SharedDataModel actualData = sharedDataService.getSharedDataById(id);

        // Assert
        assertNotNull(actualData);
        assertEquals(expectedData, actualData);
    }

    /**
     * Test the GetSharedDatabyID Method with invalidID
     * Verifies if the shared data is not retrieved by
     * using real service class.
     */
    @Test
    public void testGetSharedDataByIdAndNotFoundIntegration() {
        // Arrange
        Integer id = 1; // Example ID does not exists
        when(sharedDataMapper.selectSharedDataById(id)).thenReturn(null);

        // Act & Assert
        CustomException thrown =
                Assertions.assertThrows(CustomException.class, () -> {
            sharedDataService.getSharedDataById(id);
        });



        assertEquals("Record ID does not exist", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }

    /**
     * Test the GetSharedDatabyID Method
     * Verifies if all shared data are successfully retrieved by
     * using real service class.
     */
    @Test
    public void testGetAllSharedDataIntegration() {
        // Arrange
        List<SharedDataModel> expectedDataList = new ArrayList<>();
        expectedDataList.add(new SharedDataModel());
        when(sharedDataMapper.selectAllSharedData())
                .thenReturn(expectedDataList);

        // Act
        List<SharedDataModel> actualDataList
                = sharedDataService.getAllSharedData();

        // Assert
        assertNotNull(actualDataList);
        assertFalse(actualDataList.isEmpty());
        assertEquals(expectedDataList.size(), actualDataList.size());
    }

    /**
     * Test the GetSharedDatabyID Method
     * Verifies if all shared data are successfully retrieved by
     * using real service class.
     */
    @Test
    public void testGetAllSharedDataEmptyListIntegration() {
        // Arrange
        List<SharedDataModel> expectedDataList = Collections.emptyList();
        when(sharedDataMapper.selectAllSharedData())
                .thenReturn(expectedDataList);

        // Act
        List<SharedDataModel> actualDataList =
                sharedDataService.getAllSharedData();

        // Assert
        assertNotNull(actualDataList);
        assertTrue(actualDataList.isEmpty());
    }

    /**
     * Test the UpdateSharedMethod Method
     * Verifies When Data Does Not Exist
     * using real service class.
     */
    @Test
    public void testUpdateNonExistentDataIntegration() {
        Integer id = 1; // Non-existent ID
        SharedDataModel newData = new SharedDataModel();

        when(sharedDataMapper.selectSharedDataById(id)).thenReturn(null);

        CustomException thrown = assertThrows(CustomException.class,
                () -> sharedDataService.updateSharedData(id, newData));

        assertEquals("Record ID does not exist", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }

    /**
     * Test the UpdateSharedMethod Method
     * Verifies Test When Content and Subject Are Null
     * using real service class.
     */
    @Test
    public void testUpdateDataWithNullContentAndSubjectIntegration() {
        Integer id = 1;
        SharedDataModel existingData = new SharedDataModel();
        SharedDataModel newData = new SharedDataModel();

        when(sharedDataMapper.selectSharedDataById(id))
                .thenReturn(existingData);

        CustomException thrown = assertThrows(CustomException.class,
                () -> sharedDataService.updateSharedData(id, newData));

        assertEquals("Updates can not be null", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }

    /**
     * Test the UpdateSharedMethod Method
     * Verifies Test When User ID Is Null
     * using real service class.
     */
    @Test
    public void whenUpdateDataWithNullUserIdIntegration() {
        Integer id = 1;
        SharedDataModel existingData = new SharedDataModel();
        SharedDataModel newData = new SharedDataModel();
        newData.setContent("Test Content");
        newData.setSubject("Test Subject");
        // UID is null

        when(sharedDataMapper.selectSharedDataById(id))
                .thenReturn(existingData);

        CustomException thrown = assertThrows(CustomException.class,
                () -> sharedDataService.updateSharedData(id, newData));

        assertEquals("User ID cannot be null", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }

    /**
     * Test the UpdateSharedMethod Method
     * Verifies Test Successful Update
     * using real service class.
     */
    @Test
    public void testUpdateValidDataIntegration() {
        Integer id = 1;
        SharedDataModel existingData = new SharedDataModel();
        SharedDataModel newData = new SharedDataModel();
        newData.setContent("Test Content");
        newData.setSubject("Test Subject");
        newData.setUid(2); // Valid user ID

        when(sharedDataMapper.selectSharedDataById(id))
                .thenReturn(existingData);

        sharedDataService.updateSharedData(id, newData);

        verify(sharedDataMapper).updateSharedData(newData);
    }

    /**
     * Test the deleteSharedMethod Method
     * Verifies Test When Data Does Not Exist
     * using real service class.
     */
    @Test
    public void testDeleteNonExistentDataIntegration() {
        Integer id = 1; // Non-existent ID

        when(sharedDataMapper.selectSharedDataById(id)).thenReturn(null);

        CustomException thrown = assertThrows(CustomException.class,
                () -> sharedDataService.deleteSharedDataById(id));

        assertEquals("Record ID does not exist", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    }

    /**
     * Test the deleteSharedMethod Method
     * Verifies Test Successful Deletion
     * using real service class.
     */
    @Test
    public void testDeleteExistingDataIntegration() {
        Integer id = 1;
        SharedDataModel existingData = new SharedDataModel();

        when(sharedDataMapper.selectSharedDataById(id))
                .thenReturn(existingData);

        sharedDataService.deleteSharedDataById(id);

        verify(sharedDataMapper).deleteSharedDataById(id);
    }






}
