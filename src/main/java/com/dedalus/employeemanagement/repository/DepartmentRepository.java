package com.dedalus.employeemanagement.repository;

import com.dedalus.employeemanagement.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByName(String name);
}
