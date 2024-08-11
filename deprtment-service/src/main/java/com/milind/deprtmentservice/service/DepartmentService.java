package com.milind.deprtmentservice.service;

import com.milind.deprtmentservice.dto.DepartmentDto;
import com.milind.deprtmentservice.dto.DepartmentDtoWithEmployees;
import com.milind.deprtmentservice.entity.Department;

public interface DepartmentService {

    DepartmentDto saveDepartment(Department department);
    DepartmentDto getDepartment(Long id);
    DepartmentDtoWithEmployees getDepartmentAndEmployees(Long id);
    void deleteDepartment(Long id);
    DepartmentDto changeDepartment(Long id, Department department);
}
