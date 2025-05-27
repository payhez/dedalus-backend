package com.dedalus.employeemanagement.controller.api.v1.department;

import com.dedalus.employeemanagement.entity.Department;
import com.dedalus.employeemanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class RetrieveDepartmentsController {

    private final DepartmentService deptService;
    @Autowired
    public RetrieveDepartmentsController(DepartmentService deptService) {
        this.deptService = deptService;
    }

    @GetMapping
    public ResponseEntity<List<Department>> listAll() {
        List<Department> departments = deptService.getAll();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getOne(@PathVariable Long id) {
        Department dept = deptService.getById(id);
        return ResponseEntity.ok(dept);
    }
}
