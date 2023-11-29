package com.mvcmasters.ems.internal_integration.ControllerServiceIntegration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;
import com.mvcmasters.ems.model.SharedDataModel;
import com.mvcmasters.ems.service.SharedDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;

/**
 * Internal Integration Test for testing interaction
 * between UserController and UserService.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SharedSpaceControllerIntegrationTest {
    /**
     * Mock of HTTP requests.
     */
    @Autowired
    private MockMvc mockMvc;
    /**
     * Mock of SharedDataService.
     */
    @MockBean
    private SharedDataService sharedDataService;
    /**
     * Test the AddSharedData Method.
     */
    @Test
    public void testAddSharedData() throws Exception {
        SharedDataModel sharedData = new SharedDataModel();
        doNothing().when(sharedDataService)
                .addSharedData(any(SharedDataModel.class));

        mockMvc.perform(post("/announcement/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"data\":\"Example data\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Shared data added successfully!"));

        verify(sharedDataService).addSharedData(any(SharedDataModel.class));
    }
    /**
     * Test the GetSharedDataById Method.
     */
    @Test
    public void testGetSharedDataById() throws Exception {
        SharedDataModel sharedData = new SharedDataModel();
        when(sharedDataService.getSharedDataById(anyInt()))
                .thenReturn(sharedData);

        mockMvc.perform(get("/announcement/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());

        verify(sharedDataService).getSharedDataById(1);
    }
    /**
     * Test the GetAllSharedData Method.
     */
    @Test
    public void testGetAllSharedData() throws Exception {
        List<SharedDataModel> sharedDataList =
                Arrays.asList(new SharedDataModel(), new SharedDataModel());
        when(sharedDataService.getAllSharedData()).thenReturn(sharedDataList);

        mockMvc.perform(get("/announcement/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists());

        verify(sharedDataService).getAllSharedData();
    }
    /**
     * Test the UpdateSharedData Method.
     */
    @Test
    public void testUpdateSharedData() throws Exception {
        doNothing().when(sharedDataService)
                .updateSharedData(anyInt(), any(SharedDataModel.class));

        mockMvc.perform(put("/announcement/update/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"data\":\"Updated data\"}"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("Shared data updated successfully!"));

        verify(sharedDataService)
                .updateSharedData(eq(1), any(SharedDataModel.class));
    }
    /**
     * Test the DeleteSharedDataById Method.
     */
    @Test
    public void testDeleteSharedDataById() throws Exception {
        doNothing().when(sharedDataService).deleteSharedDataById(anyInt());

        mockMvc.perform(delete("/announcement/delete/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("Shared data deleted successfully!"));

        verify(sharedDataService).deleteSharedDataById(1);
    }
    /**
     * Test the Index Method.
     */
    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/announcement/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("announcement/announcement"));
    }
}
