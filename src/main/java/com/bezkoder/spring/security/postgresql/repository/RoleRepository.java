package com.bezkoder.spring.security.postgresql.repository;

import com.bezkoder.spring.security.postgresql.entities.Role;
import com.bezkoder.spring.security.postgresql.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
