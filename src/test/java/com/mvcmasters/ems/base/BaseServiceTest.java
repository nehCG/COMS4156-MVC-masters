package com.mvcmasters.ems.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BaseServiceTest {

    /**
     * The BaseService instance for testing.
     */
    private BaseService<TestEntity, Long> baseService;

    /**
     * A mock implementation of BaseMapper for testing.
     */
    private BaseMapper<TestEntity, Long> baseMapperMock;

    /**
     * A mock implementation of BaseQuery for testing.
     */
    private BaseQuery baseQueryMock;

    /**
     * Set up the test by initializing the BaseService
     * instance and its dependencies.
     *
     * @throws Exception if there is an error in the setup process.
     */
    @BeforeEach
    public void setUp() throws Exception {
        baseMapperMock = mock(BaseMapper.class);
        baseQueryMock = mock(BaseQuery.class);

        baseService = new BaseService<TestEntity, Long>() {
            private BaseMapper<TestEntity, Long> getBaseMapper() {
                return baseMapperMock;
            }
        };

        setBaseMapperUsingReflection(baseService, baseMapperMock);
    }

    /**
     * Test the selectByParams method.
     *
     * @throws DataAccessException if there is a data access exception.
     */
    @Test
    public void testSelectByParams() throws DataAccessException {
        List<TestEntity> mockEntities = Arrays.asList(new TestEntity(),
                                                      new TestEntity());
        when(baseMapperMock.selectByParams(baseQueryMock)).
                thenReturn(mockEntities);

        List<TestEntity> entities = baseService.selectByParams(baseQueryMock);

        assertEquals(mockEntities, entities);
        verify(baseMapperMock).selectByParams(baseQueryMock);
    }

    /**
     * Test the queryByParamsForTable method.
     */
    @Test
    public void testQueryByParamsForTable() {
        List<TestEntity> mockEntities = Arrays.asList(new TestEntity(),
                                                      new TestEntity());
        when(baseMapperMock.selectByParams(baseQueryMock)).
                thenReturn(mockEntities);

        Map<String, Object> result = baseService.
                queryByParamsForTable(baseQueryMock);

        assertEquals(2, ((List<?>) result.get("data")).size());
        assertEquals(0, result.get("code"));
        assertEquals("", result.get("msg"));
        verify(baseMapperMock).selectByParams(baseQueryMock);
    }

    private void setBaseMapperUsingReflection(
            final BaseService<TestEntity, Long> service,
            final BaseMapper<TestEntity, Long> mapper)
            throws Exception {
        Field field = BaseService.class.getDeclaredField("baseMapper");
        field.setAccessible(true);
        field.set(service, mapper);
    }

    /**
     * Test the selectByPrimaryKey method.
     *
     * @throws DataAccessException if there is a data access exception.
     */
    @Test
    public void testSelectByPrimaryKey() throws DataAccessException {
        Long testId = 1L; // Example primary key
        // Expected entity to be returned
        TestEntity expectedEntity = new TestEntity();

        // Configure the mock to return the expected entity
        // when selectByPrimaryKey is called with testId
        when(baseMapperMock.selectByPrimaryKey(testId)).
                thenReturn(expectedEntity);

        // Call the method under test
        TestEntity actualEntity = baseService.selectByPrimaryKey(testId);

        // Assert that the returned entity is the same as the expected entity
        assertEquals(expectedEntity, actualEntity);
    }

    /**
     * This inner class represents a test entity used for testing purposes.
     */
    private static final class TestEntity {
    }
}
