package com.mvcmasters.ems.repository;

import com.mvcmasters.ems.base.BaseMapper;
import com.mvcmasters.ems.model.SharedDataModel;

import java.util.List;

public interface SharedDataMapper extends BaseMapper<SharedDataModel, Integer> {

    // Insert a new shared data entry
    void insertSharedData(SharedDataModel data);

    // Retrieve a shared data entry by its id
    SharedDataModel selectSharedDataById(Integer id);

    // Retrieve all shared data entries
    List<SharedDataModel> selectAllSharedData();

    // Update a shared data entry by its id
    void updateSharedData(SharedDataModel data);

    // Delete a shared data entry by its id
    void deleteSharedDataById(Integer id);
}
