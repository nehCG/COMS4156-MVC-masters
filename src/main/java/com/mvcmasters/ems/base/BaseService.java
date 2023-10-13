package com.mvcmasters.ems.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseService<T,ID> {

    @Autowired
    private BaseMapper<T,ID> baseMapper;

    public List<T> selectByParams(BaseQuery baseQuery) throws DataAccessException{
        return baseMapper.selectByParams(baseQuery);
    }

    public Map<String, Object> queryByParamsForTable(BaseQuery baseQuery) {
        Map<String, Object> result = new HashMap<String, Object>();
        PageHelper.startPage(baseQuery.getPage(), baseQuery.getLimit());
        PageInfo<T> pageInfo = new PageInfo<T>(selectByParams(baseQuery));
        result.put("count", pageInfo.getTotal());
        result.put("data", pageInfo.getList());
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }
}
