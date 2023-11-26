package com.mvcmasters.ems.repository;

import com.mvcmasters.ems.base.BaseMapper;
import com.mvcmasters.ems.vo.Permission;

import java.util.List;

/**
 * Mapper interface for Permission entities.
 * This interface extends BaseMapper and provides
 * additional methods specific to the Permission entity.
 */
public interface PermissionMapper extends BaseMapper<Permission, Integer> {
    /**
     * Counts the number of permissions associated with a given role ID.
     *
     * @param roleId The ID of the role.
     * @return The number of permissions associated with the specified role.
     */
    Integer countPermissionByRoleId(Integer roleId);

    /**
     * Deletes all permissions associated with a given role ID.
     *
     * @param roleId The ID of the role for which permissions should be deleted.
     */
    void deletePermissionByRoleId(Integer roleId);


    /**
     * Retrieves a list of module IDs that a role has permissions for.
     *
     * @param roleId The ID of the role.
     * @return A list of module IDs that the specified role has access to.
     */
    List<Integer> queryRoleHasModuleIdsByRoleId(Integer roleId);


    /**
     * Retrieves a list of permission strings that
     * a user has through their roles.
     * This typically involves a join operation over user,
     * role, and permission tables.
     *
     * @param userId The ID of the user.
     * @return A list of permission strings that the user
     * has access to through their roles.
     */
    List<String> queryUserHasRoleHasPermissionByUserId(Integer userId);
}
