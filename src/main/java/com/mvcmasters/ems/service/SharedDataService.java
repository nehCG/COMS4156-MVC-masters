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

@Service
public class SharedDataService extends BaseService<SharedDataModel, Integer> {

    @Autowired
    private SharedDataMapper sharedDataMapper;

    // Add a new shared data entry
    public void addSharedData(SharedDataModel sharedData) {
        sharedData.setCreatedTime(LocalDateTime.now());
        validateSharedData(sharedData);
        sharedDataMapper.insertSharedData(sharedData);
    }

    // Get a shared data entry by its id
    public SharedDataModel getSharedDataById(Integer id) {
        SharedDataModel data = sharedDataMapper.selectSharedDataById(id);
        if (data == null) {
            throw new CustomException("Record ID does not exist", HttpStatus.BAD_REQUEST);
        }
        return data;
    }

    // View all shared data entries
    public List<SharedDataModel> getAllSharedData() {
        return sharedDataMapper.selectAllSharedData();
    }

    // Update a shared data entry by its id
    public void updateSharedData(Integer id, SharedDataModel newData) {
        SharedDataModel existingData = sharedDataMapper.selectSharedDataById(id);
        if (existingData == null) {
            throw new CustomException("Record ID does not exist", HttpStatus.BAD_REQUEST);
        }

        if (newData.getContent() == null && newData.getSubject() == null) {
            throw new CustomException("Updates can not be null", HttpStatus.BAD_REQUEST);
        }

        // Check if the user ID is not null
        if (newData.getUid() == null) {
            throw new CustomException("User ID cannot be null", HttpStatus.BAD_REQUEST);
        }

        newData.setModifiedTime(LocalDateTime.now());
        newData.setId(id);
        sharedDataMapper.updateSharedData(newData);

        // may need to add check for data owner match current logged-in user
    }

    // Delete a shared data entry by its id
    public void deleteSharedDataById(Integer id) {
        SharedDataModel data = sharedDataMapper.selectSharedDataById(id);
        // check if id exists in database
        if (data == null) {
            throw new CustomException("Record ID does not exist", HttpStatus.BAD_REQUEST);
        }
        sharedDataMapper.deleteSharedDataById(id);

        // may need to add check for data owner or only admin can delete
    }

    public void validateSharedData(SharedDataModel sharedData){
        // Check if the sharedData object itself is not null
        if (sharedData == null) {
            throw new CustomException("Shared data cannot be null", HttpStatus.BAD_REQUEST);
        }

        // Check if the shared data object has a ID, which should be empty as the service
        // will automatically assign ID
        if (sharedData.getId() != null) {
            throw new CustomException("ID will be automatically assigned", HttpStatus.BAD_REQUEST);
        }

        // Check if the user ID is not null
        if (sharedData.getUid() == null) {
            throw new CustomException("User ID cannot be null", HttpStatus.BAD_REQUEST);
        }

        // Check if the 'subject' field is not null
        if (sharedData.getSubject() == null || sharedData.getSubject().isBlank()) {
            throw new CustomException("Subject cannot be empty", HttpStatus.BAD_REQUEST);
        }

        // Check if the 'content' field is not null
        if (sharedData.getContent() == null || sharedData.getContent().isBlank()) {
            throw new CustomException("Content cannot be empty", HttpStatus.BAD_REQUEST);
        }
    }


}
