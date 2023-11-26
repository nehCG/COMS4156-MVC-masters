package com.mvcmasters.ems.repository;

import com.mvcmasters.ems.base.BaseMapper;
import com.mvcmasters.ems.vo.Role;

import java.util.List;
import java.util.Map;

/**
 * RoleMapper interface for performing database
 * operations related to 'Role' entities.
 * Extends {@link BaseMapper} to provide basic CRUD operations.
 */
public interface RoleMapper extends BaseMapper<Role, Integer> {

    /**
     * Queries and retrieves all roles associated with a given user ID.
     *
     * @param userId The ID of the user whose roles are to be queried.
     * @return A list of maps, each map representing a role with its attributes.
     */
    List<Map<String, Object>> queryAllRoles(Integer userId);

    /**
     * Selects a role based on the given role name.
     *
     * @param roleName The name of the role to be selected.
     * @return The {@link Role} object corresponding to the given role name.
     */
    Role selectByRoleName(String roleName);
}
