package com.mvcmasters.ems.base;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface BaseMapper<T,ID> {
    public List<T> selectByParams(BaseQuery baseQuery) throws DataAccessException;

}
