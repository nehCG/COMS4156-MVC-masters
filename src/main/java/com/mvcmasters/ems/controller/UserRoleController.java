package com.mvcmasters.ems.controller;

import com.mvcmasters.ems.base.BaseController;
import com.mvcmasters.ems.service.UserRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;

@Controller
public class UserRoleController extends BaseController {

    /**
     * Service for handling userRole-related operations.
     */
    @Resource
    private UserRoleService userRoleService;
}
