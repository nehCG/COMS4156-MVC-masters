package com.mvcmasters.ems.external_integration.database;

import com.mvcmasters.ems.model.SharedDataModel;
import com.mvcmasters.ems.repository.SharedDataMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * External Integration Test of shared Space interaction
 * with MySQL database.
 */
@SpringBootTest
public class SharedDataRepositoryIntegrationTest {

    /**
     * Shared Data Mapper with External MySQL Database.
     */
    @Autowired
    private SharedDataMapper sharedDataMapper;

    /**
     * Test for Add and Select new data into database.
     */
    @Test
    public void testInsertAndSelectSharedData() {
        SharedDataModel newData = new SharedDataModel();
        newData.setSubject("Test Subject");
        newData.setContent("Test Content");

        sharedDataMapper.insertSharedData(newData);

        SharedDataModel retrievedData =
                sharedDataMapper.selectSharedDataById(1);
        assertNotNull(retrievedData);
    }

    /**
     * Test for select all shared data from the database.
     */
    @Test
    public void testSelectAllSharedData() {
        List<SharedDataModel> dataList = sharedDataMapper.selectAllSharedData();
        assertNotNull(dataList);
        assertFalse(dataList.isEmpty());
    }
    /**
     * Test for updated shared data in the database.
     */
    @Test
    public void testUpdateSharedData() {
        // Assume there's already data in the database with id = 1
        SharedDataModel existingData = sharedDataMapper.selectSharedDataById(1);
        assertNotNull(existingData);

        existingData.setSubject("Updated Subject");
        sharedDataMapper.updateSharedData(existingData);

        SharedDataModel updatedData = sharedDataMapper.selectSharedDataById(1);
        assertEquals("Updated Subject", updatedData.getSubject());
    }
    /**
     * Test for delete shared data in the database.
     */
    @Test
    public void testDeleteSharedData() {
        // Insert a new record and delete it
        SharedDataModel newData = new SharedDataModel();
        // set properties of newData
        sharedDataMapper.insertSharedData(newData);

        sharedDataMapper.deleteSharedDataById(newData.getId());
        SharedDataModel deletedData =
                sharedDataMapper.selectSharedDataById(newData.getId());
        assertNull(deletedData);
    }
}
