package com.mvcmasters.ems.vo;

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
     * Gets the user's validity status.
     *
     * @return The user's validity status.
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * Sets the user's validity status.
     *
     * @param newIsValid The user's validity status to set.
     */
    public void setIsValid(final Integer newIsValid) {
        this.isValid = newIsValid;
    }
}
