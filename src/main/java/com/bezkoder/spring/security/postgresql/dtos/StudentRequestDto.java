package com.bezkoder.spring.security.postgresql.dtos;

import com.bezkoder.spring.security.postgresql.enums.EStudentRequest;

public class StudentRequestDto {

    private Long id;
    private String comment;
    private Long studentId;
    private Long internshipId;
    private EStudentRequest requestStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(Long internshipId) {
        this.internshipId = internshipId;
    }

    public EStudentRequest getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(EStudentRequest requestStatus) {
        this.requestStatus = requestStatus;
    }
}
