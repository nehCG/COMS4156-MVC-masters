package com.mvcmasters.ems.model;

/**
 * Represents the user model with details like userId, userName, and trueName.
 */
public class UserModel {
    /**
     * The unique identifier for the user.
     */
    private Integer userId;

    /**
     * The user's login or display name.
     */
    private String userName;

    /**
     * The user's real or official name.
     */
    private String trueName;

    /**
     * Retrieves the user ID.
     *
     * @return the user ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param id the user ID to set
     */
    public void setUserId(final Integer id) {
        this.userId = id;
    }

    /**
     * Retrieves the username.
     *
     * @return the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username.
     *
     * @param name the username to set
     */
    public void setUserName(final String name) {
        this.userName = name;
    }

    /**
     * Retrieves the true name of the user.
     *
     * @return the true name
     */
    public String getTrueName() {
        return trueName;
    }

    /**
     * Sets the true name of the user.
     *
     * @param realName the true name to set
     */
    public void setTrueName(final String realName) {
        this.trueName = realName;
    }
}
