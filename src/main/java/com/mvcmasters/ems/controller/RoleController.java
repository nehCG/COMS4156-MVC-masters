package com.mvcmasters.ems.controller;

import com.mvcmasters.ems.base.BaseController;
import com.mvcmasters.ems.base.ResultInfo;
import com.mvcmasters.ems.query.RoleQuery;
import com.mvcmasters.ems.service.RoleService;
import com.mvcmasters.ems.vo.Role;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Controller class for role management in the EMS application.
 * This class handles HTTP requests related to role operations.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    /**
     * Service for handling role-related operations.
     */
    @Resource
    private RoleService roleService;

    /**
     * Queries and returns all roles associated with a given user ID.
     *
     * @param userId The ID of the user for whom to query roles.
     * @return A list of maps, each representing a role and its attributes.
     */
    @RequestMapping("/queryAllRoles")
    @ResponseBody
    public List<Map<String, Object>> queryAllRoles(final Integer userId) {
        return roleService.queryAllRoles(userId);
    }

    /**
     * Selects and returns roles based on specified query parameters.
     *
     * @param roleQuery The query parameters for selecting roles.
     * @return A map containing role data matching the query parameters.
     */
    @GetMapping("/list")
    @ResponseBody
    public Map<String, Object> selectByParams(final RoleQuery roleQuery) {
        return roleService.queryByParamsForTable(roleQuery);
    }

    /**
     * Directs to the role index page.
     *
     * @return A string representing the path to the role index view.
     */
    @RequestMapping("/index")
    public String index() {
        return "role/role";
    }

    /**
     * Adds a new role to the system.
     *
     * @param role The role object to be added.
     * @return A result info object indicating the success of the operation.
     */
    @PostMapping("/add")
    @ResponseBody
    public ResultInfo addRole(final Role role) {
        roleService.addRole(role);
        return success("Role added successfully!");
    }

    /**
     * Directs to a page for adding or updating a role.
     *
     * @param roleId  The ID of the role to edit, or null for adding a new role.
     * @param request The HttpServletRequest object.
     * @return A string representing the path to the add/update role view.
     */
    @RequestMapping("toAddOrUpdateRolePage")
    public String toAddOrUpdateRolePage(final Integer roleId,
                                        final HttpServletRequest request) {
        if (roleId != null) {
            Role role = roleService.selectByPrimaryKey(roleId);
            request.setAttribute("role", role);
        }
        return "role/add_update";
    }

    /**
     * Updates an existing role in the system.
     *
     * @param role The role object containing updated data.
     * @return A result info object indicating the
     * success of the update operation.
     */
    @PostMapping("/update")
    @ResponseBody
    public ResultInfo updateRole(final Role role) {
        roleService.updateRole(role);
        return success("Role edited successfully!");
    }

    /**
     * Deletes a role from the system.
     *
     * @param roleId The ID of the role to be deleted.
     * @return A result info object indicating the success of the deletion.
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteRole(final Integer roleId) {
        roleService.deleteRole(roleId);
        return success("Role deleted successfully!");
    }
}
