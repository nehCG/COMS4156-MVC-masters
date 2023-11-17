package com.mvcmasters.ems.controller;

import com.mvcmasters.ems.base.BaseController;
import com.mvcmasters.ems.service.UserService;
import com.mvcmasters.ems.utils.LoginUserUtil;
import com.mvcmasters.ems.vo.User;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
     * @param request The HttpServletRequest object,
     *                providing information about the request.
     * @return The name of the main view.
     */
    @RequestMapping("/main")
    public String main(final HttpServletRequest request) {
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userService.selectByPrimaryKey(userId);
        request.getSession().setAttribute("user", user);
        return "main";
    }
}
