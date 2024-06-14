package com.milind.employeeservice.service.impl;

import com.milind.employeeservice.dto.EmployeeDto;
import com.milind.employeeservice.entity.Employee;
import com.milind.employeeservice.repository.EmployeeRepository;
import com.milind.employeeservice.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public EmployeeDto createEmployee(Employee employee) {
        if (employee.getJoiningDate()==null) {
            employee.setJoiningDate(LocalDate.now());
        }
        employee.setPassword(employee.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return modelMapper.map(employeeRepository.save(employee),EmployeeDto.class);
    }

    @Override
    public EmployeeDto getEmployee(Long id) {
        return modelMapper.map(employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Emp Not Found")),
                EmployeeDto.class);
    }

    @Override
    public EmployeeDto firstLogin(Long id, String currentpassword, String newpassword) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("employee not found"));
        if(!employee.getPassword().equals(currentpassword))  //BCRpYTENCODER NEEDED
            throw new RuntimeException("incorrect password");
        employee.setPassword(newpassword);          //BCRpYTENCODER NEEDED
        employeeRepository.save(employee);
        return modelMapper.map(employee,EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployee(Long id, Employee employee) {
        employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Emp Not found"));
        employee.setEmpId(id);
        return modelMapper.map(employeeRepository.save(employee),EmployeeDto.class);
    }

    @Override
    public void deleteEmployee(Long id) {
         employeeRepository.deleteById(id);
    }
}
