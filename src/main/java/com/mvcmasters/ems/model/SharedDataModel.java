package com.mvcmasters.ems.model;
import java.time.LocalDateTime;

public class SharedDataModel {
    private Integer id;
    private Integer uid;
    private String subject;
    private String content;
    private LocalDateTime created_time;
    private Integer last_modified_by;
    private LocalDateTime modified_time;


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
        this.last_modified_by = uid;
    }

    public Integer getLastModifiedUserID() {
        return last_modified_by;
    }

    public void setCreatedTime(LocalDateTime time) {
        this.created_time = time;
    }

    public LocalDateTime getCreatedTime() {
        return created_time;
    }

    public void setLastModifiedTime(LocalDateTime time) {
        this.modified_time = time;
    }

    public LocalDateTime getLastModifiedTime() {
        return modified_time;
    }
}
