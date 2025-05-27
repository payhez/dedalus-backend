package com.dedalus.employeemanagement.controller.api.v1.department;

import com.dedalus.employeemanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DeleteDepartmentsController {

    private final DepartmentService deptService;

    @Autowired
    public DeleteDepartmentsController(DepartmentService deptService) {
        this.deptService = deptService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deptService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
