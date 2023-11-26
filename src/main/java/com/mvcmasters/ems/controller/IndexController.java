package com.mvcmasters.ems.controller;

import org.springframework.ui.Model;
import com.mvcmasters.ems.base.BaseController;
import com.mvcmasters.ems.service.PermissionService;
import com.mvcmasters.ems.service.UserService;
import com.mvcmasters.ems.utils.LoginUserUtil;
import com.mvcmasters.ems.vo.User;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Controller for handling main application pages.
 * This controller extends the {@link BaseController} and manages the
 * primary navigation pages of the application such as index, welcome, and main.
 */
@Controller
public class IndexController extends BaseController {
    /**
     * Service for handling user-related operations.
     */
    @Resource
    private UserService userService;
    /**
     * Service for handling permission operations.
     */
    @Resource
    private PermissionService permissionService;
    /**
     * Displays the index page.
     *
     * @return The name of the index view.
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    /**
     * Displays the welcome page.
     *
     * @return The name of the welcome view.
     */
    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
    /**
     * Displays the main page of the application.
     * This method retrieves the current user's ID from a cookie,
     * fetches the corresponding user
     * details, and stores them in the session.
     * It then returns the name of the main view.
     *
     * @param model The model object, providing information about the model.
     * @param request The HttpServletRequest object,
     *                providing information about the request.
     * @return The name of the main view.
     */
    @RequestMapping("/main")
    public String main(final Model model, final HttpServletRequest request) {
        // Example of fetching user data
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userService.selectByPrimaryKey(userId);
        List<String> permissions =
                permissionService.queryUserHasRoleHasPermissionByUserId(userId);
        // Adding attributes to the model
        model.addAttribute("user", user);
        model.addAttribute("permissions", permissions);
        // Return the view name
        return "main";
    }
}
