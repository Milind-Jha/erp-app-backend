package com.milind.deprtmentservice.controller;

import com.milind.deprtmentservice.dto.DepartmentDto;
import com.milind.deprtmentservice.entity.Department;
import com.milind.deprtmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/departmentInfo/{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable Long id){
        return ResponseEntity.ok().body(departmentService.getDepartment(id));
    }

    @PostMapping("/createDepartment")
    public ResponseEntity<DepartmentDto> getDepartment(@RequestBody Department department){
        DepartmentDto departmentDto = departmentService.saveDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentDto);
    }


    @PutMapping("/changeDepartmentInfo/{id}")
    public ResponseEntity<DepartmentDto> changeDepartmentInfo(@PathVariable Long id,@RequestBody Department department){
        DepartmentDto departmentDto = departmentService.changeDepartment(id,department);
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentDto);
    }

    @DeleteMapping("/deleteDepartmentInfo/{id}")
    public ResponseEntity<Void>  deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
