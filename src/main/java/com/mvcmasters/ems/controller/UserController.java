package com.mvcmasters.ems.controller;

import com.mvcmasters.ems.base.BaseController;
import com.mvcmasters.ems.base.ResultInfo;
import com.mvcmasters.ems.exceptions.ParamsException;
import com.mvcmasters.ems.model.UserModel;
import com.mvcmasters.ems.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @PostMapping ("login")
    @ResponseBody
    public ResultInfo userLogin(String userName, String userPwd) {

        ResultInfo resultInfo = new ResultInfo();

        try {
            UserModel userModel = userService.userLogin(userName, userPwd);
            resultInfo.setResult(userModel);
        } catch (ParamsException p) {
            resultInfo.setCode(p.getCode());
            resultInfo.setMsg(p.getMsg());
            p.printStackTrace();
        } catch (Exception e) {
            resultInfo.setCode(500);
            resultInfo.setMsg("Login Failed!");
        }

        return resultInfo;
    }
}
