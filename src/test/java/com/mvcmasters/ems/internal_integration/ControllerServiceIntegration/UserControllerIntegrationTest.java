package com.mvcmasters.ems.internal_integration.ControllerServiceIntegration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvcmasters.ems.model.UserModel;
import com.mvcmasters.ems.query.UserQuery;
import com.mvcmasters.ems.service.UserService;
import com.mvcmasters.ems.utils.LoginUserUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.mvcmasters.ems.vo.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Internal Integration Test for testing interaction
 * between UserController and UserService.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {
    /**
     * Mock of HTTP requests.
     */
    @Autowired
    private MockMvc mockMvc;
    /**
     * Mock of UserService.
     */
    @MockBean
    private UserService userService;

    /**
     * Mock of LoginUserUtil.
     */
    @MockBean
    private LoginUserUtil loginUserUtil;
    /**
     * Test the UserLogin Method.
     */
    @Test
    public void testUserLogin() throws Exception {
        // Arrange
        String userName = "testUser";
        String userPwd = "testPassword";
        UserModel userModel = new UserModel();
        userModel.setUserName(userName);

        given(userService.userLogin(userName, userPwd)).willReturn(userModel);

        // Act & Assert
        mockMvc.perform(post("/user/login")
                        .param("userName", userName)
                        .param("userPwd", userPwd))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.userName").value(userName));

        verify(userService).userLogin(userName, userPwd);
    }
    /**
     * Test the UpdateUserPassword Method.
     */
    @Test
    public void testUpdateUserPassword() throws Exception {
        // Arrange
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        String repeatPassword = "newPassword";
        Integer userId = 1;

        // Mock the static method in LoginUserUtil
        try (MockedStatic<LoginUserUtil> mockedLoginUserUtil =
                     Mockito.mockStatic(LoginUserUtil.class)) {
            mockedLoginUserUtil.when(() -> LoginUserUtil
                            .releaseUserIdFromCookie(
                                    any(HttpServletRequest.class)))
                    .thenReturn(userId);

            doNothing().when(userService)
                    .updatePassWord(userId,
                            oldPassword, newPassword, repeatPassword);

            // Act & Assert
            mockMvc.perform(post("/user/updatePwd")
                            .param("oldPassword", oldPassword)
                            .param("newPassword", newPassword)
                            .param("repeatPassword", repeatPassword))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.msg").value("success"));

            verify(userService, times(1))
                    .updatePassWord(userId,
                            oldPassword, newPassword, repeatPassword);
        }
    }
    /**
     * Test the SelectByParams Method.
     */
    @Test
    public void testSelectByParams() throws Exception {
        // Arrange
        UserQuery userQuery = new UserQuery();
        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("key", "value");

        given(userService.queryByParamsForTable(any(UserQuery.class)))
                .willReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(get("/user/list")
                        .param("param1", "value1")
                        .param("param2", "value2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("value"));

        verify(userService).queryByParamsForTable(any(UserQuery.class));
    }
    /**
     * Define the objectMapper for HTTP request.
     */
    private final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * Test the AddUser Method.
     */
    @Test
    public void testAddUser() throws Exception {
        // Arrange
        User user = new User();
        doNothing().when(userService).addUser(any(User.class));

        // Act & Assert
        mockMvc.perform(post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("User added successfully!"));

        verify(userService).addUser(any(User.class));
    }
    /**
     * Test the UpdateUser Method.
     */
    @Test
    public void testUpdateUser() throws Exception {
        // Arrange
        User user = new User();
        doNothing().when(userService).updateUser(any(User.class));

        // Act & Assert
        mockMvc.perform(post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg")
                        .value("User records updated successfully!"));

        verify(userService).updateUser(any(User.class));
    }
    /**
     * Test the DeleteUser Method.
     */
    @Test
    public void testDeleteUser() throws Exception {
        // Arrange
        Integer[] ids = new Integer[]{1, 2};
        doNothing().when(userService).deleteByIds(ids);

        // Act & Assert
        mockMvc.perform(post("/user/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ids)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg")
                        .value("User deleted successfully!"));
    }
    /**
     * Test the ToPasswordPage Method.
     */
    @Test
    public void testToPasswordPage() throws Exception {
        mockMvc.perform(get("/user/toPasswordPage"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/password"));
    }
    /**
     * Test the Index Method.
     */
    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/user/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/user"));
    }
    /**
     * Test the ToAddOrUpdateUserPage Method with valid ID.
     */
    @Test
    public void testToAddOrUpdateUserPageWithId() throws Exception {
        Integer userId = 1;
        User user = new User();
        given(userService.selectByPrimaryKey(userId)).willReturn(user);

        mockMvc.perform(get("/user/toAddOrUpdateUserPage")
                        .param("id", userId.toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add_update"));

        verify(userService).selectByPrimaryKey(userId);
    }
    /**
     * Test the ToAddOrUpdateUserPage Method with invalid ID.
     */
    @Test
    public void testToAddOrUpdateUserPageWithoutId() throws Exception {
        mockMvc.perform(get("/user/toAddOrUpdateUserPage"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add_update"))
                .andExpect(model().attributeDoesNotExist("userInfo"));
    }
}
