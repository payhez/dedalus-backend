package com.dedalus.employeemanagement.service;

import com.dedalus.employeemanagement.model.Department;
import com.dedalus.employeemanagement.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentService {
    private final DepartmentRepository deptRepo;

    @Autowired
    public DepartmentService(DepartmentRepository deptRepo) {
        this.deptRepo = deptRepo;
    }

    public Department create(Department department) {
        // Check uniqueness
        if (deptRepo.existsByName(department.getName())) {
            throw new IllegalArgumentException("Department with name '"
                    + department.getName()
                    + "' already exists.");
        }
        return deptRepo.save(department);
    }

    public Department update(Long id, Department updated) {
        Department existing = deptRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + id));
        if (!existing.getName().equals(updated.getName())
                && deptRepo.existsByName(updated.getName())) {
            throw new IllegalArgumentException("Department name '"
                    + updated.getName()
                    + "' is already in use.");
        }
        existing.setName(updated.getName());
        return deptRepo.save(existing);
    }

    public void delete(Long id) {
        if (!deptRepo.existsById(id)) {
            throw new IllegalArgumentException("Department not found with id: " + id);
        }
        deptRepo.deleteById(id);
    }

    public Department getById(Long id) {
        return deptRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + id));
    }

    public List<Department> getAll() {
        return deptRepo.findAll();
    }
}
