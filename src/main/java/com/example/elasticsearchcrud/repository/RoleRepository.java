package com.example.elasticsearchcrud.repository;

import com.example.elasticsearchcrud.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Long> {

    Optional<Roles> findByRole(String role);
}
