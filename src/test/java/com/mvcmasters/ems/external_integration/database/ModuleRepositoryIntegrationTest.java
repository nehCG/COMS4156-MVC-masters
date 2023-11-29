package com.mvcmasters.ems.external_integration.database;

import com.mvcmasters.ems.model.TreeModel;
import com.mvcmasters.ems.repository.ModuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

/**
 * External Integration Test of ModuleMapper
 * with MySQL database.
 */
@SpringBootTest
public class ModuleRepositoryIntegrationTest {
    /**
     * Test for moduleMapper.
     */
    @Autowired
    private ModuleMapper moduleMapper;
    /**
     * Test for QueryAllModules method.
     */
    @Test
    public void testQueryAllModules() {
        List<TreeModel> modules = moduleMapper.queryAllModules();
        assertThat(modules).isNotNull();
        assertThat(modules).isNotEmpty();
    }
}
