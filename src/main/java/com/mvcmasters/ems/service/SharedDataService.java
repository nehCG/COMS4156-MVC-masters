package com.mvcmasters.ems.service;

import com.mvcmasters.ems.base.BaseService;
import com.mvcmasters.ems.exceptions.CustomException;
import com.mvcmasters.ems.model.SharedDataModel;
import com.mvcmasters.ems.repository.SharedDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        sharedData.setCreatedTime(LocalDateTime.now());
        validateSharedData(sharedData);
        sharedDataMapper.insertSharedData(sharedData);
    }

    /**
     * Retrieve a shared data by its id.
     *
     * @param id shared data id.
     * @return a shared data with the corresponding id.
     */
    public SharedDataModel getSharedDataById(final Integer id) {
        SharedDataModel data = sharedDataMapper.selectSharedDataById(id);
        if (data == null) {
            throw new CustomException("Record ID does not exist",
                                      HttpStatus.BAD_REQUEST);
        }
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
     * Updated a shared data by its id.
     *
     * @param id shared data id.
     * @param newData data content to be updated.
     */
    public void updateSharedData(final Integer id,
                                 final SharedDataModel newData) {
        SharedDataModel existingData =
                sharedDataMapper.selectSharedDataById(id);
        if (existingData == null) {
            throw new CustomException("Record ID does not exist",
                    HttpStatus.BAD_REQUEST);
        }

        if (newData.getContent() == null && newData.getSubject() == null) {
            throw new CustomException("Updates can not be null",
                    HttpStatus.BAD_REQUEST);
        }

        // Check if the user ID is not null
        if (newData.getUid() == null) {
            throw new CustomException("User ID cannot be null",
                    HttpStatus.BAD_REQUEST);
        }

        newData.setModifiedTime(LocalDateTime.now());
        newData.setId(id);
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
                || sharedData.getSubject().trim().isBlank()) {
            throw new CustomException("Subject cannot be empty",
                    HttpStatus.BAD_REQUEST);
        }

        // Check if the 'content' field is not null
        if (sharedData.getContent() == null
                || sharedData.getContent().trim().isBlank()) {
            throw new CustomException("Content cannot be empty",
                    HttpStatus.BAD_REQUEST);
        }
    }


}
