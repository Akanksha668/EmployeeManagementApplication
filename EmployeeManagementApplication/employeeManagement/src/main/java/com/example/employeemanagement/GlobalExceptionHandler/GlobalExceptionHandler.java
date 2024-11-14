package com.example.employeemanagement.GlobalExceptionHandler;


import com.example.employeemanagement.entity.ErrorResponse;
import com.example.employeemanagement.exceptions.DepartmentNotFoundException;
import com.example.employeemanagement.exceptions.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DepartmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(DepartmentNotFoundException ex, Model model) {
        model.addAttribute("statusCode", HttpStatus.NOT_FOUND.value());
        model.addAttribute("errorMessage", "Resource not found");
        return "error";
    }
    
    
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEmployeeNotFound(EmployeeNotFoundException ex,Model model) {
    	model.addAttribute("statusCode", HttpStatus.NOT_FOUND.value());
        model.addAttribute("errorMessage", "Resource not found");
        return "error";
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}