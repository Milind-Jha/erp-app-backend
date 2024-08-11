package com.milind.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    Long id;
    String departmentName;
    String departmentCode;
    Long departmentHeadId;
}
