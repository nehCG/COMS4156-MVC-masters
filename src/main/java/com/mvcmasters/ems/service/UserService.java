package com.mvcmasters.ems.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mvcmasters.ems.base.BaseService;
import com.mvcmasters.ems.vo.User;
import com.mvcmasters.ems.repository.UserMapper;
import com.mvcmasters.ems.model.UserModel;
import com.mvcmasters.ems.utils.AssertUtil;
import com.mvcmasters.ems.utils.Md5Util;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePassWord(Integer userId, String oldPwd, String newPwd, String repeatPwd) {
        User user = userMapper.selectByPrimaryKey(userId);

        AssertUtil.isTrue(null == user, "Record to be updated does not exist!");

        checkPasswordParams(user, oldPwd, newPwd, repeatPwd);

        user.setUserPwd(Md5Util.encode(newPwd));

        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) < 1, "Failed to change password!");

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

    private void checkPasswordParams(User user, String oldPwd, String newPwd, String repeatPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(oldPwd), "Original password cannot be empty!");
        AssertUtil.isTrue(!user.getUserPwd().equals(Md5Util.encode(oldPwd)), "Incorrect original password!");
        AssertUtil.isTrue(StringUtils.isBlank(newPwd), "New password cannot be empty!");
        AssertUtil.isTrue(oldPwd.equals(newPwd), "New password cannot be same as old password!");
        AssertUtil.isTrue(StringUtils.isBlank(repeatPwd), "Repeated password cannot be empty!");
        AssertUtil.isTrue(!newPwd.equals(repeatPwd), "Repeated password is inconsistent with new password!");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user) {
        checkUserParams(user.getUserName(), user.getEmail(), user.getPhone(), null);

        user.setIsValid(1);

        user.setUserPwd(Md5Util.encode("123456"));

        AssertUtil.isTrue(userMapper.insertSelective(user) != 1, "Failed to add a new user");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user) {
        AssertUtil.isTrue(null == user.getId(), "User to be updated does not exist!");
        User temp = userMapper.selectByPrimaryKey(user.getId());
        AssertUtil.isTrue(null == temp, "User to be updated does not exist!");
        checkUserParams(user.getUserName(), user.getEmail(), user.getPhone(), user.getId());
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) != 1, "Failed to update user records!");
    }

    private void checkUserParams(String userName, String email, String phone, Integer userId) {
        AssertUtil.isTrue(StringUtils.isBlank(userName), "Username cannot be empty!");
        User temp = userMapper.queryUserByName(userName);
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(userId)), "Username already exists. Please try another one!");
        AssertUtil.isTrue(StringUtils.isBlank(email), "User email cannot be empty!");
        AssertUtil.isTrue(StringUtils.isBlank(phone), "User phone cannot be empty!");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByIds(Integer[] ids) {
        AssertUtil.isTrue(ids == null || ids.length == 0, "Records to be deleted do not exist!");
        AssertUtil.isTrue(userMapper.deleteBatch(ids) != ids.length, "Failed to delete users!");
    }
}
