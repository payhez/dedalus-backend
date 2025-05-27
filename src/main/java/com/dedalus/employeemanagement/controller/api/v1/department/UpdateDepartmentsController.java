package com.dedalus.employeemanagement.controller.api.v1.department;

import com.dedalus.employeemanagement.entity.Department;
import com.dedalus.employeemanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UpdateDepartmentsController {

    private final DepartmentService deptService;

    @Autowired
    public UpdateDepartmentsController(DepartmentService deptService) {
        this.deptService = deptService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> update(@PathVariable Long id, @RequestBody Department dto) {
        Department updated = deptService.update(id, dto);
        return ResponseEntity.ok(updated);
    }
}
