package com.example.demo.controller;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.DemoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
public class DemoController {

    @Autowired
    private DemoService service;

//    public DemoController(DemoService service) {
//        this.service = service;
//    }

    @GetMapping(value = "/test")
    public String getMessage(){
        return "testing...";
    }

    @PostMapping(value = "/employee")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){

        return new ResponseEntity<>(service.createUser(employeeDto) , HttpStatus.CREATED);

    }

    @GetMapping(value = "/employee/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id")long id){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(service.getEmployeeById(id),headers,HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>>getAllEmployee(){

        return new ResponseEntity<>(service.getAllEmployee(),HttpStatus.OK);
    }
}
