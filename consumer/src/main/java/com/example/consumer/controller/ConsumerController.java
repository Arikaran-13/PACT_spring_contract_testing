package com.example.consumer.controller;

import com.example.consumer.dto.EmployeeDto;
import com.example.consumer.service.RestClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class ConsumerController {

    RestClient restClient;

    public ConsumerController(RestClient restClient) {
        this.restClient = restClient;
    }

    @GetMapping("/test")
    public String test(){
        return "Testing..";
    }
    @GetMapping("/employee/name/{id}")
    public ResponseEntity<EmployeeDto> getEmployee (@PathVariable("id") long id){

        return new ResponseEntity<>(
                Objects.requireNonNull(restClient.getEmployeeById(id).getBody()), HttpStatus.OK);
    }
}
