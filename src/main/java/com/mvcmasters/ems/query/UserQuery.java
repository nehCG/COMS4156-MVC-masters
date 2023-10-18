package com.mvcmasters.ems.query;

import com.mvcmasters.ems.base.BaseQuery;

/**
 * Represents the criteria used to query for users.
 */
public class UserQuery extends BaseQuery {

    /**
     * The user's login or display name used for querying.
     */
    private String userName;

    /**
     * The user's email used for querying.
     */
    private String email;

    /**
     * The user's phone number used for querying.
     */
    private String phone;

    /**
     * Retrieves the user's login or display name.
     *
     * @return The user's login or display name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user's login or display name.
     *
     * @param puserName The user's login or display name to be set.
     */
    public void setUserName(final String puserName) {
        this.userName = puserName;
    }

    /**
     * Retrieves the user's email.
     *
     * @return The user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     *
     * @param pemail The user's email to be set.
     */
    public void setEmail(final String pemail) {
        this.email = pemail;
    }

    /**
     * Retrieves the user's phone number.
     *
     * @return The user's phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the user's phone number.
     *
     * @param pphone The user's phone number to be set.
     */
    public void setPhone(final String pphone) {
        this.phone = pphone;
    }
}
