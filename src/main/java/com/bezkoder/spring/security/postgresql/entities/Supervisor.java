package com.bezkoder.spring.security.postgresql.entities;

import javax.persistence.Entity;

@Entity
public class Supervisor extends User {
    private String firstName;
    private String lastName;
}
