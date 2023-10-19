package com.mvcmasters.ems.controller;

import com.mvcmasters.ems.base.BaseController;
import com.mvcmasters.ems.base.ResultInfo;
import com.mvcmasters.ems.model.UserModel;
import com.mvcmasters.ems.query.UserQuery;
import com.mvcmasters.ems.service.UserService;
import com.mvcmasters.ems.vo.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Controller class responsible for handling user-related operations.
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    /**
     * Service for handling user-related operations.
     */
    @Resource
    private UserService userService;

    /**
     * User login operation.
     * @param userName the name of the user.
     * @param userPwd the password of the user.
     * @return a ResultInfo containing user data or error.
     */
    @PostMapping ("login")
    @ResponseBody
    public ResultInfo userLogin(final String userName, final String userPwd) {

        ResultInfo resultInfo = new ResultInfo();

        UserModel userModel = userService.userLogin(userName, userPwd);

        resultInfo.setResult(userModel);

        return resultInfo;
    }

    /**
     * Updates the password of a user.
     * @param userId the ID of the user.
     * @param oldPassword the old password.
     * @param newPassword the new password.
     * @param repeatPassword repeated new password.
     * @return a ResultInfo confirming password update.
     */
    @PostMapping("updatePwd")
    @ResponseBody
    public ResultInfo updateUserPassword(final Integer userId,
                                         final String oldPassword,
                                         final String newPassword,
                                         final String repeatPassword) {
        ResultInfo resultInfo = new ResultInfo();

        userService.updatePassWord(userId,
                                   oldPassword,
                                   newPassword,
                                   repeatPassword);

        return resultInfo;
    }

    /**
     * Gets a list of users based on given parameters.
     * @param userQuery the query parameters.
     * @return a Map containing user list data.
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectByParams(final UserQuery userQuery) {
        return userService.queryByParamsForTable(userQuery);
    }

    /**
     * Adds a new user.
     * @param user the User object containing user details.
     * @return a ResultInfo confirming user addition.
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addUser(final User user) {
        userService.addUser(user);
        return success("User added successfully!");
    }

    /**
     * Updates user details.
     * @param user the User object containing updated user details.
     * @return a ResultInfo confirming user update.
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateUser(final User user) {
        userService.updateUser(user);
        return success("User records updated successfully!");
    }

    /**
     * Deletes users based on given IDs.
     * @param ids array of user IDs.
     * @return a ResultInfo confirming user deletion.
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteUser(final Integer[] ids) {
        userService.deleteByIds(ids);
        return success("User deleted successfully!");
    }

}
