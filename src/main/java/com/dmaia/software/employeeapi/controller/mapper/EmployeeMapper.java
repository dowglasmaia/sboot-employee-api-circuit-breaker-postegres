package com.dmaia.software.employeeapi.controller.mapper;


import com.dmaia.software.employeeapi.domain.Employee;
import com.dmaia.software.provider.model.CrateEmployeeRequestVO;
import com.dmaia.software.provider.model.GetEmployeeResponseVO;

import java.time.OffsetDateTime;
import java.util.Random;
import java.util.UUID;

public class EmployeeMapper {

    public static Employee toEmployee(CrateEmployeeRequestVO body) {
        String id = String.valueOf(UUID.randomUUID());
        var enroll = String.valueOf(new Random().nextInt(99999));
        return Employee.builder()
                .id(id)
                .cpf(body.getCpf())
                .name(body.getName())
                .email(body.getEmail())
                .enroll(enroll)
                .dateCrated(OffsetDateTime.now())
                .build();
    }

    public static GetEmployeeResponseVO fromEmployee(Employee body) {
        GetEmployeeResponseVO response = new GetEmployeeResponseVO();
        response.setId(body.getId());
        response.setName(body.getName());
        response.setCpf(body.getCpf());
        response.setEmail(body.getEmail());
        response.setEnroll(body.getEnroll());
        response.setDateCrated(String.valueOf(body.getDateCrated()));
        response.setDateUpdate(String.valueOf(body.getDateUpdate()));
        return response;
    }

}
