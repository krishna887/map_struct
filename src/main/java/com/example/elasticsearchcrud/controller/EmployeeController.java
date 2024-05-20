package com.example.elasticsearchcrud.controller;

import com.example.elasticsearchcrud.exception.custom.BookNotFoundException;
import com.example.elasticsearchcrud.model.Employee;
import com.example.elasticsearchcrud.repository.EmployeeRepository;
import com.example.elasticsearchcrud.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleResourceNotFoundException(NullPointerException e) {
        return new ResponseEntity<>("Null Point exception occur: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @PostMapping("/post")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) throws Exception {
        return new ResponseEntity<Employee>(employeeService.addEmployee(employee),HttpStatus.CREATED);
    }
}
