package com.mvcmasters.ems.controller;

import com.mvcmasters.ems.base.BaseController;
import com.mvcmasters.ems.base.BaseQuery;
import com.mvcmasters.ems.base.ResultInfo;
import com.mvcmasters.ems.model.SharedDataModel;
import com.mvcmasters.ems.service.SharedDataService;
import com.mvcmasters.ems.vo.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller for operations related to Shared Data.
 */
@Controller
@RequestMapping("/announcement")
public class SharedSpaceController extends BaseController {
    /**
     * Constructor for dependency injection.
     * sharedDataService manages shared data operations.
     */
    @Autowired
    private SharedDataService sharedDataService;

    /**
     * Add a new shared data entry.
     *
     * @param sharedData the shared data to add.
     * @return a ResponseEntity with a success message.
     */
    @PostMapping("/post")
    @ResponseBody
    public ResultInfo addSharedData(@RequestBody final SharedDataModel sharedData) {
        // Call the service method to add the shared data
        sharedDataService.addSharedData(sharedData);
        // Return a ResponseEntity with a success message and HTTP status OK
        return success("Announcement added successfully!");
//        return new ResponseEntity<>(
//                "Shared data added successfully!", HttpStatus.OK);
    }

    /**
     * Get specific shared data by ID.
     *
     * @param id of the shared data.
     * @return ResponseEntity containing data.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SharedDataModel> getSharedDataById(
            @PathVariable final Integer id) {
        // Call the service method to retrieve the shared data by ID
        SharedDataModel sharedData = sharedDataService.getSharedDataById(id);

        // Return a ResponseEntity containing the retrieved
        // data and HTTP status OK
        return new ResponseEntity<>(sharedData, HttpStatus.OK);
    }

    /**
     * Get all shared data entries.
     *
     * @return ResponseEntity with all data.
     */
    @GetMapping("/all")
    @ResponseBody
    public Map<String, Object> getAllSharedData(final BaseQuery baseQuery){
        return sharedDataService.queryByParamsForTable(baseQuery);
    }
//    public ResponseEntity<List<SharedDataModel>> getAllSharedData() {
//        // return new ResponseEntity<>(tableData, HttpStatus.OK);
//        // Call the service method to retrieve all shared data entries
//        List<SharedDataModel> sharedDataList =
//                sharedDataService.getAllSharedData();
//        // Return a ResponseEntity containing the list of
//        // shared data entries and HTTP status OK
//        return new ResponseEntity<>(sharedDataList, HttpStatus.OK);
//    }

    /**
     * Update shared data by ID.
     *
     * @param id of the shared data.
     * @param sharedData updated data.
     * @return ResponseEntity with message.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateSharedData(
            @PathVariable final Integer id,
            @RequestBody final SharedDataModel sharedData) {
        // Call the service method to update the shared data by ID
        sharedDataService.updateSharedData(id, sharedData);

        // Return a ResponseEntity with a success message and HTTP status OK
        return new ResponseEntity<>(
                "Shared data updated successfully!", HttpStatus.OK);
    }

    /**
     * Delete shared data by ID.
     *
     * @param id of the shared data.
     * @return ResponseEntity with message.
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResultInfo deleteSharedDataById(
            @PathVariable final Integer id) {
        // Call the service method to delete the shared data by ID
        sharedDataService.deleteSharedDataById(id);

        // Return a ResponseEntity with a success message and HTTP status OK
        return success("Announcement deleted successfully!");
    }

    /**
     * Displays the index page.
     *
     * @return The name of the index view.
     */
    @RequestMapping("/index")
    public String index() {
        return "announcement/announcement";
    }

    @RequestMapping("toAddOrUpdateAnnouncementPage")
    public String toAddOrUpdateUserPage(final Integer id,
                                        final HttpServletRequest request) {
        if (id != null) {
            SharedDataModel sharedData = sharedDataService.getSharedDataById(id);
            sharedData.setUid(1);
            request.setAttribute("sharedDataInfo", sharedData);
        }
        return "announcement/add_update";
    }
}
