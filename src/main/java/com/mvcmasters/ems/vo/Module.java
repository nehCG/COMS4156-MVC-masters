package com.mvcmasters.ems.vo;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.Date;

/**
 * Represents a module entity in the system, encapsulating various
 * properties like name, style, URL, and parent information.
 */
public class Module {
    /**
     * The unique identifier of the module.
     */
    private Integer id;

    /**
     * The name of the module.
     */
    private String moduleName;

    /**
     * The CSS style associated with the module, used for UI representation.
     */
    private String moduleStyle;

    /**
     * The URL linked with the module,
     * typically pointing to its location in the application.
     */
    private String url;

    /**
     * The ID of the parent module, establishing a hierarchical relationship.
     */
    private Integer parentId;

    /**
     * A value representing some specific attribute
     * or permission of the parent module.
     */
    private String parentOptValue;

    /**
     * The hierarchical level or grade of the module,
     * used for sorting or structuring.
     */
    private Integer grade;

    /**
     * An option value, often used for permissions
     * or specific module attributes.
     */
    private String optValue;

    /**
     * The order in which the module appears
     * relative to other modules, used for sorting.
     */
    private Integer orders;

    /**
     * A flag indicating whether the module is active (valid) or not.
     */
    private Byte isValid;

    /**
     * The date and time when the module was created.
     */
    private Date createDate;

    /**
     * The date and time when the module was last updated.
     */
    private Date updateDate;

    /**
     * Gets the module's unique identifier.
     *
     * @return The ID of the module.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the module's unique identifier.
     *
     * @param iD The ID to be set for the module.
     */
    public void setId(final Integer iD) {
        this.id = iD;
    }

    /**
     * Gets the name of the module.
     *
     * @return The name of the module.
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Sets the name of the module.
     *
     * @param mName The name to set for the module.
     */
    public void setModuleName(final String mName) {
        this.moduleName = mName == null ? null : mName.trim();
    }

    /**
     * Gets the style associated with the module.
     *
     * @return The style of the module.
     */
    public String getModuleStyle() {
        return moduleStyle;
    }

    /**
     * Sets the style for the module.
     *
     * @param mStyle The style to set for the module.
     */
    public void setModuleStyle(final String mStyle) {
        this.moduleStyle = mStyle == null ? null : mStyle.trim();
    }

    /**
     * Gets the URL of the module.
     *
     * @return The URL of the module.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL of the module.
     *
     * @param purl The URL to set for the module.
     */
    public void setUrl(final String purl) {
        this.url = purl == null ? null : purl.trim();
    }

    /**
     * Gets the parent ID of the module, indicating its hierarchical placement.
     *
     * @return The parent ID of the module.
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * Sets the parent ID of the module.
     *
     * @param pId The parent ID to set for the module.
     */
    public void setParentId(final Integer pId) {
        this.parentId = pId;
    }

    /**
     * Gets the parent module's option value,
     * representing some specific attribute or permission.
     *
     * @return The parent module's option value.
     */
    public String getParentOptValue() {
        return parentOptValue;
    }

    /**
     * Sets the parent module's option value.
     *
     * @param pOptValue The parent module's option value to set.
     */
    public void setParentOptValue(final String pOptValue) {
        this.parentOptValue =
                pOptValue == null ? null : pOptValue.trim();
    }

    /**
     * Gets the hierarchical grade or level of the module.
     *
     * @return The grade or level of the module.
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * Sets the hierarchical grade or level of the module.
     *
     * @param pgrade The grade or level to set for the module.
     */
    public void setGrade(final Integer pgrade) {
        this.grade = pgrade;
    }

    /**
     * Gets the option value of the module,
     * typically representing specific attributes or permissions.
     *
     * @return The option value of the module.
     */
    public String getOptValue() {
        return optValue;
    }

    /**
     * Sets the option value of the module.
     *
     * @param oValue The option value to set for the module.
     */
    public void setOptValue(final String oValue) {
        this.optValue = oValue == null ? null : oValue.trim();
    }

    /**
     * Gets the ordering value for the module,
     * used for sorting or display purposes.
     *
     * @return The order value of the module.
     */
    public Integer getOrders() {
        return orders;
    }

    /**
     * Sets the ordering value for the module.
     *
     * @param order The order value to set for the module.
     */
    public void setOrders(final Integer order) {
        this.orders = order;
    }

    /**
     * Gets the validity status of the module.
     *
     * @return The validity status as a Byte.
     */
    public Byte getIsValid() {
        return isValid;
    }

    /**
     * Sets the validity status of the module.
     * This status is used to determine if the module is active or inactive.
     *
     * @param valid The validity status to set for the module.
     */
    public void setIsValid(final Byte valid) {
        this.isValid = valid;
    }

    /**
     * Gets the creation date of the module.
     * This date indicates when the module was first created in the system.
     *
     * @return The creation date of the module.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP",
            justification = "Acceptable risk")
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets the creation date of the module.
     * This date should represent when the module
     * was first created in the system.
     *
     * @param cDate The creation date to set for the module.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2",
            justification = "Acceptable risk")
    public void setCreateDate(final Date cDate) {
        this.createDate = cDate;
    }

    /**
     * Gets the last update date of the module.
     * This date indicates the most recent time the module was updated.
     *
     * @return The last update date of the module.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP",
            justification = "Acceptable risk")
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * Sets the last update date of the module.
     * This date should represent the most recent time the module was updated.
     *
     * @param uDate The update date to set for the module.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2",
            justification = "Acceptable risk")
    public void setUpdateDate(final Date uDate) {
        this.updateDate = uDate;
    }
}
