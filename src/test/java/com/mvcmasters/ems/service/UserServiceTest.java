package com.mvcmasters.ems.service;

import com.mvcmasters.ems.repository.UserMapper;
import com.mvcmasters.ems.vo.User;
import com.mvcmasters.ems.model.UserModel;
import com.mvcmasters.ems.utils.Md5Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        //MockitoAnnotations.initMocks(this);
    }

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
        assertEquals(user.getId(), returnedUserModel.getUserId());
        assertEquals(user.getUserName(), returnedUserModel.getUserName());
        assertEquals(user.getTrueName(), returnedUserModel.getTrueName());
    }

    @Test
    public void testUserLoginUserDoesNotExist() {
        String userName = "admin";
        String userPwd = "123456";

        when(userMapper.queryUserByName(userName)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> userService.userLogin(userName, userPwd));
    }

    @Test
    public void testUserLoginIncorrectPassword() {
        String userName = "admin";
        String userPwd = "wrongpassword";

        User user = new User();
        user.setId(1);
        user.setUserName("admin");
        user.setUserPwd(Md5Util.encode("123456"));

        when(userMapper.queryUserByName(userName)).thenReturn(user);

        assertThrows(RuntimeException.class, () -> userService.userLogin(userName, userPwd));
    }

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
        when(userMapper.updateByPrimaryKeySelective(any(User.class))).thenReturn(1);

        userService.updatePassWord(userId, oldPwd, newPwd, repeatPwd);
    }

    @Test
    public void testUpdatePasswordNonExistentUser() {
        Integer userId = 5;
        when(userMapper.selectByPrimaryKey(userId)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> userService.updatePassWord(userId, "oldPass", "newPass", "newPass"));
    }

    @Test
    public void testUpdatePasswordIncorrectOriginalPassword() {
        Integer userId = 1;
        User user = new User();
        user.setId(userId);
        user.setUserPwd(Md5Util.encode("wrongOldPassword"));

        when(userMapper.selectByPrimaryKey(userId)).thenReturn(user);

        assertThrows(RuntimeException.class, () -> userService.updatePassWord(userId, "oldPass", "newPass", "newPass"));
    }

    @Test
    public void testUpdatePasswordInconsistentNewPassword() {
        Integer userId = 1;
        User user = new User();
        user.setId(userId);
        user.setUserPwd(Md5Util.encode("oldPass"));

        when(userMapper.selectByPrimaryKey(userId)).thenReturn(user);

        assertThrows(RuntimeException.class, () -> userService.updatePassWord(userId, "oldPass", "newPass", "differentNewPass"));
    }

    @Test
    public void testFailedToUpdatePassword() {
        Integer userId = 1;
        User user = new User();
        user.setId(userId);
        user.setUserPwd(Md5Util.encode("oldPass"));

        when(userMapper.selectByPrimaryKey(userId)).thenReturn(user);
        when(userMapper.updateByPrimaryKeySelective(user)).thenReturn(0); // simulate a failed update

        assertThrows(RuntimeException.class, () -> userService.updatePassWord(userId, "oldPass", "newPass", "newPass"));
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUserName("newUser");
        user.setEmail("newuser@ems.com");
        user.setPhone("2222222222");

        when(userMapper.queryUserByName(user.getUserName())).thenReturn(null);
        when(userMapper.insertSelective(any(User.class))).thenReturn(1);

        userService.addUser(user);

    }

    @Test
    public void testAddUserExistingName() {
        User user = new User();
        user.setUserName("admin");

        when(userMapper.queryUserByName(user.getUserName())).thenReturn(user);

        assertThrows(RuntimeException.class, () -> userService.addUser(user));
    }

    @Test
    public void testFailedToAddUser() {
        User user = new User();
        user.setUserName("newUser");
        user.setEmail("newUser@ems.com");
        user.setPhone("2222222222");

        when(userMapper.queryUserByName(user.getUserName())).thenReturn(null); // simulate the username doesn't exist
        when(userMapper.insertSelective(user)).thenReturn(0); // simulate a failed insertion

        assertThrows(RuntimeException.class, () -> userService.addUser(user));
    }

    @Test
    public void testUpdateUserValid() {
        User user = new User();
        user.setId(1);
        user.setUserName("updatedAdmin");
        user.setEmail("updatedAdmin@ems.com");
        user.setPhone("2222222222");

        when(userMapper.selectByPrimaryKey(user.getId())).thenReturn(user);
        when(userMapper.updateByPrimaryKeySelective(user)).thenReturn(1);
        when(userMapper.queryUserByName(user.getUserName())).thenReturn(null);

        userService.updateUser(user);
    }

    @Test
    public void testUpdateUserNullId() {
        User user = new User();
        user.setId(null);

        assertThrows(RuntimeException.class, () -> userService.updateUser(user));
    }

    @Test
    public void testUpdateUserNonExistent() {
        User user = new User();
        user.setId(2);

        when(userMapper.selectByPrimaryKey(user.getId())).thenReturn(null);

        assertThrows(RuntimeException.class, () -> userService.updateUser(user));
    }


    @Test
    public void testUpdateUserNonExisting() {
        User user = new User();
        user.setId(2);

        when(userMapper.selectByPrimaryKey(user.getId())).thenReturn(null);

        assertThrows(RuntimeException.class, () -> userService.updateUser(user));
    }

    @Test
    public void testUpdateUserExistingName() {
        User user = new User();
        user.setId(1);
        user.setUserName("admin");

        User existingUser = new User();
        existingUser.setId(2);
        existingUser.setUserName("admin");

        when(userMapper.selectByPrimaryKey(user.getId())).thenReturn(user);
        when(userMapper.queryUserByName(user.getUserName())).thenReturn(existingUser);

        assertThrows(RuntimeException.class, () -> userService.updateUser(user));
    }

    @Test
    public void testFailedToUpdateUserRecords() {
        User user = new User();
        user.setId(1);
        user.setUserName("admin");
        user.setEmail("admin@ems.com");
        user.setPhone("1111111111");

        when(userMapper.selectByPrimaryKey(user.getId())).thenReturn(user);  // assume user exists
        when(userMapper.queryUserByName(user.getUserName())).thenReturn(null); // assume no other user has the same username
        when(userMapper.updateByPrimaryKeySelective(user)).thenReturn(0); // simulate a failed update

        assertThrows(RuntimeException.class, () -> userService.updateUser(user));
    }

    @Test
    public void testUsernameAlreadyExists() {
        User user = new User();
        user.setUserName("admin");
        User existingUser = new User();
        existingUser.setId(2);
        when(userMapper.queryUserByName(user.getUserName())).thenReturn(existingUser);  // mock that the user already exists

        assertThrows(RuntimeException.class, () -> userService.addUser(user));
    }


    @Test
    public void testDeleteByIdsValid() {
        Integer[] ids = {1, 2, 3};

        when(userMapper.deleteBatch(ids)).thenReturn(ids.length);

        userService.deleteByIds(ids);
    }

    @Test
    public void testDeleteByIdsSomeInvalid() {
        Integer[] ids = {1, 2, 3};

        when(userMapper.deleteBatch(ids)).thenReturn(2);

        assertThrows(RuntimeException.class, () -> userService.deleteByIds(ids));
    }

    @Test
    public void testDeleteByIdsNullOrEmpty() {
        Integer[] ids = {};

        assertThrows(RuntimeException.class, () -> userService.deleteByIds(ids));
    }

    @Test
    public void testDeleteByIdsPartialFailure() {
        Integer[] ids = {1, 2, 3};
        when(userMapper.deleteBatch(ids)).thenReturn(2);  // let's assume only 2 out of 3 are deleted

        assertThrows(RuntimeException.class, () -> userService.deleteByIds(ids));
    }

    @Test
    public void testDeleteByIdsNoIdsProvided() {
        Integer[] ids = {};

        assertThrows(RuntimeException.class, () -> userService.deleteByIds(ids));
    }

    @Test
    public void testDeleteByIdsNullIds() {
        assertThrows(RuntimeException.class, () -> userService.deleteByIds(null));
    }
}