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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer for User related operations.
 */
@Service
public class UserService extends BaseService<User, Integer> {
    /**
     * Mapper for user-related database operations.
     */
    @Resource
    private UserMapper userMapper;

    /**
     * Handles user login logic.
     *
     * @param userName User's name.
     * @param userPwd User's password.
     * @return User's model information after successful login.
     */
    public UserModel userLogin(final String userName, final String userPwd) {
        checkLoginParams(userName, userPwd);

        User user = userMapper.queryUserByName(userName);

        AssertUtil.isTrue(user == null, "User does not exist!");

        checkUserPwd(userPwd, user.getUserPwd());

        return buildUserInfo(user);
    }

    /**
     * Updates the password of the user.
     *
     * @param userId     The ID of the user.
     * @param oldPwd     The old password.
     * @param newPwd     The new password.
     * @param repeatPwd  The repeated new password.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePassWord(final Integer userId,
                               final String oldPwd,
                               final String newPwd,
                               final String repeatPwd) {
        User user = userMapper.selectByPrimaryKey(userId);

        AssertUtil.isTrue(null == user, "Record to be updated does not exist!");

        checkPasswordParams(user, oldPwd, newPwd, repeatPwd);

        user.setUserPwd(Md5Util.encode(newPwd));

        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) < 1,
                "Failed to change password!");

    }

    private void checkLoginParams(final String userName, final String userPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),
                "Username cannot be empty!");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),
                "Password cannot be empty!");
    }

    private void checkUserPwd(final String userPwd, final String pwd) {
        String encodedUserPwd = Md5Util.encode(userPwd);
        AssertUtil.isTrue(!encodedUserPwd.equals(pwd), "Incorrect Password!");
    }

    private UserModel buildUserInfo(final User user) {
        UserModel userModel = new UserModel();
        userModel.setUserId(user.getId());
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        return userModel;
    }

    private void checkPasswordParams(final User user,
                                     final String oldPwd,
                                     final String newPwd,
                                     final String repeatPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(oldPwd),
                "Original password cannot be empty!");
        AssertUtil.isTrue(!user.getUserPwd().equals(Md5Util.encode(oldPwd)),
                "Incorrect original password!");
        AssertUtil.isTrue(StringUtils.isBlank(newPwd),
                "New password cannot be empty!");
        AssertUtil.isTrue(oldPwd.equals(newPwd),
                "New password cannot be same as old password!");
        AssertUtil.isTrue(StringUtils.isBlank(repeatPwd),
                "Repeated password cannot be empty!");
        AssertUtil.isTrue(!newPwd.equals(repeatPwd),
                "Repeated password is inconsistent with new password!");
    }

    /**
     * Adds a new user to the system.
     *
     * @param user The user object containing
     *             the details of the user to be added.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(final User user) {
        checkUserParams(user.getUserName(),
                        user.getEmail(),
                        user.getPhone(),
                        null);

        user.setIsValid(1);

        user.setUserPwd(Md5Util.encode("123456"));

        AssertUtil.isTrue(userMapper.insertSelective(user) != 1,
                "Failed to add a new user");
    }

    /**
     * Updates user details.
     *
     * @param user The user object containing the updated details.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(final User user) {
        AssertUtil.isTrue(null == user.getId(),
                "User to be updated does not exist!");
        User temp = userMapper.selectByPrimaryKey(user.getId());
        AssertUtil.isTrue(null == temp, "User to be updated does not exist!");
        checkUserParams(user.getUserName(),
                        user.getEmail(),
                        user.getPhone(),
                        user.getId());
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) != 1,
                "Failed to update user records!");
    }

    private void checkUserParams(final String userName,
                                 final String email,
                                 final String phone,
                                 final Integer userId) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),
                "Username cannot be empty!");
        User temp = userMapper.queryUserByName(userName);
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(userId)),
                "Username already exists. Please try another one!");
        AssertUtil.isTrue(StringUtils.isBlank(email),
                "User email cannot be empty!");
        AssertUtil.isTrue(StringUtils.isBlank(phone),
                "User phone cannot be empty!");
    }

    /**
     * Deletes users by their IDs.
     *
     * @param ids An array of user IDs to be deleted.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByIds(final Integer[] ids) {
        AssertUtil.isTrue(ids == null || ids.length == 0,
                "Records to be deleted do not exist!");
        AssertUtil.isTrue(userMapper.deleteBatch(ids) != ids.length,
                "Failed to delete users!");
    }
}
