package com.example.elasticsearchcrud.service;

import com.example.elasticsearchcrud.model.Address;
import com.example.elasticsearchcrud.model.Employee;
import com.example.elasticsearchcrud.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final  EmployeeRepository employeeRepository;
    private final AddressService addressService;
@Transactional
    public Employee addEmployee(Employee employee) throws  Exception{
        Employee employee1= employeeRepository.save(employee);
        Address address= null;
        address.setAddress("kathmandu");
        address.setEmployee(employee);
        address.setId(123L);

        addressService.addAddress(address);
        return employee1;
    }



}
