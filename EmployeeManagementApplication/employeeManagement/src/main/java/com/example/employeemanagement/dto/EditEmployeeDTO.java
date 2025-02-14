package com.example.employeemanagement.dto;

import com.example.employeemanagement.entity.Department;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditEmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String department;
}
