package com.milind.deprtmentservice.service;

import com.milind.deprtmentservice.dto.DepartmentDto;
import com.milind.deprtmentservice.entity.Department;
import com.milind.deprtmentservice.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DepartmentDto saveDepartment(Department department) {
        return modelMapper.map( departmentRepository.save(department),DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartment(Long id) {
        return modelMapper.map(departmentRepository.findById(id).get(),DepartmentDto.class);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.delete(departmentRepository.findById(id).get());
    }

    @Override
    public DepartmentDto changeDepartment(Long id, Department department) {
        Department department1 = departmentRepository.findById(id).get();
        department1.setDepartmentName(department.getDepartmentName());
        department1.setDepartmentCode(department.getDepartmentCode());
        departmentRepository.save(department1);
        return modelMapper.map(department1,DepartmentDto.class);
    }
}
