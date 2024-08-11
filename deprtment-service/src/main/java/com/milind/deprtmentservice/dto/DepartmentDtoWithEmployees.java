package com.milind.deprtmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDtoWithEmployees {
    DepartmentDto departmentDto;
    List<EmployeeDto> employees;
}
