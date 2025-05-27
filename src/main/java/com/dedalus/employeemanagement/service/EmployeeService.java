package com.dedalus.employeemanagement.service;

import com.dedalus.employeemanagement.entity.Department;
import com.dedalus.employeemanagement.entity.Employee;
import com.dedalus.employeemanagement.repository.DepartmentRepository;
import com.dedalus.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository empRepo;
    private final DepartmentRepository deptRepo;

    @Autowired
    public EmployeeService(EmployeeRepository empRepo, DepartmentRepository deptRepo) {
        this.empRepo = empRepo;
        this.deptRepo = deptRepo;
    }

    public Employee create(Employee employee) {
        if (empRepo.existsByEmail(employee.getEmail())) {
            throw new IllegalArgumentException("Employee with email '" + employee.getEmail() + "' already exists.");
        }
        Long deptId = employee.getDepartment().getId();
        Department dept = deptRepo.findById(deptId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + deptId));
        employee.setDepartment(dept);
        return empRepo.save(employee);
    }

    public Employee update(Long id, Employee updated) {
        Employee existing = empRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));
        if (!existing.getEmail().equals(updated.getEmail())
                && empRepo.existsByEmail(updated.getEmail())) {
            throw new IllegalArgumentException("Email '" + updated.getEmail() + "' is already in use.");
        }
        existing.setFullName(updated.getFullName());
        existing.setAddress(updated.getAddress());
        existing.setPhoneNumber(updated.getPhoneNumber());
        existing.setEmail(updated.getEmail());

        Long newDeptId = updated.getDepartment().getId();
        if (!existing.getDepartment().getId().equals(newDeptId)) {
            Department newDept = deptRepo.findById(newDeptId)
                    .orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + newDeptId));
            existing.setDepartment(newDept);
        }

        return empRepo.save(existing);
    }

    public void delete(Long id) {
        if (!empRepo.existsById(id)) {
            throw new IllegalArgumentException("Employee not found with id: " + id);
        }
        empRepo.deleteById(id);
    }

    public Employee getById(Long id) {
        return empRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));
    }

    public List<Employee> getAll() {
        return empRepo.findAll();
    }
}
