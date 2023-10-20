package com.mvcmasters.ems.base;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * BaseMapper is a generic interface for basic CRUD operations.
 *
 * @param <T>  the type of the entity
 * @param <ID> the type of the entity's primary key
 */
@Mapper
public interface BaseMapper<T, ID> {

    /**
     * Selects entities based on given parameters.
     *
     * @param baseQuery the parameters for the query
     * @return a list of entities that match the query parameters
     * @throws DataAccessException if a data access error occurs
     */
    List<T> selectByParams(BaseQuery baseQuery) throws DataAccessException;

    /**
     * Selects an entity by its primary key.
     *
     * @param id the primary key of the entity to be selected
     * @return the entity with the specified primary key
     * @throws DataAccessException if a data access error occurs
     */
    T selectByPrimaryKey(ID id) throws DataAccessException;

    /**
     * Updates the specified entity by its primary key selectively.
     *
     * @param entity the entity to be updated
     * @return the number of rows affected by the update
     * @throws DataAccessException if a data access error occurs
     */
    Integer updateByPrimaryKeySelective(T entity) throws DataAccessException;

    /**
     * Inserts the specified entity selectively.
     *
     * @param entity the entity to be inserted
     * @return the number of rows affected by the insert
     * @throws DataAccessException if a data access error occurs
     */
    Integer insertSelective(T entity) throws DataAccessException;

    /**
     * Deletes entities with the given primary keys in a batch.
     *
     * @param ids the primary keys of the entities to be deleted
     * @return the number of rows affected by delete
     * @throws DataAccessException if a data access error occurs
     */
    Integer deleteBatch(ID[] ids) throws DataAccessException;
}
