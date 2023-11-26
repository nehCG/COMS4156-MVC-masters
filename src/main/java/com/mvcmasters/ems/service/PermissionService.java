package com.mvcmasters.ems.service;

import com.mvcmasters.ems.base.BaseService;
import com.mvcmasters.ems.repository.PermissionMapper;
import com.mvcmasters.ems.vo.Permission;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing permissions.
 * Extends BaseService to provide basic service
 * operations for the Permission entity.
 */
@Service
public class PermissionService extends BaseService<Permission, Integer> {

    /**
     * Injected PermissionMapper for accessing database
     * operations related to permissions.
     * Used to perform CRUD operations and other queries on permission entities.
     */
    @Resource
    private PermissionMapper permissionMapper;

    /**
     * Retrieves a list of permissions that a user has through their roles.
     * @param userId The ID of the user whose permissions are to be queried.
     * @return A list of permission strings representing
     *         the permissions the user has through their roles.
     */
    public List<String>
    queryUserHasRoleHasPermissionByUserId(final Integer userId) {
        return permissionMapper.queryUserHasRoleHasPermissionByUserId(userId);
    }
}
