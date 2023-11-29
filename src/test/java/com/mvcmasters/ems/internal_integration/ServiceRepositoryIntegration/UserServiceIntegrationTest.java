package com.mvcmasters.ems.internal_integration.ServiceRepositoryIntegration;

import com.mvcmasters.ems.model.UserModel;
import com.mvcmasters.ems.repository.UserMapper;
import com.mvcmasters.ems.repository.UserRoleMapper;
import com.mvcmasters.ems.service.UserService;
import com.mvcmasters.ems.utils.Md5Util;
import com.mvcmasters.ems.vo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceIntegrationTest {
    /**
     * UserService to be tested.
     */
    @Autowired
    private UserService userService;
    /**
     * Isolating external database using MockBean.
     */
    @MockBean
    private UserMapper userMapper;
    /**
     * Isolating external database using MockBean.
     */
    @MockBean
    private UserRoleMapper userRoleMapper;
    /**
     * Test for UserLogin method.
     */
    @Test
    public void testUserLogin() {
        User user = new User();
        user.setId(1);
        user.setUserName("Test User");
        user.setUserPwd(Md5Util.encode("Test Pwd"));
        when(userMapper.queryUserByName("Test User")).thenReturn(user);
        UserModel userModel = userService.userLogin("Test User", "Test Pwd");
        assertNotNull(userModel);
        assertEquals(user.getId(), 1);
        assertEquals(user.getUserName(), userModel.getUserName());
        assertEquals(user.getTrueName(), userModel.getTrueName());
    }
    /**
     * Test for DeleteByIds method.
     */
    @Test
    public void testDeleteByIds() {
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
     * Test for DeleteByIds method.
     */
    @Test
    public void testRelationUserRoleDeleteExistingRoles() {
        Integer userId = 1;
        String roleIds = "";
        when(userRoleMapper.countUserRoleByUserId(userId)).thenReturn(1);
        when(userRoleMapper.deleteUserRoleByUserId(userId)).thenReturn(1);
        userService.relationUserRole(userId, roleIds);
        verify(userRoleMapper, times(1)).deleteUserRoleByUserId(userId);
    }
}
