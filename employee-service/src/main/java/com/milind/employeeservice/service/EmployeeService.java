package com.milind.employeeservice.service;

import com.milind.employeeservice.dto.EmployeeDto;
import com.milind.employeeservice.entity.Employee;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(Employee employee);

    EmployeeDto getEmployee(Long id);

    List<EmployeeDto>getAllEmployeeOfDepartment(Long deptId);
    EmployeeDto firstLogin(Long id,String currentpassword, String newpassword);
    EmployeeDto updateEmployee(Long id, EmployeeDto employee);
    void deleteEmployee(Long id);

}
