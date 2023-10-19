package com.mvcmasters.ems.repository;

import com.mvcmasters.ems.base.BaseMapper;
import com.mvcmasters.ems.model.SharedDataModel;

import java.util.List;
/**
 * Mapper interface for shared data related database operations.
 */
public interface SharedDataMapper extends BaseMapper<SharedDataModel, Integer> {
    /**
     * Added a new shared data into the database.
     *
     * @param data the new shared data to be added.
     */
    void insertSharedData(SharedDataModel data);
    /**
     * Queries the database to retrieve a shared data based on its ID.
     *
     * @param id the shared data id
     * @return the Shared Data corresponding to the provided id,
     * or null if not found.
     */
    SharedDataModel selectSharedDataById(Integer id);
    /**
     * Retrieve all shared data entries.
     *
     * @return all the shared data in the database.
     */
    List<SharedDataModel> selectAllSharedData();
    /**
     * Update a shared data entry by its id.
     *
     * @param data data content to be updated
     */
    void updateSharedData(SharedDataModel data);
    /**
     * Delete a shared data entry by its id.
     *
     * @param id data id to be deleted
     */
    void deleteSharedDataById(Integer id);
}
