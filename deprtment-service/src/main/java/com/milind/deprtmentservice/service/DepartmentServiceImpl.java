package com.milind.deprtmentservice.service;

import com.milind.deprtmentservice.config.CustomFeignClient;
import com.milind.deprtmentservice.dto.DepartmentDto;
import com.milind.deprtmentservice.dto.DepartmentDtoWithEmployees;
import com.milind.deprtmentservice.dto.EmployeeDto;
import com.milind.deprtmentservice.entity.Department;
import com.milind.deprtmentservice.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private WebClient webClient;
    @Autowired
    private CustomFeignClient customFeignClient;

    @Override
    public DepartmentDto saveDepartment(Department department) {
        return modelMapper.map( departmentRepository.save(department),DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartment(Long id) {
        return modelMapper.map(departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid Department Id")),DepartmentDto.class);
    }

    @Override
    public DepartmentDtoWithEmployees getDepartmentAndEmployees(Long id) {
        DepartmentDto departmentDto = modelMapper.map(departmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Invalid Department Id")), DepartmentDto.class);
        List<EmployeeDto> employeeDtoList = webClient.get()
                .uri("/employees/getAllEmployee/{id}", id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<EmployeeDto>>() {})
                .block();
        DepartmentDtoWithEmployees departmentDtoWithEmployees = new DepartmentDtoWithEmployees();
        departmentDtoWithEmployees.setDepartmentDto(departmentDto);
        departmentDtoWithEmployees.setEmployees(employeeDtoList);
        return departmentDtoWithEmployees;
    }

    @Override
    public void deleteDepartment(Long id) {
        List<EmployeeDto> employeeDtoList = customFeignClient.getEmployeesData(id);
        System.out.println(employeeDtoList);
        employeeDtoList.forEach(
                employeeDto -> customFeignClient.deleteEmployee(employeeDto.getEmpId())
        );
        departmentRepository.delete(departmentRepository.findById(id).get());
    }

    @Override
    public DepartmentDto changeDepartment(Long id, Department department) {
        System.out.println(department);
        Department department1 = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("department not found"));
        List<EmployeeDto> employeeDtoList = customFeignClient.getEmployeesData(id);
        System.out.println("feign client fetch : "+employeeDtoList);
        modelMapper.map(department,department1);
        departmentRepository.save(department1);
        return modelMapper.map(department1,DepartmentDto.class);
    }
}
