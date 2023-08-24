package com.example.demo.serviceImpl;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.DemoService;
import org.apache.el.stream.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements DemoService {

    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository , ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = mapper;
    }

    @Override
    public EmployeeDto createUser(EmployeeDto employee) {

        Employee emp = employeeRepository.save(modelMapper.map(employee, Employee.class));

        return modelMapper.map(emp,EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {

       return employeeRepository
                .findAll()
                .stream()
                .map(employee -> modelMapper.map(employee,EmployeeDto.class))
                .toList();
    }

    @Override
    public EmployeeDto getEmployeeById(long id) {

        Employee emp = employeeRepository
                .findById(id)
                .orElseThrow( ()-> new RuntimeException("Employee not found"));

        return modelMapper.map(emp, EmployeeDto.class);
    }
}
