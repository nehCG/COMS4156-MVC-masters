package com.mvcmasters.ems.repository;

import com.mvcmasters.ems.base.BaseMapper;
import com.mvcmasters.ems.vo.User;

/**
 * Mapper interface for User related database operations.
 */
public interface UserMapper extends BaseMapper<User, Integer> {

    /**
     * Queries the database to retrieve a User based on their username.
     *
     * @param userName the name of the user to query for.
     * @return the User corresponding to the provided username, or null if not found.
     */
    User queryUserByName(String userName);
}
