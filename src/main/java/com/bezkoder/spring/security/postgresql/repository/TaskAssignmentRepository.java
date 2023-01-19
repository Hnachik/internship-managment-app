package com.bezkoder.spring.security.postgresql.repository;

import com.bezkoder.spring.security.postgresql.entities.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, String> {
}
