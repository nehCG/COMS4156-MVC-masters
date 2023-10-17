package com.mvcmasters.ems.controller;

import com.mvcmasters.ems.base.BaseController;
import com.mvcmasters.ems.model.SharedDataModel;
import com.mvcmasters.ems.service.SharedDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcement")
public class SharedSpaceController extends BaseController {

    @Autowired
    private SharedDataService sharedDataService;

    @PostMapping("/post")
    public ResponseEntity<String> addSharedData(@RequestBody SharedDataModel sharedData) {
        sharedDataService.addSharedData(sharedData);
        return new ResponseEntity<>("Shared data added successfully!", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SharedDataModel> getSharedDataById(@PathVariable Integer id) {
        SharedDataModel sharedData = sharedDataService.getSharedDataById(id);
        return new ResponseEntity<>(sharedData, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SharedDataModel>> getAllSharedData() {
        List<SharedDataModel> sharedDataList = sharedDataService.getAllSharedData();
        return new ResponseEntity<>(sharedDataList, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateSharedData(@PathVariable Integer id, @RequestBody SharedDataModel sharedData) {
        sharedDataService.updateSharedData(id, sharedData);
        return new ResponseEntity<>("Shared data updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSharedDataById(@PathVariable Integer id) {
        sharedDataService.deleteSharedDataById(id);
        return new ResponseEntity<>("Shared data deleted successfully!", HttpStatus.NO_CONTENT);
    }

}
