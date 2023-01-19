package com.bezkoder.spring.security.postgresql.services;

import com.bezkoder.spring.security.postgresql.enums.EStudentRequest;

public interface StudentService {

    void applyForInternship(String studentId, String internshipId, EStudentRequest studentRequest);
}
