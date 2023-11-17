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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

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
        User user = new User();
        user.setUserName("newUser");
        user.setEmail("newuser@ems.com");
        user.setPhone("2222222222");

        when(userMapper.queryUserByName(user.getUserName())).thenReturn(null);
        when(userMapper.insertSelective(any(User.class))).thenReturn(1);

        userService.addUser(user);

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
     * Test for deleting users by id.
     */
    @Test
    public void testDeleteByIdsValid() {
        Integer[] ids = {ID1, ID2, ID3};

        when(userMapper.deleteBatch(ids)).thenReturn(ids.length);

        userService.deleteByIds(ids);
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
}
