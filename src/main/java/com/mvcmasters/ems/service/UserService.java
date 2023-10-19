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
        // Check and validate the login parameters
        checkLoginParams(userName, userPwd);

        // Query the user based on the provided username
        User user = userMapper.queryUserByName(userName);

        // Check if the user exists, and if not, throw an exception
        AssertUtil.isTrue(user == null, "User does not exist!");

        // Check if the provided password matches the stored password
        checkUserPwd(userPwd, user.getUserPwd());

        // Build and return user model information after successful login
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
        // Retrieve the user based on the provided userId
        User user = userMapper.selectByPrimaryKey(userId);

        // Check if the user record exists, and if not, throw an exception
        AssertUtil.isTrue(null == user, "Record to be updated does not exist!");

        // Validate the password change parameters
        checkPasswordParams(user, oldPwd, newPwd, repeatPwd);

        // Encode and set the new password for the user
        user.setUserPwd(Md5Util.encode(newPwd));

        // Attempt to update the user's password,
        // and throw an exception if the update fails
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) < 1,
                "Failed to change password!");

    }

    /**
     * Validates login parameters.
     *
     * @param userName User's name.
     * @param userPwd  User's password.
     */
    private void checkLoginParams(final String userName, final String userPwd) {
        // Check if the provided username is blank,
        // and if so, throw an exception
        AssertUtil.isTrue(StringUtils.isBlank(userName),
                "Username cannot be empty!");
        // Check if the provided password is blank,
        // and if so, throw an exception
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),
                "Password cannot be empty!");
    }

    /**
     * Checks if the provided user password matches the stored password.
     *
     * @param userPwd The provided user password.
     * @param pwd     The stored password to compare against.
     */
    private void checkUserPwd(final String userPwd, final String pwd) {
        // Encode the provided user password
        String encodedUserPwd = Md5Util.encode(userPwd);

        // Check if the encoded user password matches the stored password,
        // and if not, throw an exception indicating incorrect password
        AssertUtil.isTrue(!encodedUserPwd.equals(pwd), "Incorrect Password!");
    }

    /**
     * Builds a UserModel object based on the provided User object.
     *
     * @param user The User object containing user information.
     * @return A UserModel object containing user details.
     */
    private UserModel buildUserInfo(final User user) {
        // Create a new UserModel object
        UserModel userModel = new UserModel();

        // Set the user ID, username, and true name
        // from the provided User object
        userModel.setUserId(user.getId());
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());

        // Return the constructed UserModel object
        return userModel;
    }
    /**
     * Checks and validates password change parameters.
     *
     * @param user      The User object for which the password is being changed.
     * @param oldPwd    The old password.
     * @param newPwd    The new password.
     * @param repeatPwd The repeated new password.
     */
    private void checkPasswordParams(final User user,
                                     final String oldPwd,
                                     final String newPwd,
                                     final String repeatPwd) {
        // Check if the provided old password is blank,
        // and if so, throw an exception
        AssertUtil.isTrue(StringUtils.isBlank(oldPwd),
                "Original password cannot be empty!");

        // Check if the provided old password matches the stored password,
        // and if not, throw an exception
        AssertUtil.isTrue(!user.getUserPwd().equals(Md5Util.encode(oldPwd)),
                "Incorrect original password!");

        // Check if the provided new password is blank,
        // and if so, throw an exception
        AssertUtil.isTrue(StringUtils.isBlank(newPwd),
                "New password cannot be empty!");

        // Check if the new password is the same as the old password,
        // and if so, throw an exception
        AssertUtil.isTrue(oldPwd.equals(newPwd),
                "New password cannot be same as old password!");

        // Check if the provided repeated password is blank,
        // and if so, throw an exception
        AssertUtil.isTrue(StringUtils.isBlank(repeatPwd),
                "Repeated password cannot be empty!");

        // Check if the repeated password matches the new password,
        // and if not, throw an exception
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
        // Check and validate user parameters,
        // excluding the ID (assuming null ID for a new user)
        checkUserParams(user.getUserName(),
                        user.getEmail(),
                        user.getPhone(),
                        null);

        // Set the user's validity status to 1
        // (assuming 1 represents a valid user)
        user.setIsValid(1);

        // Set the initial password for the new user (e.g., "123456")
        user.setUserPwd(Md5Util.encode("123456"));

        // Attempt to insert the new user into the database,
        // and throw an exception if the insertion fails
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
        // Check if the user's ID is null, and if so, throw an exception
        AssertUtil.isTrue(null == user.getId(),
                "User to be updated does not exist!");

        // Retrieve the user with the provided ID
        User temp = userMapper.selectByPrimaryKey(user.getId());

        // Check if the user with the provided ID exists,
        // and if not, throw an exception
        AssertUtil.isTrue(null == temp, "User to be updated does not exist!");

        // Check and validate user parameters, including the user's ID
        checkUserParams(user.getUserName(),
                        user.getEmail(),
                        user.getPhone(),
                        user.getId());

        // Attempt to update the user records in the database,
        // and throw an exception if the update fails
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) != 1,
                "Failed to update user records!");
    }

    private void checkUserParams(final String userName,
                                 final String email,
                                 final String phone,
                                 final Integer userId) {
        // Check if the provided username is blank,
        // and if so, throw an exception
        AssertUtil.isTrue(StringUtils.isBlank(userName),
                "Username cannot be empty!");

        // If a user with the provided username already exists
        // (excluding the current user), throw an exception
        User temp = userMapper.queryUserByName(userName);
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(userId)),
                "Username already exists. Please try another one!");

        // Check if the provided email is blank, and if so, throw an exception
        AssertUtil.isTrue(StringUtils.isBlank(email),
                "User email cannot be empty!");

        // Check if the provided phone number is blank,
        // and if so, throw an exception
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
        // Check if the provided array of user IDs is null or empty,
        // and if so, throw an exception
        AssertUtil.isTrue(ids == null || ids.length == 0,
                "Records to be deleted do not exist!");

        // Attempt to delete users with the provided IDs from the database,
        // and throw an exception if the deletion count does not match
        // the expected number of deletions
        AssertUtil.isTrue(userMapper.deleteBatch(ids) != ids.length,
                "Failed to delete users!");
    }
}
