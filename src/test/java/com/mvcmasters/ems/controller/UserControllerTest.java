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
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

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

    @Test
    public void testUpdateUserPassword() {
        Integer userId = 1;
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        String repeatPassword = "newPassword";

        userController.updateUserPassword(userId, oldPassword, newPassword, repeatPassword);

        verify(userService, times(1)).updatePassWord(userId, oldPassword, newPassword, repeatPassword);
    }

    @Test
    public void testSelectByParams() {
        UserQuery userQuery = new UserQuery();
        Map<String, Object> expectedResult = new HashMap<>();
        when(userService.queryByParamsForTable(userQuery)).thenReturn(expectedResult);

        Map<String, Object> result = userController.selectByParams(userQuery);

        assertEquals(expectedResult, result);
        verify(userService, times(1)).queryByParamsForTable(userQuery);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        doNothing().when(userService).addUser(user);

        ResultInfo resultInfo = userController.addUser(user);

        assertEquals("User added successfully!", resultInfo.getMsg());
        verify(userService, times(1)).addUser(user);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        doNothing().when(userService).updateUser(user);

        ResultInfo resultInfo = userController.updateUser(user);

        assertEquals("User records updated successfully!", resultInfo.getMsg());
        verify(userService, times(1)).updateUser(user);
    }

    @Test
    public void testDeleteUser() {
        Integer[] ids = {1, 2, 3};
        doNothing().when(userService).deleteByIds(ids);

        ResultInfo resultInfo = userController.deleteUser(ids);

        assertEquals("User deleted successfully!", resultInfo.getMsg());
        verify(userService, times(1)).deleteByIds(ids);
    }
}
