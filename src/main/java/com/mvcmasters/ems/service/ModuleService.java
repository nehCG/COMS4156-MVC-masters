package com.mvcmasters.ems.service;

import com.mvcmasters.ems.base.BaseService;
import com.mvcmasters.ems.model.TreeModel;
import com.mvcmasters.ems.repository.ModuleMapper;
import com.mvcmasters.ems.repository.PermissionMapper;
import com.mvcmasters.ems.vo.Module;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing module operations.
 * Extends BaseService to provide basic
 * service operations for the Module entity.
 */
@Service
public class ModuleService extends BaseService<Module, Integer> {

    /**
     * Injected ModuleMapper for accessing db operations related to modules.
     * Used to perform CRUD operations and other queries on module entities.
     */
    @Resource
    private ModuleMapper moduleMapper;

    /**
     * Injected PermissionMapper for accessing database
     * operations related to permissions.
     * Used to perform CRUD operations and other queries on permission entities.
     */
    @Resource
    private PermissionMapper permissionMapper;

    /**
     * Retrieves all modules and marks them as checked if they are associated
     * with the given role ID.
     * @param roleId The role ID to check module permissions against.
     * @return A list of TreeModel objects representing modules,
     *         with 'checked' status updated.
     */
    public List<TreeModel> queryAllModules(final Integer roleId) {
        // Retrieve all modules from the database.
        List<TreeModel> treeModelList = moduleMapper.queryAllModules();
        // Retrieve the list of module IDs that the role has access to.
        List<Integer> permissionIds =
                permissionMapper.queryRoleHasModuleIdsByRoleId(roleId);
        // Check if the role has any permissions.
        if (permissionIds != null && permissionIds.size() > 0) {
            // Iterate through each TreeModel (module) and
            // mark it as checked if the role has permission for it.
            treeModelList.forEach(treeModel -> {
                if (permissionIds.contains(treeModel.getId())) {
                    // Mark the module as checked/selected.
                    treeModel.setChecked(true);
                }
            });
        }
        // Return the list of modules with their checked status.
        return treeModelList;
    }
}
