package com.bezkoder.spring.security.postgresql.repository;

import com.bezkoder.spring.security.postgresql.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends UserRepository {
}
