package com.bezkoder.spring.security.postgresql.repository;

import com.bezkoder.spring.security.postgresql.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select t from Task t where t.taskName like :kw")
    List<Task> searchTask(@Param("kw") String keyword);

    List<Task> findByInternshipId(Long id);
}
