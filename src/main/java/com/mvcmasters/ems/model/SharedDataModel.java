package com.mvcmasters.ems.model;
import java.time.LocalDateTime;

/**
 * Represents the shared data model with details like data id, user id
 * created date, data subject, data content, and modified info.
 */
public class SharedDataModel {
    /**
     * The unique identifier for each shared data.
     */
    private Integer id;
    /**
     * The user ID for the creator of the shared data.
     */
    private Integer uid;
    /**
     * The subject of the shared data.
     */
    private String subject;
    /**
     * The content of the shared data.
     */
    private String content;
    /**
     * The created time of the shared data.
     */
    private LocalDateTime createdTime;
    /**
     * The uid of the user who last modified the data.
     */
    private Integer lastModifiedBy;
    /**
     * The last modified time of the shared data.
     */
    private LocalDateTime modifiedTime;
    /**
     * Retrieves the data ID.
     *
     * @return the data ID
     */
    public Integer getId() {
        return id;
    }
    /**
     * Sets the data ID.
     *
     * @param newId the data ID to set
     */
    public void setId(final Integer newId) {
        this.id = newId;
    }
    /**
     * Retrieves the user ID.
     *
     * @return the user ID
     */
    public Integer getUid() {
        return uid;
    }
    /**
     * Sets the user ID.
     *
     * @param newUid the data ID to set
     */
    public void setUid(final Integer newUid) {
        this.uid = newUid;
    }
    /**
     * Sets the data subject.
     *
     * @param newSubject the data ID to set
     */
    public void setSubject(final String newSubject) {
        this.subject = newSubject == null ? null : newSubject.trim();
    }
    /**
     * Retrieves the data subject.
     *
     * @return the data subject
     */
    public String getSubject() {
        return subject;
    }
    /**
     * Sets the data subject.
     *
     * @param newContent the data ID to set
     */
    public void setContent(final String newContent) {
        this.content = newContent == null ? null : newContent.trim();
    }
    /**
     * Retrieves the data content.
     *
     * @return the data content
     */
    public String getContent() {
        return content;
    }
    /**
     * Sets the last modified user id.
     *
     * @param newUid the last modified user id to be set
     */
    public void setLastModifiedBy(final Integer newUid) {
        this.lastModifiedBy = newUid;
    }
    /**
     * Retrieves last modified user id.
     *
     * @return the last modified user id
     */
    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }
    /**
     * Sets the data creation date.
     *
     * @param time the data creation date to set
     */
    public void setCreatedTime(final LocalDateTime time) {
        this.createdTime = time;
    }
    /**
     * Retrieves the data creation date.
     *
     * @return the data creation date
     */
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }
    /**
     * Sets the modified date.
     *
     * @param time the modified date to set
     */
    public void setModifiedTime(final LocalDateTime time) {
        this.modifiedTime = time;
    }
    /**
     * Retrieves the modified date.
     *
     * @return the modified date
     */
    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }
}
