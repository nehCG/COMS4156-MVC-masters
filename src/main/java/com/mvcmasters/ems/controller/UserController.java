package com.mvcmasters.ems.controller;

import com.mvcmasters.ems.base.BaseController;
import com.mvcmasters.ems.base.ResultInfo;
import com.mvcmasters.ems.exceptions.ParamsException;
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

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @PostMapping ("login")
    @ResponseBody
    public ResultInfo userLogin(String userName, String userPwd) {

        ResultInfo resultInfo = new ResultInfo();

        UserModel userModel = userService.userLogin(userName, userPwd);

        resultInfo.setResult(userModel);

        return resultInfo;
    }

    @PostMapping("updatePwd")
    @ResponseBody
    public ResultInfo updateUserPassword(Integer userId, String oldPassword, String newPassword, String repeatPassword) {
        ResultInfo resultInfo = new ResultInfo();

        userService.updatePassWord(userId, oldPassword, newPassword, repeatPassword);

        return resultInfo;
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectByParams(UserQuery userQuery) {
        return userService.queryByParamsForTable(userQuery);
    }

    @PostMapping("add")
    @ResponseBody
    public ResultInfo addUser(User user) {
        userService.addUser(user);
        return success("User added successfully!");
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateUser(User user) {
        userService.updateUser(user);
        return success("User records updated successfully!");
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteUser(Integer[] ids) {
        userService.deleteByIds(ids);
        return success("User deleted successfully!");
    }

}
