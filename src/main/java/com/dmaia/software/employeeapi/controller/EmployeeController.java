package com.dmaia.software.employeeapi.controller;

import com.dmaia.software.employeeapi.controller.mapper.EmployeeMapper;
import com.dmaia.software.employeeapi.services.EmployeeService;
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
    private EmployeeService service;

    @Override
    public ResponseEntity<Void> create(CrateEmployeeRequestVO body) {
        var requestMapper = EmployeeMapper.toEmployee(body);
        service.create(requestMapper);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{enroll}")
                .buildAndExpand(requestMapper.getEnroll()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<GetEmployeeResponseVO> getByEnroll(String enroll) {
        var response = service.findByEnroll(enroll);
        var resposneMapper = EmployeeMapper.fromEmployee(response.get());
        return ResponseEntity.ok().body(resposneMapper);
    }


}
