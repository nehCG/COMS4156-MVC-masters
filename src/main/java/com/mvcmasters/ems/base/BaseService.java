package com.mvcmasters.ems.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BaseService provides foundational CRUD operations for a given type.
 *
 * @param <T>  the type of the entity
 * @param <ID> the type of the ID of the entity
 */
public abstract class BaseService<T, ID> {

    /**
     * The base mapper used for CRUD operations.
     */
    @Autowired
    private BaseMapper<T, ID> baseMapper;

    /**
     * Select entities based on given parameters.
     *
     * @param baseQuery the query parameters
     * @return list of entities matching the criteria
     * @throws DataAccessException in case of data access issues
     */
    public List<T> selectByParams(final BaseQuery baseQuery)
            throws DataAccessException {
        return baseMapper.selectByParams(baseQuery);
    }

    /**
     * Query entities based on given parameters for table representation.
     *
     * @param baseQuery the query parameters
     * @return a map representation of the query results for table display
     */
    public Map<String, Object> queryByParamsForTable(final
                                                     BaseQuery baseQuery) {
        Map<String, Object> result = new HashMap<>();
        PageHelper.startPage(baseQuery.getPage(), baseQuery.getLimit());
        PageInfo<T> pageInfo = new PageInfo<>(selectByParams(baseQuery));
        result.put("count", pageInfo.getTotal());
        result.put("data", pageInfo.getList());
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }
}
