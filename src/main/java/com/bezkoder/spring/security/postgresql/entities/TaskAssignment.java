package com.bezkoder.spring.security.postgresql.entities;

import com.bezkoder.spring.security.postgresql.enums.EStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "task_assignment")
public class TaskAssignment {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String title;
    private String type;
    @Enumerated(EnumType.STRING)
    private EStatus status;
    @Lob
    private byte[] document;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Task task;

    public TaskAssignment(String documentName, String contentType, EStatus status, byte[] bytes) {
        this.title = documentName;
        this.type = contentType;
        this.status = status;
        this.document = bytes;
    }

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

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
