package com.bezkoder.spring.security.postgresql.dtos;

import com.bezkoder.spring.security.postgresql.enums.EStatus;
import org.springframework.web.multipart.MultipartFile;

public class TaskAssignmentDto {
    private String id;
    private String title;
    private String type;
    private EStatus status;
    private MultipartFile document;
    private Long taskId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public MultipartFile getDocument() {
        return document;
    }

    public void setDocument(MultipartFile document) {
        this.document = document;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
