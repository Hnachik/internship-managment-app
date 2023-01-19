package com.bezkoder.spring.security.postgresql.dtos;

import com.bezkoder.spring.security.postgresql.enums.EStatus;
import org.springframework.web.multipart.MultipartFile;

public class TaskAssignmentDto {
    private Long id;
    private String title;
    private EStatus status;
    private MultipartFile document;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
