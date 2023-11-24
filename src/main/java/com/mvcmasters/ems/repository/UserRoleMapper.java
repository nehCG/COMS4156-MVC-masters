package com.mvcmasters.ems.repository;

import com.mvcmasters.ems.base.BaseMapper;
import com.mvcmasters.ems.vo.UserRole;

/**
 * UserRoleMapper interface for performing database
 * operations related to 'UserRole' entities.
 * Extends {@link BaseMapper} to provide basic
 * CRUD operations for UserRole entities.
 */
public interface UserRoleMapper extends BaseMapper<UserRole, Integer> {

    /**
     * Counts the number of user roles associated with a given user ID.
     *
     * @param userId The ID of the user for
     *               whom the count of user roles is to be determined.
     * @return The count of user roles associated with the specified user ID.
     */
    Integer countUserRoleByUserId(Integer userId);

    /**
     * Deletes all user roles associated with a given user ID.
     *
     * @param userId The ID of the user whose user roles are to be deleted.
     * @return The number of user roles that were deleted.
     */
    Integer deleteUserRoleByUserId(Integer userId);
}
