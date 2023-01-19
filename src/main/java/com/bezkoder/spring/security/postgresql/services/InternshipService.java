package com.bezkoder.spring.security.postgresql.services;

import com.bezkoder.spring.security.postgresql.dtos.InternshipDto;
import com.bezkoder.spring.security.postgresql.dtos.StudentDto;
import com.bezkoder.spring.security.postgresql.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InternshipService {
    ResponseEntity<InternshipDto> saveInternship(Long managerId, InternshipDto internshipDto) throws ResourceNotFoundException;

    List<InternshipDto> listInternships();

    InternshipDto getInternship(Long internshipId) throws ResourceNotFoundException;

    InternshipDto updateInternship(InternshipDto internshipDto);

    ResponseEntity<List<InternshipDto>> getAllInternshipByStudentId(Long studentId) throws ResourceNotFoundException;

    void deleteInternship(Long internshipId);

    List<InternshipDto> searchInternships(String keyword);

    ResponseEntity<List<InternshipDto>> getAllInternshipsByManagerId(Long managerId) throws ResourceNotFoundException;
}
