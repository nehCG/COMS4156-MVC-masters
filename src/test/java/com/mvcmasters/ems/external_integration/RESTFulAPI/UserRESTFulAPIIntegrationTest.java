package com.mvcmasters.ems.external_integration.RESTFulAPI;
import com.mvcmasters.ems.base.ResultInfo;
import com.mvcmasters.ems.query.UserQuery;
import com.mvcmasters.ems.vo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

/**
 * External Integration Test of UserMapper
 * with MySQL database.
 */
@SpringBootTest
public class UserRESTFulAPIIntegrationTest {
    /**
     * To make real HTTP request.
     */
    private final RestTemplate restTemplate;
    /**
     * Utility Function for Building HTTP requests.
     * @param builder Builder for RestTemplate
     */
    @Autowired
    public UserRESTFulAPIIntegrationTest(
            final RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
    /**
     * Test the UserLogin Endpoint by making HTTP requests.
     */
    @Test
    public void testUserLogin() {
        Map<String, String> loginDetails = Map.of("userName", "testUser", "userPwd", "testPwd");
        ResponseEntity<ResultInfo> response = restTemplate.postForEntity(
                "http://localhost:8080/ems/user/login",
                loginDetails,
                ResultInfo.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    /**
     * Test the UpdateUserPassword Endpoint by making HTTP requests.
     */
    @Test
    public void testUpdateUserPassword() {
        Map<String, String> passwordDetails = Map.of(
                "oldPassword", "oldPwd",
                "newPassword", "newPwd",
                "repeatPassword", "newPwd"
        );
        ResponseEntity<ResultInfo> response = restTemplate.postForEntity(
                "http://localhost:8080/ems/user/updatePwd",
                passwordDetails,
                ResultInfo.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    /**
     * Test the ToPasswordPage Endpoint by making HTTP requests.
     */
    @Test
    public void testToPasswordPage() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:8080/ems/user/toPasswordPage",
                String.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("user/password"));
    }
    /**
     * Test the Index Endpoint by making HTTP requests.
     */
    @Test
    public void testIndex() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:8080/ems/user/index",
                String.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("user/user"));
    }
    /**
     * Test the ToAddOrUpdateUserPage Endpoint by making HTTP requests.
     */
    @Test
    public void testToAddOrUpdateUserPage() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:8080/ems/user/toAddOrUpdateUserPage?id=1",
                String.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    /**
     * Test the AddUser Endpoint by making HTTP requests.
     */
    @Test
    public void testAddUser() {
        User newUser = new User();
        HttpEntity<User> request = new HttpEntity<>(newUser);
        ResponseEntity<ResultInfo> response = restTemplate.postForEntity(
                "http://localhost:8080/ems/user/add",
                request,
                ResultInfo.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    /**
     * Test the UpdateUser Endpoint by making HTTP requests.
     */
    @Test
    public void testUpdateUser() {
        User existingUser = new User();
        HttpEntity<User> request = new HttpEntity<>(existingUser);
        ResponseEntity<ResultInfo> response = restTemplate.postForEntity(
                "http://localhost:8080/ems/user/update",
                request,
                ResultInfo.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    /**
     * Test the DeleteUser Endpoint by making HTTP requests.
     */
    @Test
    public void testDeleteUser() {
        Integer[] idsToDelete = {1, 2};
        ResponseEntity<ResultInfo> response = restTemplate.exchange(
                "http://localhost:8080/ems/user/delete",
                HttpMethod.POST,
                new HttpEntity<>(idsToDelete),
                ResultInfo.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    /**
     * Test the SelectByParams Endpoint by making HTTP requests.
     */
    @Test
    public void testSelectByParams() {
        UserQuery userQuery = new UserQuery();
        ResponseEntity<Map> response = restTemplate.postForEntity(
                "http://localhost:8080/ems/user/list",
                userQuery,
                Map.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
