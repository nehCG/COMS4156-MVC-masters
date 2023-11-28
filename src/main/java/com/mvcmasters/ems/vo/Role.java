package com.mvcmasters.ems.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.Date;

/**
 * Represents a role entity in the EMS application.
 * This class is used to store and manage data related to roles.
 */
public class Role {
    /**
     * The unique identifier for the role.
     */
    private Integer id;

    /**
     * The name of the role.
     */
    private String roleName;

    /**
     * A remark or description for the role.
     */
    private String roleRemark;

    /**
     * The date and time when the role was created.
     * Formatted as 'yyyy-MM-dd HH:mm:ss' and adjusted to 'GMT-5' timezone.
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-5")
    private Date createDate;

    /**
     * The date and time when the role was last updated.
     * Formatted as 'yyyy-MM-dd HH:mm:ss' and adjusted to 'GMT-5' timezone.
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-5")
    private Date updateDate;

    /**
     * Indicates whether the role is valid (1 for valid, 0 for invalid).
     */
    private Integer isValid;

    /**
     * Gets the ID of the role.
     *
     * @return The ID of the role.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the role.
     *
     * @param iD The ID to set for the role.
     */
    public void setId(final Integer iD) {
        this.id = iD;
    }

    /**
     * Gets the name of the role.
     *
     * @return The name of the role.
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the name of the role. Trims the input string if not null.
     *
     * @param name The name to set for the role.
     */
    public void setRoleName(final String name) {
        this.roleName = name == null ? null : name.trim();
    }

    /**
     * Gets the remark or description of the role.
     *
     * @return The remark of the role.
     */
    public String getRoleRemark() {
        return roleRemark;
    }

    /**
     * Sets the remark or description for the role.
     * Trims the input string if not null.
     *
     * @param remark The remark to set for the role.
     */
    public void setRoleRemark(final String remark) {
        this.roleRemark = remark == null ? null : remark.trim();
    }

    /**
     * Gets the creation date of the role.
     *
     * @return The creation date of the role.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP",
            justification = "Acceptable risk")
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets the creation date of the role.
     *
     * @param cDate The creation date to set for the role.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2",
            justification = "Acceptable risk")
    public void setCreateDate(final Date cDate) {
        this.createDate = cDate;
    }

    /**
     * Gets the last update date of the role.
     *
     * @return The last update date of the role.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP",
            justification = "Acceptable risk")
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * Sets the last update date for the role.
     *
     * @param uDate The update date to set for the role.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2",
            justification = "Acceptable risk")
    public void setUpdateDate(final Date uDate) {
        this.updateDate = uDate;
    }

    /**
     * Gets the validity status of the role.
     *
     * @return The validity status of the role (1 for valid, 0 for invalid).
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * Sets the validity status of the role.
     *
     * @param iValid The validity status to set for
     *               the role (1 for valid, 0 for invalid).
     */
    public void setIsValid(final Integer iValid) {
        this.isValid = iValid;
    }
}
