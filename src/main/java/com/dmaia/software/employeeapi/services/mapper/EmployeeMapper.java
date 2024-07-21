package com.dmaia.software.employeeapi.services.mapper;


import com.dmaia.software.employeeapi.domain.Employee;
import com.dmaia.software.provider.model.AddressResponseVO;
import com.dmaia.software.provider.model.CrateEmployeeRequestVO;
import com.dmaia.software.provider.model.GetEmployeeResponseVO;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

public class EmployeeMapper {

    public static Employee toEmployee(CrateEmployeeRequestVO body){
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

    public static GetEmployeeResponseVO fromEmployee(Optional<Employee> body, AddressResponseVO address){
        if (!body.isPresent()) {
            return new GetEmployeeResponseVO();
        }

        Employee employee = body.get();
        GetEmployeeResponseVO response = new GetEmployeeResponseVO();
        response.setId(employee.getId());
        response.setName(employee.getName());
        response.setCpf(employee.getCpf());
        response.setEmail(employee.getEmail());
        response.setEnroll(employee.getEnroll());
        response.setDateCrated(String.valueOf(employee.getDateCrated()));
        response.setDateUpdate(String.valueOf(employee.getDateUpdate()));
        response.setAddress(getAddress(address));
        return response;
    }

    private static AddressResponseVO getAddress(AddressResponseVO address){
        AddressResponseVO addressResponse = new AddressResponseVO();
        addressResponse.setStreet(address.getStreet());
        addressResponse.setCity(address.getCity());
        addressResponse.setNeighborhood(address.getNeighborhood());
        addressResponse.setZip(address.getZip());
        addressResponse.setState(address.getState());
        addressResponse.setId(address.getId());
        addressResponse.setNumber(address.getNumber());
        return addressResponse;
    }

}
