package com.dmaia.software.employeeapi.controller;

import com.dmaia.software.employeeapi.services.CreateService;
import com.dmaia.software.employeeapi.services.mapper.EmployeeMapper;
import com.dmaia.software.employeeapi.services.FindEmployeeService;
import com.dmaia.software.provider.api.EmployeesApi;
import com.dmaia.software.provider.model.CrateEmployeeRequestVO;
import com.dmaia.software.provider.model.GetEmployeeResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1")
public class EmployeeController implements EmployeesApi {

    @Autowired
    private FindEmployeeService service;

    @Autowired
    private CreateService createService;

    @Override
    public ResponseEntity<Void> create(CrateEmployeeRequestVO body) {

        var response = createService.create(body);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{enroll}")
                .buildAndExpand(response.getEnroll()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<GetEmployeeResponseVO> getByEnroll(String enroll) {
        var response = service.findByEnroll(enroll);

        return ResponseEntity.ok().body(response);
    }


}
