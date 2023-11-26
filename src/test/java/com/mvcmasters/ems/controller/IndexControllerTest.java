package com.mvcmasters.ems.controller;
import com.mvcmasters.ems.service.PermissionService;
import com.mvcmasters.ems.service.UserService;
import com.mvcmasters.ems.utils.LoginUserUtil;
import com.mvcmasters.ems.vo.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Test class for IndexController.
 * This class contains unit tests to verify
 * the behavior of IndexController methods.
 */
public class IndexControllerTest {

    /**
     * Mock of UserService used for handling user-related operations.
     */
    @Mock
    private UserService userService;

    /**
     * Mock of HttpServletRequest used to simulate
     * the behavior of HTTP requests.
     */
    @Mock
    private HttpServletRequest request;

    /**
     * Mock of HttpSession used to simulate the behavior of an HTTP session.
     */
    @Mock
    private HttpSession session;

    /**
     * Instance of IndexController under test.
     */
    @InjectMocks
    private IndexController controller;

    /**
     * Mock of PermissionService for handling permission operations.
     */
    @Mock
    private PermissionService permissionService;

    /**
     * Sets up the testing environment before each test.
     * Initialize mocks and injects them into the IndexController instance.
     *
     * @throws Exception if there is an error in setting up the test.
     */
    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        controller = new IndexController();
        injectMocks(controller, "userService", userService);
        injectMocks(controller, "permissionService", permissionService);

        when(request.getSession()).thenReturn(session);
    }

    // Utility method for injecting mock
    private void injectMocks(final Object target,
                             final String fieldName,
                             final Object mockValue) throws
            NoSuchFieldException, IllegalAccessException {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, mockValue);
    }

    /**
     * Tests the index method of IndexController.
     * Verifies if the index view name is correctly returned.
     */
    @Test
    void testIndex() {
        String viewName = controller.index();
        assertEquals("index", viewName);
    }

    /**
     * Tests the welcome method of IndexController.
     * Verifies if the welcome view name is correctly returned.
     */
    @Test
    void testWelcome() {
        String viewName = controller.welcome();
        assertEquals("welcome", viewName);
    }

    /**
     * Tests the main method of IndexController.
     * Verifies if the main view name is correctly returned
     * and if user information is correctly set in the session.
     */
    @Test
    void testMain() {
        // Arrange
        Integer userId = 1;
        User mockUser = new User();
        List<String> mockPermissions = List.of("READ", "WRITE");

        Cookie[] cookies = {new Cookie("user", "1")};
        when(request.getCookies()).thenReturn(cookies);

        try (MockedStatic<LoginUserUtil> mockedLoginUserUtil
                     = Mockito.mockStatic(LoginUserUtil.class)) {
            mockedLoginUserUtil.when(() ->
                    LoginUserUtil.releaseUserIdFromCookie(request)).
                    thenReturn(userId);

            when(userService.selectByPrimaryKey(userId)).thenReturn(mockUser);
            when(permissionService.
                    queryUserHasRoleHasPermissionByUserId(userId)).
                    thenReturn(mockPermissions);

            Model model = new ExtendedModelMap();

            // Act
            String viewName = controller.main(model, request);

            // Assert
            assertEquals("main", viewName);
            assertEquals(mockUser, model.asMap().get("user"));
            assertEquals(mockPermissions, model.asMap().get("permissions"));
        }
    }
}
