package com.bezkoder.spring.security.postgresql.repository;

import com.bezkoder.spring.security.postgresql.entities.StudentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRequestRepository extends JpaRepository<StudentRequest, Long> {
    List<StudentRequest> findByStudentId(Long studentId);

    List<StudentRequest> findByInternshipId(Long internshipId);
}
