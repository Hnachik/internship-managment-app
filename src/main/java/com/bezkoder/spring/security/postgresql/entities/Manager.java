package com.bezkoder.spring.security.postgresql.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "manager")
public class Manager extends User {
    private String firstName;
    private String lastName;
    private String profession;
    @ManyToOne
    private Company company;
    @OneToMany(mappedBy = "manager")
    private List<Internship> internships = new ArrayList<>();

    public Manager(
            final String firstName,
            final String lastName,
            final String profession,
            final String username,
            final String email,
            final String password) {
        super(username, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.profession = profession;
    }

    public Manager() {
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Internship> getInternships() {
        return internships;
    }

    public void setInternships(List<Internship> internships) {
        this.internships = internships;
    }
}
