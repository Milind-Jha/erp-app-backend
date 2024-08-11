package com.milind.employeeservice.service.impl;

import com.milind.employeeservice.dto.DepartmentDto;
import com.milind.employeeservice.dto.EmployeeDto;
import com.milind.employeeservice.entity.Employee;
import com.milind.employeeservice.repository.EmployeeRepository;
import com.milind.employeeservice.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmployeeDto createEmployee(Employee employee) {
        if (employee.getJoiningDate()==null) {
            employee.setJoiningDate(LocalDate.now());
        }
        employee.setPasswordChanged(false);
        employee.setPassword(employee.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Employee saveEmp = employeeRepository.save(employee);
        try {
            if(saveEmp.getDeptId()!= null && getEmployeeDtoWithDepartmentName(saveEmp.getDeptId(),saveEmp) == null){}
        }catch (Exception e){
            deleteEmployee(employee.getEmpId());
            throw new RuntimeException("Invalid department. No Such department exixts");
        }
        return modelMapper.map(saveEmp,EmployeeDto.class);
    }
    @Override
    public EmployeeDto getEmployee(Long id) {
        Employee employee = getEmployeeFromDb(id);
        return getEmployeeDtoWithDepartmentName(employee.getDeptId(), employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployeeOfDepartment(Long deptId) {
        return employeeRepository.findAll().stream().filter(employee -> employee.getDeptId().equals(deptId))
                .collect(Collectors.toList()).stream().map(employee -> modelMapper.map(employee,EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto firstLogin(Long id, String currentpassword, String newpassword) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("employee not found"));
        if(!employee.getPassword().equals(currentpassword))  //BCRpYTENCODER NEEDED
            throw new RuntimeException("incorrect password");
        employee.setPassword(newpassword);          //BCRpYTENCODER NEEDED
        employee.setPasswordChanged(true);
        employeeRepository.save(employee);
        return getEmployeeDtoWithDepartmentName(id,employee);
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = getEmployeeFromDb(id);
        modelMapper.map(employeeDto, employee);
       // employee.setEmpId(id);
        return modelMapper.map(employeeRepository.save(employee),EmployeeDto.class);
    }
    @Override
    public void deleteEmployee(Long id) {
         getEmployeeFromDb(id);
         employeeRepository.deleteById(id);
    }
    private Employee getEmployeeFromDb(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Emp Not Found"));
        return employee;
    }
    private EmployeeDto getEmployeeDtoWithDepartmentName(Long id, Employee employee) {
        ResponseEntity<DepartmentDto> dtoResponseEntity = restTemplate.exchange(
                "http://localhost:8081/departments/departmentInfo/" + id, HttpMethod.GET,
                null, DepartmentDto.class);
        if(dtoResponseEntity.getStatusCode()== HttpStatus.OK && dtoResponseEntity.getBody()!=null) {
            EmployeeDto employeeDto = modelMapper.map(employee,
                    EmployeeDto.class);
            employeeDto.setDepartmentDetails(dtoResponseEntity.getBody());
            return employeeDto;
        }
        return null;
    }

}
