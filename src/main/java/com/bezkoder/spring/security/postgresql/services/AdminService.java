package com.bezkoder.spring.security.postgresql.services;

import com.bezkoder.spring.security.postgresql.dtos.InternshipDto;
import com.bezkoder.spring.security.postgresql.dtos.StudentRequestDto;
import com.bezkoder.spring.security.postgresql.entities.StudentRequest;
import com.bezkoder.spring.security.postgresql.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {
    ResponseEntity<StudentRequest> saveStudentRequest(Long studentId, StudentRequestDto studentRequestDto) throws ResourceNotFoundException;

    ResponseEntity<List<StudentRequest>> getAllStudentRequestsByStudentId(Long studentId) throws ResourceNotFoundException;

    ResponseEntity<List<StudentRequest>> getAllStudentRequestsByInternshipId(Long internshipId) throws ResourceNotFoundException;

    List<StudentRequest> getAllStudentRequests();

    ResponseEntity<StudentRequest> approveStudentRequest(StudentRequestDto studentRequestDto) throws ResourceNotFoundException;

    ResponseEntity<StudentRequest> rejectStudentRequest(StudentRequestDto studentRequestDto) throws ResourceNotFoundException;
}
