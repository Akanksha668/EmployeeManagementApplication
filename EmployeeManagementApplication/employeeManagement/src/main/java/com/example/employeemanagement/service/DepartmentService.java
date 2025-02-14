package com.example.employeemanagement.service;


import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.exceptions.DepartmentNotFoundException;
import com.example.employeemanagement.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department getOrCreateDepartment(String name) {
        Optional<Department> existingDepartment = departmentRepository.findByName(name);
        if (existingDepartment.isPresent()) {
            return existingDepartment.get();
        } else {
            Department newDepartment = Department.builder().name(name).build();
            return departmentRepository.save(newDepartment);
        }
    }
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
        		//.stream()
              //  .map(this::convertToDTO)
               // .toList();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + id));
              //  .map(this::convertToDTO)
               // .orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + id));
    }

    public void addDepartment(String name) {
        Department department = new Department();
        department.setName(name);
        departmentRepository.save(department);
    }

    public void updateDepartment(Department department) throws DepartmentNotFoundException {
//        Department department = departmentRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + id));
//        department.setName(name);
//        departmentRepository.save(department);
    	
    	Department existingDepartment = departmentRepository.findById(department.getId())
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
        
        existingDepartment.setName(department.getName());
        // Update other fields as needed
        departmentRepository.save(existingDepartment);
    }

    public void deleteDepartment(Long id) {

        departmentRepository.deleteById(id);
    }

//    private DepartmentDTO convertToDTO(Department department) {
//        return DepartmentDTO.builder()
//                .id(department.getId())
//                .name(department.getName())
//                .build();
//    }


    public Department findOrCreateDepartment(String name) {
        Department department = departmentRepository.findByName(name)
                .orElseGet(() -> {
                    Department newDepartment = new Department();
                    newDepartment.setName(name);
                    return departmentRepository.save(newDepartment);
                });

        return department;
    }
}
