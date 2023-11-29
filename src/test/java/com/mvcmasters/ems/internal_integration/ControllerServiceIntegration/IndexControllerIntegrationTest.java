package com.mvcmasters.ems.internal_integration.ControllerServiceIntegration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import jakarta.servlet.http.Cookie;
import com.mvcmasters.ems.service.PermissionService;
import com.mvcmasters.ems.service.UserService;
import com.mvcmasters.ems.vo.User;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Internal Integration Test for testing interaction
 * between IndexController and IndexService.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class IndexControllerIntegrationTest {
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
     * Mock of permissionService.
     */
    @MockBean
    private PermissionService permissionService;
    /**
     * Test for Index method.
     */
    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
    /**
     * Test for Welcome method.
     */
    @Test
    public void testWelcome() throws Exception {
        mockMvc.perform(get("/welcome"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"));
    }
    /**
     * Test for Main method.
     */
    @Test
    public void testMain() throws Exception {
        User mockUser = new User();
        List<String> mockPermissions = Arrays
                .asList("Permission1", "Permission2");

        when(userService.selectByPrimaryKey(anyInt())).thenReturn(mockUser);
        when(permissionService.queryUserHasRoleHasPermissionByUserId(anyInt()))
                .thenReturn(mockPermissions);

        mockMvc.perform(get("/main").cookie(new Cookie("userId", "123")))
                .andExpect(status().isOk())
                .andExpect(view().name("main"))
                .andExpect(model().attributeExists("user", "permissions"));
    }
}
