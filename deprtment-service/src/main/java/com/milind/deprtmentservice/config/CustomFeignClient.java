package com.milind.deprtmentservice.config;

import com.milind.deprtmentservice.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "EMPLOYEE-SERVICE")
public interface CustomFeignClient {
    @GetMapping("/employees/getAllEmployee/{id}")
    List<EmployeeDto> getEmployeesData(@PathVariable Long id);

    @DeleteMapping("/employees/deleteEmployee/{id}")
    void deleteEmployee(@PathVariable Long id);
}
