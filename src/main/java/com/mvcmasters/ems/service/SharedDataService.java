package com.mvcmasters.ems.service;

import com.mvcmasters.ems.base.BaseService;
import com.mvcmasters.ems.model.SharedDataModel;
import com.mvcmasters.ems.repository.SharedDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SharedDataService extends BaseService<SharedDataModel, Integer> {

    @Autowired
    private SharedDataMapper sharedDataMapper;

    public void addSharedData(SharedDataModel sharedData) {
        sharedDataMapper.insertSharedData(sharedData);
    }

    public SharedDataModel getSharedDataById(Integer id) {
        return sharedDataMapper.selectSharedDataById(id);
    }

    public List<SharedDataModel> getAllSharedData() {
        return sharedDataMapper.selectAllSharedData();
    }

    public void updateSharedData(SharedDataModel sharedData) {
        sharedDataMapper.updateSharedData(sharedData);
    }

    public void deleteSharedDataById(Integer id) {
        sharedDataMapper.deleteSharedDataById(id);
    }
}
