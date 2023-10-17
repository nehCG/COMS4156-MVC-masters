package com.mvcmasters.ems.repository;

import com.mvcmasters.ems.base.BaseMapper;
import com.mvcmasters.ems.model.SharedDataModel;

import java.util.List;

public interface SharedDataMapper extends BaseMapper<SharedDataModel, Integer> {
    void insertSharedData(SharedDataModel data);

    SharedDataModel selectSharedDataById(Integer id);

    List<SharedDataModel> selectAllSharedData();

    void updateSharedData(SharedDataModel data);

    void deleteSharedDataById(Integer id);
}
