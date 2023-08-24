package com.example.demo.service;

import com.example.demo.dto.EmployeeDto;
import java.util.List;
public interface DemoService {

    EmployeeDto createUser(EmployeeDto employee);
    List<EmployeeDto> getAllEmployee();

    EmployeeDto getEmployeeById(long id);
}
