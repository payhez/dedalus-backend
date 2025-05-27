package com.dedalus.employeemanagement.controller.api.v1.department;


import com.dedalus.employeemanagement.entity.Department;
import com.dedalus.employeemanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CreateDepartmentController {
    private final DepartmentService deptService;

    @Autowired
    public CreateDepartmentController(DepartmentService deptService) {
        this.deptService = deptService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Department dto) {
        try {
            deptService.create(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
        return ResponseEntity.status(201).body("The department is successfully created.");
    }
}
