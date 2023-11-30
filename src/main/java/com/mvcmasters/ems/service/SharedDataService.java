package com.mvcmasters.ems.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mvcmasters.ems.base.BaseQuery;
import com.mvcmasters.ems.base.BaseService;
import com.mvcmasters.ems.exceptions.CustomException;
import com.mvcmasters.ems.model.SharedDataModel;
import com.mvcmasters.ems.repository.SharedDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service layer for shared data related operations.
 */
@Service
public class SharedDataService extends BaseService<SharedDataModel, Integer> {
    /**
     * Mapper for shared data-related database operations.
     */
    @Autowired
    private SharedDataMapper sharedDataMapper;

    /**
     * Handles adding new shared data logic.
     *
     * @param sharedData the shared data to be added.
     */
    public void addSharedData(final SharedDataModel sharedData) {
        // Set the created time to the current LocalDateTime
        sharedData.setCreatedTime(LocalDateTime.now());

        // Validate the shared data before adding it
        validateSharedData(sharedData);

        // Insert the shared data using the sharedDataMapper
        sharedDataMapper.insertSharedData(sharedData);
    }

    /**
     * Retrieve a shared data by its id.
     *
     * @param id shared data id.
     * @return a shared data with the corresponding id.
     */
    public SharedDataModel getSharedDataById(final Integer id) {
        // Retrieve shared data from the data source using sharedDataMapper
        SharedDataModel data = sharedDataMapper.selectSharedDataById(id);

        // If the data is not found, throw a CustomException
        // with a Bad Request status
        if (data == null) {
            throw new CustomException("Record ID does not exist",
                    HttpStatus.BAD_REQUEST);
        }

        // Return the retrieved shared data
        return data;
    }

    /**
     * Retrieve all shared data in the database.
     *
     * @return all shared data in the database.
     */
   public List<SharedDataModel> getAllSharedData() {
        return sharedDataMapper.selectAllSharedData();
   }

   /**
    * Handles shared data query.
    *
    * @param baseQuery the query of the data.
    * @return a map of query results
    */
   public Map<String, Object> queryByParamsForTable(final BaseQuery baseQuery) {
        Map<String, Object> result = new HashMap<>();
        // Initialize the pagination mechanism using the page number and limit
        PageHelper.startPage(baseQuery.getPage(), baseQuery.getLimit());

        // Fetch the paginated results
       // and organize them into a PageInfo structure
        List<SharedDataModel> sharedDataList = sharedDataMapper.
                selectAllSharedData();
        PageInfo<SharedDataModel> pageInfo = new PageInfo<>(sharedDataList);

        System.out.println("hereee");

        // Populate the results map with relevant information for table display
        result.put("count", pageInfo.getTotal());
        result.put("data", pageInfo.getList());
        // Code 0 typically signifies a successful operation
        result.put("code", 0);
        result.put("msg", ""); // Placeholder for any potential messages
        return result;
    }

    /**
     * Updated a shared data by its id.
     *
     * @param id shared data id.
     * @param newData data content to be updated.
     */
    public void updateSharedData(final Integer id,
                                 final SharedDataModel newData) {
        // Retrieve existing shared data by ID from the data source
        SharedDataModel existingData =
                sharedDataMapper.selectSharedDataById(id);

        // If the data does not exist, throw a CustomException
        // with a Bad Request status
        if (existingData == null) {
            throw new CustomException("Record ID does not exist",
                    HttpStatus.BAD_REQUEST);
        }

        // Check if the content and subject of the new data are both null
        if (newData.getContent() == null && newData.getSubject() == null) {
            throw new CustomException("Updates can not be null",
                    HttpStatus.BAD_REQUEST);
        }

        // Check if the user ID is not null
        if (newData.getUid() == null) {
            throw new CustomException("User ID cannot be null",
                    HttpStatus.BAD_REQUEST);
        }

        // Set the modified time to the current LocalDateTime
        newData.setModifiedTime(LocalDateTime.now());
        // Set the ID to the provided ID (to ensure the
        // update is for the correct record)
        newData.setId(id);
        // Update the shared data using sharedDataMapper
        sharedDataMapper.updateSharedData(newData);
    }

    /**
     * Delete a shared data by its id.
     *
     * @param id shared data id.
     */
    public void deleteSharedDataById(final Integer id) {
        SharedDataModel data = sharedDataMapper.selectSharedDataById(id);
        // check if id exists in database
        if (data == null) {
            throw new CustomException("Record ID does not exist",
                    HttpStatus.BAD_REQUEST);
        }
        sharedDataMapper.deleteSharedDataById(id);

        // may need to add check for data owner or only admin can delete
    }

    /**
     * Validate a shared data when adding a new data.
     *
     * @param sharedData shared data to be added.
     */
    public void validateSharedData(final SharedDataModel sharedData) {

        // Check if the sharedData object itself is not null
        if (sharedData == null) {
            throw new CustomException("Shared data cannot be null",
                    HttpStatus.BAD_REQUEST);
        }

        // Check if the shared data object has ID, which should be
        // empty as the service will automatically assign ID
        if (sharedData.getId() != null) {
            throw new CustomException("ID will be automatically assigned",
                    HttpStatus.BAD_REQUEST);
        }

        // Check if the user ID is not null
        if (sharedData.getUid() == null) {
            throw new CustomException("User ID cannot be null",
                    HttpStatus.BAD_REQUEST);
        }

        // Check if the 'subject' field is not null
        if (sharedData.getSubject() == null
                || sharedData.getSubject().isBlank()) {
            throw new CustomException("Subject cannot be empty",
                    HttpStatus.BAD_REQUEST);
        }

        // Check if the 'content' field is not null
        if (sharedData.getContent() == null
                || sharedData.getContent().isBlank()) {
            throw new CustomException("Content cannot be empty",
                    HttpStatus.BAD_REQUEST);
        }
    }
}
