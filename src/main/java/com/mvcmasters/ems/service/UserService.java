package com.mvcmasters.ems.service;

import com.mvcmasters.ems.base.BaseService;
import com.mvcmasters.ems.vo.User;
import com.mvcmasters.ems.repository.UserMapper;
import com.mvcmasters.ems.model.UserModel;
import com.mvcmasters.ems.utils.AssertUtil;
import com.mvcmasters.ems.utils.Md5Util;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, Integer> {
    @Resource
    private UserMapper userMapper;

    public UserModel userLogin(String userName, String userPwd) {
        checkLoginParams(userName, userPwd);

        User user = userMapper.queryUserByName(userName);

        AssertUtil.isTrue(user == null, "User does not exist!");

        checkUserPwd(userPwd, user.getUserPwd());

        return buildUserInfo(user);
    }

    private void checkLoginParams(String userName, String userPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(userName), "Username cannot be empty!");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd), "Password cannot be empty!");
    }

    private void checkUserPwd(String userPwd, String pwd) {
        userPwd = Md5Util.encode(userPwd);
        AssertUtil.isTrue(!userPwd.equals(pwd), "Incorrect Password!");
    }

    private UserModel buildUserInfo(User user) {
        UserModel userModel = new UserModel();
        userModel.setUserId(user.getId());
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        return userModel;
    }
}
