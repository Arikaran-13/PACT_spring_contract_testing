package com.example.consumer.service;

import com.example.consumer.constant.RestConstants;
import com.example.consumer.dto.EmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ObjectStreamClass;
import java.util.Objects;

import static com.example.consumer.constant.RestConstants.BASE_URI;

@Service
public class RestClient {

    private final RestTemplate restTemplate;

    public RestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<EmployeeDto> getEmployeeById(long id){


        String uri = "http://localhost:8080" + RestConstants.EMPLOYEE_BY_ID.replace("{id}",String.valueOf(id));
        EmployeeDto e = restTemplate.getForEntity(uri,EmployeeDto.class).getBody();
        System.out.println("Employee name 123: "+Objects.requireNonNull(e).getName());
        return restTemplate.getForEntity(uri,EmployeeDto.class);
    }
}
