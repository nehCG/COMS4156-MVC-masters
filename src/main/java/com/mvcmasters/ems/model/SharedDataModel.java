package com.mvcmasters.ems.model;
import java.time.LocalDateTime;

public class SharedDataModel {
    private Integer id;
    private Integer uid;
    private String subject;
    private String content;
    private LocalDateTime createdTime;
    private Integer lastModifiedBy;
    private LocalDateTime modifiedTime;

    // Getter and Setter functions for the attributes
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserID() {
        return uid;
    }

    public void setUserId(Integer uid) {
        this.uid = uid;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getContent() {
        return content;
    }

    public void setLastModifiedUserID(Integer uid) {
        this.lastModifiedBy = uid;
    }

    public Integer getLastModifiedUserID() {
        return lastModifiedBy;
    }

    public void setCreatedTime(LocalDateTime time) {
        this.createdTime = time;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setModifiedTime(LocalDateTime time) {
        this.modifiedTime = time;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }
}
