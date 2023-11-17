package com.mvcmasters.ems.controller;
import com.mvcmasters.ems.service.UserService;
import com.mvcmasters.ems.utils.LoginUserUtil;
import com.mvcmasters.ems.vo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.lang.reflect.Field;

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
    private IndexController controller;

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
        Integer userId = 1; // Example user ID
        User user = new User(); // Mock user object

        try (MockedStatic<LoginUserUtil> mockedStatic
                     = Mockito.mockStatic(LoginUserUtil.class)) {
            mockedStatic.when(() ->
                    LoginUserUtil.releaseUserIdFromCookie(request)).
                    thenReturn(userId);
            when(userService.selectByPrimaryKey(userId)).thenReturn(user);
            when(request.getSession()).thenReturn(session);

            String viewName = controller.main(request);

            assertEquals("main", viewName);
            verify(request).getSession();
            verify(session).setAttribute("user", user);
            verify(userService).selectByPrimaryKey(userId);
        }
    }
}
