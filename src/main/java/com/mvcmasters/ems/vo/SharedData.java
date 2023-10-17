package com.mvcmasters.ems.vo;

public class SharedData {

    private Integer id;

    private Integer uid;

    private Integer last_modified_by;

    private String subject;

    private String content;

    private String created_time;

    private String modified_time;

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

    public void setCreatedTime(String time) {
        this.created_time = time == null ? null : time.trim();
    }

    public String getCreatedTime() {
        return created_time;
    }

    public void setLastModifiedTime(String time) {
        this.modified_time = time == null ? null : time.trim();
    }

    public String getLastModifiedTime() {
        return modified_time;
    }
}
