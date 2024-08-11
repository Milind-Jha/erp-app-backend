package com.milind.deprtmentservice.config;

import com.milind.deprtmentservice.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient(url = "http://localhost:8082/employees/", value = "Department-Service")
public interface CustomFeignClient {
    @GetMapping("/getAllEmployee/{id}")
    List<EmployeeDto> getEmployeesData(@PathVariable Long id);
}
