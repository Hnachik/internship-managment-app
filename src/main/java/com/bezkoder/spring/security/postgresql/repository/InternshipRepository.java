package com.bezkoder.spring.security.postgresql.repository;

import com.bezkoder.spring.security.postgresql.entities.Internship;
import com.bezkoder.spring.security.postgresql.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipRepository extends JpaRepository<Internship, Long> {
    @Query("select c from Internship c where c.theme like :kw")
    List<Internship> searchInternship(@Param("kw") String keyword);

    @Query("select c from Internship c where c.manager.id = :managerId")
    List<Internship> findByManagerId(@Param("managerId") Long managerId);

    void deleteByManagerId(long tutorialId);

    @Query("select c from Internship c where c.students")
    List<Internship> findByStudentsId(Student student);
}
