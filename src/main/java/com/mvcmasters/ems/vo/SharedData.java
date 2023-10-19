package com.mvcmasters.ems.vo;

/**
 * Represents a shared data.
 */
public class SharedData {
    /**
     * The shared data's ID.
     */
    private Integer id;
    /**
     * The shared data's creator's uid.
     */
    private Integer uid;
    /**
     * The uid of the user who last modified this shared data.
     */
    private Integer lastModifiedBy;
    /**
     * The shared data subject.
     */
    private String subject;
    /**
     * The shared data content.
     */
    private String content;
    /**
     * The creation date of the shared data.
     */
    private String createdTime;
    /**
     * The last modification date of the shared data.
     */
    private String modifiedTime;
    /**
     * Gets the data's ID.
     *
     * @return The data's ID.
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
     * Gets the user's ID.
     *
     * @return The user's ID.
     */
    public Integer getUid() {
        return uid;
    }
    /**
     * Sets the user's ID.
     *
     * @param newUid The user's ID to set.
     */
    public void setUid(final Integer newUid) {
        this.uid = newUid;
    }
    /**
     * Sets the data new subjects.
     *
     * @param newSubject the data new subjects.
     */
    public void setSubject(final String newSubject) {
        this.subject = newSubject == null ? null : newSubject.trim();
    }
    /**
     * Gets the subject.
     *
     * @return data subject.
     */
    public String getSubject() {
        return subject;
    }
    /**
     * Sets the new data content.
     *
     * @param newContent new data content.
     */
    public void setContent(final String newContent) {
        this.content = newContent == null ? null : newContent.trim();
    }
    /**
     * Gets the content.
     *
     * @return data content.
     */
    public String getContent() {
        return content;
    }
    /**
     * Sets the last modified user's ID.
     *
     * @param newUid the last modified user ID.
     */
    public void setLastModifiedBy(final Integer newUid) {
        this.lastModifiedBy = newUid;
    }
    /**
     * Gets the last modified user id.
     *
     * @return the last modified user id.
     */
    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }
    /**
     * Sets the creation date.
     *
     * @param time the creation date.
     */
    public void setCreatedTime(final String time) {
        this.createdTime = time == null ? null : time.trim();
    }
    /**
     * Gets the creation data.
     *
     * @return the creation dat.
     */
    public String getCreatedTime() {
        return createdTime;
    }
    /**
     * Sets the modification date.
     *
     * @param time the modification date.
     */
    public void setLastModifiedTime(final String time) {
        this.modifiedTime = time == null ? null : time.trim();
    }
    /**
     * Gets the user's ID.
     *
     * @return The user's ID.
     */
    public String getLastModifiedTime() {
        return modifiedTime;
    }
}
