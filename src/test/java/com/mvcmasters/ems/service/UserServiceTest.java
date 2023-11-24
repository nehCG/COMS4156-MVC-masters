package com.mvcmasters.ems.service;

import com.mvcmasters.ems.exceptions.ParamsException;
import com.mvcmasters.ems.repository.UserMapper;
import com.mvcmasters.ems.repository.UserRoleMapper;
import com.mvcmasters.ems.vo.User;
import com.mvcmasters.ems.model.UserModel;
import com.mvcmasters.ems.utils.Md5Util;
import com.mvcmasters.ems.vo.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.anyInt;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    /**
     * Constant representing ID value 1.
     */
    private static final int ID1 = 1;

    /**
     * Constant representing ID value 2.
     */
    private static final int ID2 = 2;

    /**
     * Constant representing ID value 3.
     */
    private static final int ID3 = 3;

    /**
     * Constant representing ID value 5.
     */
    private static final int ID5 = 5;

    /**
     * The service being tested, with dependencies mocked.
     */
    @InjectMocks
    private UserService userService;

    /**
     * A mock of the user data access object.
     */
    @Mock
    private UserMapper userMapper;

    /**
     * A mock of the user role data access object.
     */
    @Mock
    private UserRoleMapper userRoleMapper;

    /**
     * Set up mockito annotations for each test.
     */
    @BeforeEach
    public void setUp() {
        //MockitoAnnotations.initMocks(this);
    }

    /**
     * Test for user login with valid credentials.
     */
    @Test
    public void testUserLogin() {
        // Given
        String userName = "admin";
        String userPwd = "123456";

        User user = new User();
        user.setId(1);
        user.setUserName("admin");
        user.setUserPwd(Md5Util.encode("123456"));
        user.setTrueName("admin");
        user.setEmail("admin@ems.com");
        user.setPhone("1111111111");

        when(userMapper.queryUserByName(userName)).thenReturn(user);

        UserModel returnedUserModel = userService.userLogin(userName, userPwd);

        assertNotNull(returnedUserModel);
        assertEquals(user.getId(), 1);
        assertEquals(user.getUserName(), returnedUserModel.getUserName());
        assertEquals(user.getTrueName(), returnedUserModel.getTrueName());
    }

    /**
     * Test for user login when the user does not exist.
     */
    @Test
    public void testUserLoginUserDoesNotExist() {
        String userName = "admin";
        String userPwd = "123456";

        when(userMapper.queryUserByName(userName)).thenReturn(null);

        assertThrows(RuntimeException.class, ()
                -> userService.userLogin(userName, userPwd));
    }

    /**
     * Test for user login when the password is incorrect.
     */
    @Test
    public void testUserLoginIncorrectPassword() {
        String userName = "admin";
        String userPwd = "wrongpassword";

        User user = new User();
        user.setId(1);
        user.setUserName("admin");
        user.setUserPwd(Md5Util.encode("123456"));

        when(userMapper.queryUserByName(userName)).thenReturn(user);

        assertThrows(RuntimeException.class, ()
                -> userService.userLogin(userName, userPwd));
    }

    /**
     * Test for updating user password.
     */
    @Test
    public void testUpdatePassword() {
        Integer userId = 1;
        String oldPwd = "123456";
        String newPwd = "654321";
        String repeatPwd = "654321";

        User user = new User();
        user.setId(1);
        user.setUserPwd(Md5Util.encode("123456"));

        when(userMapper.selectByPrimaryKey(userId)).thenReturn(user);
        when(userMapper.updateByPrimaryKeySelective(any(User.class))).
                thenReturn(1);

        userService.updatePassWord(userId, oldPwd, newPwd, repeatPwd);
    }

    /**
     * Test for updating user password when user does not exist.
     */
    @Test
    public void testUpdatePasswordNonExistentUser() {
        Integer userId = ID5;
        when(userMapper.selectByPrimaryKey(userId)).thenReturn(null);

        assertThrows(RuntimeException.class, ()
                -> userService.updatePassWord(userId,
                "oldPass",
                "newPass",
                "newPass"));
    }

    /**
     * Test for updating user password when original password is incorrect.
     */
    @Test
    public void testUpdatePasswordIncorrectOriginalPassword() {
        Integer userId = 1;
        User user = new User();
        user.setId(userId);
        user.setUserPwd(Md5Util.encode("wrongOldPassword"));

        when(userMapper.selectByPrimaryKey(userId)).thenReturn(user);

        assertThrows(RuntimeException.class, ()
                -> userService.updatePassWord(userId,
                "oldPass",
                "newPass",
                "newPass"));
    }

    /**
     * Test for updating user password when new password is Inconsistent.
     */
    @Test
    public void testUpdatePasswordInconsistentNewPassword() {
        Integer userId = 1;
        User user = new User();
        user.setId(userId);
        user.setUserPwd(Md5Util.encode("oldPass"));

        when(userMapper.selectByPrimaryKey(userId)).thenReturn(user);

        assertThrows(RuntimeException.class, ()
                -> userService.updatePassWord(userId,
                "oldPass",
                "newPass",
                "differentNewPass"));
    }

    /**
     * Test for fail to update user password.
     */
    @Test
    public void testFailedToUpdatePassword() {
        Integer userId = 1;
        User user = new User();
        user.setId(userId);
        user.setUserPwd(Md5Util.encode("oldPass"));

        when(userMapper.selectByPrimaryKey(userId)).thenReturn(user);
        when(userMapper.updateByPrimaryKeySelective(user)).thenReturn(0);

        assertThrows(RuntimeException.class, ()
                -> userService.updatePassWord(userId,
                "oldPass",
                "newPass",
                "newPass"));
    }

    /**
     * Test for adding user.
     */
    @Test
    public void testAddUser() {
        User newUser = new User();
        newUser.setUserName("testUser");
        newUser.setEmail("test@example.com");
        newUser.setPhone("1234567890");
        when(userMapper.insertSelective(any(User.class))).thenReturn(1);
        userService.addUser(newUser);
        verify(userMapper, times(1)).insertSelective(any(User.class));
    }

    /**
     * Test for adding user when username exists.
     */
    @Test
    public void testAddUserExistingName() {
        User user = new User();
        user.setUserName("admin");

        when(userMapper.queryUserByName(user.getUserName())).thenReturn(user);

        assertThrows(RuntimeException.class, () -> userService.addUser(user));
    }

    /**
     * Test for fail to add user.
     */
    @Test
    public void testFailedToAddUser() {
        User user = new User();
        user.setUserName("newUser");
        user.setEmail("newUser@ems.com");
        user.setPhone("2222222222");

        when(userMapper.queryUserByName(user.getUserName())).thenReturn(null);
        when(userMapper.insertSelective(user)).thenReturn(0);

        assertThrows(RuntimeException.class, () -> userService.addUser(user));
    }

    /**
     * Test for updating a user with valid data.
     */
    @Test
    public void testUpdateUser() {
        User existingUser = new User();
        existingUser.setId(1);
        existingUser.setUserName("existingUser");
        existingUser.setEmail("user@example.com");
        existingUser.setPhone("1234567890");
        when(userMapper.selectByPrimaryKey(anyInt())).thenReturn(existingUser);
        when(userMapper.updateByPrimaryKeySelective(any(User.class))).
                thenReturn(1);
        userService.updateUser(existingUser);
        verify(userMapper, times(1)).
                updateByPrimaryKeySelective(any(User.class));
    }

    /**
     * Test for updating a user with a null ID.
     */
    @Test
    public void testUpdateUserNullId() {
        User user = new User();
        user.setId(null);

        assertThrows(RuntimeException.class, ()
                -> userService.updateUser(user));
    }

    /**
     * Test for updating a user that does not exist.
     */
    @Test
    public void testUpdateUserNonExistent() {
        User user = new User();
        user.setId(2);

        when(userMapper.selectByPrimaryKey(user.getId())).thenReturn(null);

        assertThrows(RuntimeException.class, ()
                -> userService.updateUser(user));
    }

    /**
     * Test for updating a user that already exists with the same username.
     */
    @Test
    public void testUpdateUserNonExisting() {
        User user = new User();
        user.setId(2);

        when(userMapper.selectByPrimaryKey(user.getId())).thenReturn(null);

        assertThrows(RuntimeException.class, ()
                -> userService.updateUser(user));
    }

    /**
     * Test for updating a username when username exists.
     */
    @Test
    public void testUpdateUserExistingName() {
        User user = new User();
        user.setId(1);
        user.setUserName("admin");

        User existingUser = new User();
        existingUser.setId(2);
        existingUser.setUserName("admin");

        when(userMapper.selectByPrimaryKey(user.getId())).thenReturn(user);
        when(userMapper.queryUserByName(user.getUserName())).
                thenReturn(existingUser);

        assertThrows(RuntimeException.class, ()
                -> userService.updateUser(user));
    }

    /**
     * Test for fail to update user records.
     */
    @Test
    public void testFailedToUpdateUserRecords() {
        User user = new User();
        user.setId(1);
        user.setUserName("admin");
        user.setEmail("admin@ems.com");
        user.setPhone("1111111111");

        when(userMapper.selectByPrimaryKey(user.getId())).
                thenReturn(user);  // assume user exists
        when(userMapper.queryUserByName(user.getUserName())).
                thenReturn(null); // assume no other user has the same username
        when(userMapper.updateByPrimaryKeySelective(user)).
                thenReturn(0); // simulate a failed update

        assertThrows(RuntimeException.class, ()
                -> userService.updateUser(user));
    }

    /**
     * Test for updating a username when username exists.
     */
    @Test
    public void testUsernameAlreadyExists() {
        User user = new User();
        user.setUserName("admin");
        User existingUser = new User();
        existingUser.setId(2);
        when(userMapper.queryUserByName(user.getUserName())).
                thenReturn(existingUser);  // mock that the user already exists

        assertThrows(RuntimeException.class, () -> userService.addUser(user));
    }

    /**
     * Test for deleting users by id with user role deletion failure.
     */
    @Test
    public void testDeleteByIdsWithUserRoleDeletionFailure() {
        Integer[] userIds = {ID1, ID2, ID3};

        // Mock to return a count greater than 0
        when(userRoleMapper.countUserRoleByUserId(anyInt())).thenReturn(1);

        // Mock to simulate deletion failure
        // (returning a value different from count)
        when(userRoleMapper.deleteUserRoleByUserId(anyInt())).thenReturn(0);

        // Mock userMapper.deleteBatch
        when(userMapper.deleteBatch(userIds)).thenReturn(userIds.length);

        // Adjust the exception type here to ParamsException
        Exception exception = assertThrows(ParamsException.class, () -> {
            userService.deleteByIds(userIds);
        });

        assertEquals("Failed to delete users!", exception.getMessage());
        // Additional verification as necessary
    }

    /**
     * Test for deleting users by id with no user roles.
     */
    @Test
    public void testDeleteByIdsWithNoUserRoles() {
        Integer[] userIds = {ID1, ID2, ID3};

        // Mock to return a count of 0, indicating no user roles
        when(userRoleMapper.countUserRoleByUserId(anyInt())).thenReturn(0);

        // Mock userMapper.deleteBatch
        when(userMapper.deleteBatch(userIds)).thenReturn(userIds.length);

        userService.deleteByIds(userIds);

        // Verify deleteUserRoleByUserId is never called
        verify(userRoleMapper, never()).deleteUserRoleByUserId(anyInt());

        // Verify deleteBatch is called once
        verify(userMapper, times(1)).deleteBatch(userIds);
    }

    /**
     * Test for deleting users by id user role deletion matches count.
     */
    @Test
    public void testDeleteByIdsUserRoleDeletionMatchesCount() {
        Integer[] userIds = {1};
        int count = 1;

        when(userRoleMapper.countUserRoleByUserId(anyInt())).thenReturn(count);
        when(userRoleMapper.deleteUserRoleByUserId(anyInt())).thenReturn(count);
        when(userMapper.deleteBatch(userIds)).thenReturn(1);

        userService.deleteByIds(userIds);

        verify(userRoleMapper, times(1)).deleteUserRoleByUserId(anyInt());
        verify(userMapper, times(1)).deleteBatch(userIds);
    }

    /**
     * Test for deleting users by id user role deletion does not match count.
     */
    @Test
    public void testDeleteByIdsUserRoleDeletionDoesNotMatchCount() {
        Integer[] userIds = {1};
        int count = 1;

        when(userRoleMapper.countUserRoleByUserId(anyInt())).thenReturn(count);
        when(userRoleMapper.deleteUserRoleByUserId(anyInt())).
                thenReturn(0); // Different from count
        when(userMapper.deleteBatch(userIds)).thenReturn(1);

        Exception exception = assertThrows(ParamsException.class, () -> {
            userService.deleteByIds(userIds);
        });

        assertEquals("Failed to delete users!", exception.getMessage());
        verify(userRoleMapper, times(1)).deleteUserRoleByUserId(anyInt());
        verify(userMapper, times(1)).deleteBatch(userIds);
    }

    /**
     * Test for deleting users by some invalid id.
     */
    @Test
    public void testDeleteByIdsSomeInvalid() {
        Integer[] ids = {ID1, ID2, ID3};

        when(userMapper.deleteBatch(ids)).thenReturn(2);

        assertThrows(RuntimeException.class, ()
                -> userService.deleteByIds(ids));
    }

    /**
     * Test for deleting users by null id.
     */
    @Test
    public void testDeleteByIdsNullOrEmpty() {
        Integer[] ids = {};

        assertThrows(RuntimeException.class, ()
                -> userService.deleteByIds(ids));
    }

    /**
     * Test for partial fail to delete users by id.
     */
    @Test
    public void testDeleteByIdsPartialFailure() {
        Integer[] ids = {ID1, ID2, ID3};
        when(userMapper.deleteBatch(ids)).
                thenReturn(2);  // assume only 2 out of 3 are deleted

        assertThrows(RuntimeException.class, ()
                -> userService.deleteByIds(ids));
    }

    /**
     * Test for deleting user records by IDs when no IDs are provided.
     */
    @Test
    public void testDeleteByIdsNoIdsProvided() {
        Integer[] ids = {};

        assertThrows(RuntimeException.class, ()
                -> userService.deleteByIds(ids));
    }

    /**
     * Test for deleting user records by IDs when null IDs are provided.
     */
    @Test
    public void testDeleteByIdsNullIds() {
        assertThrows(RuntimeException.class, ()
                -> userService.deleteByIds(null));
    }
    /**
     * Test for relationUserRole.
     */
    @Test
    public void testRelationUserRoleDeleteExistingRoles() {
        Integer userId = 1;
        String roleIds = ""; // No new roles to add

        // Simulate existing roles
        when(userRoleMapper.countUserRoleByUserId(userId)).thenReturn(1);
        when(userRoleMapper.deleteUserRoleByUserId(userId)).thenReturn(1);

        userService.relationUserRole(userId, roleIds);

        verify(userRoleMapper, times(1)).deleteUserRoleByUserId(userId);
    }

    /**
     * Test for relationUserRole.
     */
    @Test
    public void testRelationUserRoleAddNewRoles() {
        Integer userId = 1;
        String roleIds = "2,3"; // New roles to add

        // Simulate no existing roles
        when(userRoleMapper.countUserRoleByUserId(userId)).thenReturn(0);

        List<UserRole> userRoles = new ArrayList<>();
        for (String roleId : roleIds.split(",")) {
            UserRole userRole = new UserRole();
            userRole.setRoleId(Integer.parseInt(roleId));
            userRole.setUserId(userId);
            userRole.setCreateDate(new Date());
            userRole.setUpdateDate(new Date());
            userRoles.add(userRole);
        }

        when(userRoleMapper.insertBatch(anyList())).
                thenReturn(userRoles.size());

        userService.relationUserRole(userId, roleIds);

        verify(userRoleMapper, times(1)).insertBatch(anyList());
    }

    /**
     * Test for relationUserRole.
     */
    @Test
    public void testRelationUserRoleDeleteAndAddRoles() {
        Integer userId = 1;
        String roleIds = "4,5"; // New roles to add

        // Simulate existing roles
        when(userRoleMapper.countUserRoleByUserId(userId)).thenReturn(1);
        when(userRoleMapper.deleteUserRoleByUserId(userId)).thenReturn(1);

        List<UserRole> userRoles = new ArrayList<>();
        for (String roleId : roleIds.split(",")) {
            UserRole userRole = new UserRole();
            userRole.setRoleId(Integer.parseInt(roleId));
            userRole.setUserId(userId);
            userRole.setCreateDate(new Date());
            userRole.setUpdateDate(new Date());
            userRoles.add(userRole);
        }

        when(userRoleMapper.insertBatch(anyList())).
                thenReturn(userRoles.size());

        userService.relationUserRole(userId, roleIds);

        verify(userRoleMapper, times(1)).deleteUserRoleByUserId(userId);
        verify(userRoleMapper, times(1)).insertBatch(anyList());
    }
}
