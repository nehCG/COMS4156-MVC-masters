package com.mvcmasters.ems.vo;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.Date;

/**
 * Represents a permission entity in the system, linking roles
 * to specific modules and their access control levels.
 */
public class Permission {
    /**
     * The unique identifier of the permission.
     */
    private Integer id;

    /**
     * The ID of the role associated with this permission.
     */
    private Integer roleId;

    /**
     * The ID of the module associated with this permission.
     */
    private Integer moduleId;

    /**
     * The access control value representing the level or type of permission.
     */
    private String aclValue;

    /**
     * The date and time when the permission was created.
     */
    private Date createDate;

    /**
     * The date and time when the permission was last updated.
     */
    private Date updateDate;

    /**
     * Gets the unique identifier of the permission.
     *
     * @return The ID of the permission.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the permission.
     *
     * @param iD The ID to be set for the permission.
     */
    public void setId(final Integer iD) {
        this.id = iD;
    }

    /**
     * Gets the role ID associated with this permission.
     *
     * @return The ID of the associated role.
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * Sets the role ID for this permission.
     *
     * @param rId The ID of the role to be associated with this permission.
     */
    public void setRoleId(final Integer rId) {
        this.roleId = rId;
    }

    /**
     * Gets the module ID associated with this permission.
     *
     * @return The ID of the associated module.
     */
    public Integer getModuleId() {
        return moduleId;
    }

    /**
     * Sets the module ID for this permission.
     *
     * @param mId The ID of the module to
     *                be associated with this permission.
     */
    public void setModuleId(final Integer mId) {
        this.moduleId = mId;
    }

    /**
     * Gets the access control value (ACL) of this permission.
     *
     * @return The ACL value of the permission.
     */
    public String getAclValue() {
        return aclValue;
    }

    /**
     * Sets the access control value (ACL) for this permission.
     *
     * @param aValue The ACL value to be set for this permission.
     */
    public void setAclValue(final String aValue) {
        this.aclValue = aValue == null ? null : aValue.trim();
    }

    /**
     * Gets the creation date of this permission.
     *
     * @return The creation date of the permission.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP",
            justification = "Acceptable risk")
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets the creation date of this permission.
     *
     * @param cDate The creation date to be set for the permission.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2",
            justification = "Acceptable risk")
    public void setCreateDate(final Date cDate) {
        this.createDate = cDate;
    }

    /**
     * Gets the last update date of this permission.
     *
     * @return The last update date of the permission.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP",
            justification = "Acceptable risk")
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * Sets the last update date for this permission.
     *
     * @param uDate The last update date to be set for the permission.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2",
            justification = "Acceptable risk")
    public void setUpdateDate(final Date uDate) {
        this.updateDate = uDate;
    }
}
