package com.milind.employeeservice.controller;

import com.milind.employeeservice.dto.EmployeeDto;
import com.milind.employeeservice.dto.PasswordCredentials;
import com.milind.employeeservice.entity.Employee;
import com.milind.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")

public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/createEmployee")
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody Employee employee){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(employee));
    }

    @PostMapping("/changePassword/{id}")
    public ResponseEntity<EmployeeDto> firstLogin(@PathVariable Long id, @RequestBody PasswordCredentials credentials){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.firstLogin(id, credentials.getOldPassword(),
                credentials.getNewPassword()));
    }

    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id){
        return ResponseEntity.ok().body(employeeService.getEmployee(id));
    }

    @GetMapping("/getAllEmployee/{deptId}")
    public ResponseEntity<List<EmployeeDto>> getAllEmployeeOfDepartment(@PathVariable Long deptId){
        return ResponseEntity.ok().body(employeeService.getAllEmployeeOfDepartment(deptId));
    }

    @PutMapping("updateEmployee/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id,@Valid @RequestBody EmployeeDto employee){
        return ResponseEntity.ok().body(employeeService.updateEmployee(id,employee));
    }

    @DeleteMapping("deleteEmployee/{id}")
    public ResponseEntity<Void> deleteEmployee(Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

}
