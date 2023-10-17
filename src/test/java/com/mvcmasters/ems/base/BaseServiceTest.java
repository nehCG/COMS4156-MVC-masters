package com.mvcmasters.ems.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BaseServiceTest {

    private BaseService<TestEntity, Long> baseService;
    private BaseMapper<TestEntity, Long> baseMapperMock;
    private BaseQuery baseQueryMock;

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

    @Test
    public void testSelectByParams() throws DataAccessException {
        List<TestEntity> mockEntities = Arrays.asList(new TestEntity(), new TestEntity());
        when(baseMapperMock.selectByParams(baseQueryMock)).thenReturn(mockEntities);

        List<TestEntity> entities = baseService.selectByParams(baseQueryMock);

        assertEquals(mockEntities, entities);
        verify(baseMapperMock).selectByParams(baseQueryMock);
    }

    @Test
    public void testQueryByParamsForTable() {
        List<TestEntity> mockEntities = Arrays.asList(new TestEntity(), new TestEntity());
        when(baseMapperMock.selectByParams(baseQueryMock)).thenReturn(mockEntities);

        Map<String, Object> result = baseService.queryByParamsForTable(baseQueryMock);

        assertEquals(2, ((List<?>) result.get("data")).size());
        assertEquals(0, result.get("code"));
        assertEquals("", result.get("msg"));
        verify(baseMapperMock).selectByParams(baseQueryMock);
    }

    private void setBaseMapperUsingReflection(BaseService<TestEntity, Long> service, BaseMapper<TestEntity, Long> mapper) throws Exception {
        Field field = BaseService.class.getDeclaredField("baseMapper");
        field.setAccessible(true);
        field.set(service, mapper);
    }

    private static class TestEntity {
    }
}
