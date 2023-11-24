package com.mvcmasters.ems.query;

import com.mvcmasters.ems.base.BaseQuery;

/**
 * Represents a query for searching or filtering roles in the EMS application.
 * Extends {@link BaseQuery} to include standard query capabilities.
 */
public class RoleQuery extends BaseQuery {
    /**
     * The name of the role being queried.
     * This field is used to filter or search
     * for specific roles based on their names.
     */
    private String roleName;

    /**
     * Retrieves the name of the role.
     *
     * @return The current name of the role being queried.
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the name of the role to be queried.
     *
     * @param name The name of the role to set for this query.
     */
    public void setRoleName(final String name) {
        this.roleName = name;
    }
}
