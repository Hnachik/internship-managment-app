package com.bezkoder.spring.security.postgresql.services;

import com.bezkoder.spring.security.postgresql.enums.EStudentRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Override
    public void applyForInternship(String studentId, String internshipId, EStudentRequest studentRequest) {

    }
}
