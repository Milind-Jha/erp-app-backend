package com.milind.employeeservice.service;

import com.milind.employeeservice.dto.EmployeeDto;
import com.milind.employeeservice.entity.Employee;

public interface EmployeeService {

    EmployeeDto createEmployee(Employee employee);

    EmployeeDto getEmployee(Long id);
    EmployeeDto firstLogin(Long id,String currentpassword, String newpassword);
    EmployeeDto updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);

}
