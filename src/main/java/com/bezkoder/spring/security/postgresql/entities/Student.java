package com.bezkoder.spring.security.postgresql.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "student")
public class Student extends User {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String degree;
    private byte[] cv;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "student_project",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "internship_id") })
    private Set<Internship> internships = new HashSet<>();
    @OneToMany(mappedBy = "student")
    private List<TaskAssignment> taskAssignments = new ArrayList<>();
    @OneToMany(mappedBy = "student")
    private List<StudentRequest> studentRequests = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Supervisor supervisor;

    public Student(
            final String firstName,
            final String lastName,
            final Date dateOfBirth,
            final String degree,
            final String username,
            final String email,
            final String password) {
        super(username, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.degree = degree;
    }

    public Student() {
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public byte[] getCv() {
        return cv;
    }

    public void setCv(byte[] cv) {
        this.cv = cv;
    }

    public void addInternship(Internship internship) {
        this.internships.add(internship);
        internship.getStudents().add(this);
    }

    public void removeInternship(long internshipId) {
        Internship internship = this.internships.stream().filter(t -> t.getId() == internshipId).findFirst().orElse(null);
        if (internship != null) {
            this.internships.remove(internship);
            internship.getStudents().remove(this);
        }
    }

    public List<TaskAssignment> getTaskAssignments() {
        return taskAssignments;
    }

    public void setTaskAssignments(List<TaskAssignment> taskAssignments) {
        this.taskAssignments = taskAssignments;
    }

    public List<StudentRequest> getStudentRequests() {
        return studentRequests;
    }

    public void setStudentRequests(List<StudentRequest> studentRequests) {
        this.studentRequests = studentRequests;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }
}
