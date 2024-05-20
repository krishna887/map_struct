package com.example.elasticsearchcrud.repository;

import com.example.elasticsearchcrud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
