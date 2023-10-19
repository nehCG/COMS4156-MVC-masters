package com.mvcmasters.ems.controller;

import com.mvcmasters.ems.base.ResultInfo;
import com.mvcmasters.ems.model.UserModel;
import com.mvcmasters.ems.query.UserQuery;
import com.mvcmasters.ems.service.UserService;
import com.mvcmasters.ems.vo.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;

/**
 * This class represents a test for the UserController class.
 */
public class UserControllerTest {
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
     * Mocked UserService for testing UserController.
     */
    @Mock
    private UserService userService;

    /**
     * The UserController instance to be tested.
     */
    @InjectMocks
    private UserController userController;

    /**
     * Set up the test by initializing Mockito annotations.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test the userLogin method.
     */
    @Test
    public void testUserLogin() {
        String userName = "john_doe";
        String userPwd = "password123";
        UserModel userModel = new UserModel();
        when(userService.userLogin(userName, userPwd)).thenReturn(userModel);

        ResultInfo resultInfo = userController.userLogin(userName, userPwd);

        assertEquals(userModel, resultInfo.getResult());
        verify(userService, times(1)).userLogin(userName, userPwd);
    }

    /**
     * Test the updateUserPassword method.
     */
    @Test
    public void testUpdateUserPassword() {
        Integer userId = 1;
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        String repeatPassword = "newPassword";

        userController.
                updateUserPassword(userId, oldPassword,
                        newPassword, repeatPassword);

        verify(userService, times(1)).
                updatePassWord(userId, oldPassword,
                        newPassword, repeatPassword);
    }

    /**
     * Test the selectByParams method.
     */
    @Test
    public void testSelectByParams() {
        UserQuery userQuery = new UserQuery();
        Map<String, Object> expectedResult = new HashMap<>();
        when(userService.queryByParamsForTable(userQuery)).
                thenReturn(expectedResult);

        Map<String, Object> result = userController.selectByParams(userQuery);

        assertEquals(expectedResult, result);
        verify(userService, times(1)).queryByParamsForTable(userQuery);
    }

    /**
     * Test the addUser method.
     */
    @Test
    public void testAddUser() {
        User user = new User();
        doNothing().when(userService).addUser(user);

        ResultInfo resultInfo = userController.addUser(user);

        assertEquals("User added successfully!", resultInfo.getMsg());
        verify(userService, times(1)).addUser(user);
    }

    /**
     * Test the updateUser method.
     */
    @Test
    public void testUpdateUser() {
        User user = new User();
        doNothing().when(userService).updateUser(user);

        ResultInfo resultInfo = userController.updateUser(user);

        assertEquals("User records updated successfully!", resultInfo.getMsg());
        verify(userService, times(1)).updateUser(user);
    }

    /**
     * Test the deleteUser method.
     */
    @Test
    public void testDeleteUser() {
        Integer[] ids = {ID1, ID2, ID3};
        doNothing().when(userService).deleteByIds(ids);

        ResultInfo resultInfo = userController.deleteUser(ids);

        assertEquals("User deleted successfully!", resultInfo.getMsg());
        verify(userService, times(1)).deleteByIds(ids);
    }
}
