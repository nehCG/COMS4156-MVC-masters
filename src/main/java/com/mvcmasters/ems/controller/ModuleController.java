package com.mvcmasters.ems.controller;

import com.mvcmasters.ems.base.BaseController;
import com.mvcmasters.ems.model.TreeModel;
import com.mvcmasters.ems.service.ModuleService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Controller for handling module-related operations.
 */
@RequestMapping("/module")
@Controller
public class ModuleController extends BaseController {

    /**
     * Service for handling module operations.
     */
    @Resource
    private ModuleService moduleService;

    /**
     * Queries all modules based on the given role ID.
     *
     * @param roleId The role ID used for querying modules.
     * @return A list of TreeModel objects representing modules.
     */
    @RequestMapping("/queryAllModules")
    @ResponseBody
    public List<TreeModel> queryAllModules(final Integer roleId) {
        return moduleService.queryAllModules(roleId);
    }

    /**
     * Directs the user to the page for adding grants to a role.
     *
     * @param roleId The ID of the role to add grants to.
     * @param request The HttpServletRequest object.
     * @return The path to the "grant" view under the "role" directory.
     */
    @RequestMapping("/toAddGrantPage")
    public String toAddGrantPage(final Integer roleId,
                                 final HttpServletRequest request) {
        request.setAttribute("roleId", roleId);
        return "role/grant";
    }
}
