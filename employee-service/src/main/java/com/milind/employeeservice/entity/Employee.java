package com.milind.employeeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long empId;
    @NotEmpty
    String firstName;
    String lastName;
    String password;
    LocalDate joiningDate;
    @NotEmpty
    LocalDate dateOfBirth;
    @NotEmpty
    int salary;
    @NotEmpty
    String designation;
    Long deptId;
    boolean passwordChanged;


}
