package com.mvcmasters.ems.service;

import com.mvcmasters.ems.base.BaseService;
import com.mvcmasters.ems.repository.RoleMapper;
import com.mvcmasters.ems.utils.AssertUtil;
import com.mvcmasters.ems.vo.Role;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Service class for managing roles in the EMS application.
 * Provides methods for querying, adding, updating, and deleting roles.
 */
@Service
public class RoleService extends BaseService<Role, Integer> {
    /**
     * Injected RoleMapper for accessing database operations related to roles.
     * Used to perform CRUD operations and other queries on role entities.
     */
    @Resource
    private RoleMapper roleMapper;

    /**
     * Queries and retrieves all roles associated with a given user ID.
     *
     * @param userId The ID of the user whose roles are to be queried.
     * @return A list of maps, each representing a role and its attributes.
     */
    public List<Map<String, Object>> queryAllRoles(final Integer userId) {
        // Query roles based on user ID
        return roleMapper.queryAllRoles(userId);
    }
    /**
     * Adds a new role to the system, ensuring it is unique and valid.
     *
     * @param role The role object to be added.
     * @throws IllegalArgumentException if the role name is
     * blank or already exists.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRole(final Role role) {
        // Validate role name is not blank
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()),
                "Role name cannot be empty!");
        // Check if role name already exists
        Role temp = roleMapper.selectByRoleName(role.getRoleName());
        AssertUtil.isTrue(temp != null,
                "Role name already exist, please try another one.");
        // Set validity and dates for the new role
        role.setIsValid(1);
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());
        // Insert the new role and check for success
        AssertUtil.isTrue(roleMapper.insertSelective(role) < 1,
                "Failed to add a new role!");
    }
    /**
     * Updates an existing role, ensuring the updated role is
     * valid and does not conflict with existing roles.
     *
     * @param role The role object containing updated data.
     * @throws IllegalArgumentException if the role does not exist
     * or the name is invalid or already taken.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateRole(final Role role) {
        // Validate existing role ID
        AssertUtil.isTrue(null == role.getId(),
                "Role to be updated does not exist!");
        // Retrieve existing role to update
        Role temp = roleMapper.selectByPrimaryKey(role.getId());
        AssertUtil.isTrue(null == temp,
                "Role to be updated does not exist!");
        // Validate role name is not blank
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()),
                "Role name cannot be empty!");
        temp = roleMapper.selectByRoleName(role.getRoleName());
        // Check if the new role name conflicts with existing roles
        AssertUtil.isTrue(null != temp && (temp.getId().equals(role.getId())),
                "Role name already exists!");
        // Set the update date and update the role
        role.setUpdateDate(new Date());
        AssertUtil.isTrue(roleMapper.updateByPrimaryKeySelective(role) < 1,
                "Failed to edit a role!");
    }
    /**
     * Deletes a role by marking it as invalid.
     *
     * @param roleId The ID of the role to be deleted.
     * @throws IllegalArgumentException if the role to be
     * deleted does not exist.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRole(final Integer roleId) {
        // Validate existence of the role to be deleted
        AssertUtil.isTrue(null == roleId,
                "Records to be deleted do not exist!");
        // Retrieve role to be marked as invalid (deleted)
        Role role = roleMapper.selectByPrimaryKey(roleId);
        AssertUtil.isTrue(null == role, "Records to be deleted do not exist!");
        // Set role as invalid and update deletion date
        role.setIsValid(0);
        role.setUpdateDate(new Date());
        // Perform the update and validate deletion
        AssertUtil.isTrue(roleMapper.updateByPrimaryKeySelective(role) < 1,
                "Failed to delete a role");
    }
}
