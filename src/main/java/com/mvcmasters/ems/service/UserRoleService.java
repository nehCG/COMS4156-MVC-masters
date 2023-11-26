package com.mvcmasters.ems.service;

import com.mvcmasters.ems.base.BaseService;
import com.mvcmasters.ems.repository.UserRoleMapper;
import com.mvcmasters.ems.vo.UserRole;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Service class for managing user roles in the EMS application.
 * This class extends {@link BaseService} to
 * provide general service layer functionalities
 * for {@link UserRole} entities.
 */
@Service
public class UserRoleService extends BaseService<UserRole, Integer> {

    /**
     * UserRoleMapper for accessing database operations related to user roles.
     * This field is used to perform CRUD operations and
     * other queries for user role entities.
     */
    @Resource
    private UserRoleMapper userRoleMapper;
}
