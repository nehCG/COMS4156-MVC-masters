package com.mvcmasters.ems.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Represents a user.
 */
public class User {
    /**
     * The user's ID.
     */
    private Integer id;

    /**
     * The user's username.
     */
    private String userName;

    /**
     * The user's password.
     */
    private String userPwd;

    /**
     * The user's true name.
     */
    private String trueName;

    /**
     * The user's email address.
     */
    private String email;

    /**
     * The user's phone number.
     */
    private String phone;

    /**
     * The user's validity status.
     */
    private Integer isValid;

    /**
     * The date and time when the user was created.
     * Formatted as 'yyyy-MM-dd HH:mm:ss' and adjusted to 'GMT-5' timezone.
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-5")
    private Date createDate;

    /**
     * The date and time when the user was last updated.
     * Formatted as 'yyyy-MM-dd HH:mm:ss' and adjusted to 'GMT-5' timezone.
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-5")
    private Date updateDate;

    /**
     * The user's roleIds.
     */
    private String roleIds;
    /**
     * Gets the user's ID.
     *
     * @return The user's ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the user's ID.
     *
     * @param newId The user's ID to set.
     */
    public void setId(final Integer newId) {
        this.id = newId;
    }

    /**
     * Gets the user's username.
     *
     * @return The user's username.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user's username.
     *
     * @param newUserName The user's username to set.
     */
    public void setUserName(final String newUserName) {
        this.userName = newUserName == null ? null : newUserName.trim();
    }

    /**
     * Gets the user's password.
     *
     * @return The user's password.
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * Sets the user's password.
     *
     * @param newUserPwd The user's password to set.
     */
    public void setUserPwd(final String newUserPwd) {
        this.userPwd = newUserPwd == null ? null : newUserPwd.trim();
    }

    /**
     * Gets the user's true name.
     *
     * @return The user's true name.
     */
    public String getTrueName() {
        return trueName;
    }

    /**
     * Sets the user's true name.
     *
     * @param newTrueName The user's true name to set.
     */
    public void setTrueName(final String newTrueName) {
        this.trueName = newTrueName == null ? null : newTrueName.trim();
    }

    /**
     * Gets the user's email address.
     *
     * @return The user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param newEmail The user's email address to set.
     */
    public void setEmail(final String newEmail) {
        this.email = newEmail == null ? null : newEmail.trim();
    }

    /**
     * Gets the user's phone number.
     *
     * @return The user's phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the user's phone number.
     *
     * @param newPhone The user's phone number to set.
     */
    public void setPhone(final String newPhone) {
        this.phone = newPhone == null ? null : newPhone.trim();
    }

    /**
     * Get the user's validity status.
     *
     * @return The user's validity status.
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * Set the user's validity status.
     *
     * @param newIsValid The user's validity status to set.
     */
    public void setIsValid(final Integer newIsValid) {
        this.isValid = newIsValid;
    }

    /**
     * Gets the creation date of the role.
     *
     * @return The creation date of the role.
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets the creation date of the role.
     *
     * @param cDate The creation date to set for the role.
     */
    public void setCreateDate(final Date cDate) {
        this.createDate = cDate;
    }

    /**
     * Gets the last update date of the role.
     *
     * @return The last update date of the role.
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * Sets the last update date for the role.
     *
     * @param uDate The update date to set for the role.
     */
    public void setUpdateDate(final Date uDate) {
        this.updateDate = uDate;
    }

    /**
     * Get the user's role id.
     * @return The user's roleIds.
     */
    public String getRoleIds() {
        return roleIds;
    }

    /**
     * Set the user's role id.
     * @param rIds The user's role id.
     */
    public void setRoleIds(final String rIds) {
        this.roleIds = rIds;
    }
}
