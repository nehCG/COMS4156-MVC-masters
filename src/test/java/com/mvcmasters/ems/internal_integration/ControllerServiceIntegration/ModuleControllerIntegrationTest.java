package com.mvcmasters.ems.internal_integration.ControllerServiceIntegration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;
import com.mvcmasters.ems.model.TreeModel;
import com.mvcmasters.ems.service.ModuleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;

/**
 * Internal Integration Test for testing interaction
 * between ModuleController and ModuleService.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class ModuleControllerIntegrationTest {
    /**
     * Mock of HTTP requests.
     */
    @Autowired
    private MockMvc mockMvc;
    /**
     * Mock of ModuleService.
     */
    @MockBean
    private ModuleService moduleService;

    /**
     * Utility function called before each test to set up
     * ModuleService and mockTreeModels.
     */
    @BeforeEach
    void setUp() {
        List<TreeModel> mockTreeModels =
                Arrays.asList(new TreeModel(), new TreeModel());
        when(moduleService.queryAllModules(anyInt()))
                .thenReturn(mockTreeModels);
    }
    /**
     * Test for QueryAllModules method.
     */
    @Test
    public void testQueryAllModules() throws Exception {
        mockMvc.perform(get("/module/queryAllModules").param("roleId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").isNotEmpty());

        verify(moduleService).queryAllModules(1);
    }
    /**
     * Test for ToAddGrantPage method.
     */
    @Test
    public void testToAddGrantPage() throws Exception {
        mockMvc.perform(get("/module/toAddGrantPage").param("roleId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("role/grant"));
    }

}
