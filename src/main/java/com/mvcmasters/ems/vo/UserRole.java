package com.mvcmasters.ems.vo;

import java.util.Date;

/**
 * Represents a user-role relationship in the EMS application.
 * This class stores and manages data related
 * to the assignment of roles to users.
 */
public class UserRole {
    /**
     * The unique identifier for the user-role relationship.
     */
    private Integer id;

    /**
     * The ID of the user in the user-role relationship.
     */
    private Integer userId;

    /**
     * The ID of the role in the user-role relationship.
     */
    private Integer roleId;

    /**
     * The date and time when the user-role relationship was created.
     */
    private Date createDate;

    /**
     * The date and time when the user-role relationship was last updated.
     */
    private Date updateDate;

    /**
     * Gets the ID of the user-role relationship.
     *
     * @return The ID of the user-role relationship.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the user-role relationship.
     *
     * @param iD The ID to set for the user-role relationship.
     */
    public void setId(final Integer iD) {
        this.id = iD;
    }

    /**
     * Gets the user ID in the user-role relationship.
     *
     * @return The user ID.
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the user ID for the user-role relationship.
     *
     * @param uId The user ID to set.
     */
    public void setUserId(final Integer uId) {
        this.userId = uId;
    }

    /**
     * Gets the role ID in the user-role relationship.
     *
     * @return The role ID.
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * Sets the role ID for the user-role relationship.
     *
     * @param rId The role ID to set.
     */
    public void setRoleId(final Integer rId) {
        this.roleId = rId;
    }

    /**
     * Gets the creation date of the user-role relationship.
     *
     * @return The creation date.
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets the creation date for the user-role relationship.
     *
     * @param cDate The creation date to set.
     */
    public void setCreateDate(final Date cDate) {
        this.createDate = cDate;
    }

    /**
     * Gets the last update date of the user-role relationship.
     *
     * @return The update date.
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * Sets the update date for the user-role relationship.
     *
     * @param uDate The update date to set.
     */
    public void setUpdateDate(final Date uDate) {
        this.updateDate = uDate;
    }
}
