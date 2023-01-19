package com.bezkoder.spring.security.postgresql.entities;

import com.bezkoder.spring.security.postgresql.enums.EStudentRequest;

import javax.persistence.*;

@Entity
@Table(name = "student_request")
public class StudentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String requestComment;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Internship internship;
    @Enumerated(EnumType.STRING)
    private EStudentRequest requestStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestComment() {
        return requestComment;
    }

    public void setRequestComment(String requestComment) {
        this.requestComment = requestComment;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Internship getInternship() {
        return internship;
    }

    public void setInternship(Internship internship) {
        this.internship = internship;
    }

    public EStudentRequest getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(EStudentRequest requestStatus) {
        this.requestStatus = requestStatus;
    }
}

