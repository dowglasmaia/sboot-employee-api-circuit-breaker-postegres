package com.dmaia.software.employeeapi.services.impl;

import com.dmaia.software.employeeapi.exeptions.ObjectNotFoundExeption;
import com.dmaia.software.employeeapi.integrations.AddressApiIntegration;
import com.dmaia.software.employeeapi.repository.EmployeeRepository;
import com.dmaia.software.employeeapi.services.FindEmployeeService;
import com.dmaia.software.employeeapi.services.mapper.EmployeeMapper;
import com.dmaia.software.provider.model.GetEmployeeResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class FindEmployeeServiceImp implements FindEmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private AddressApiIntegration addressApiIntegration;


    @Override
    public GetEmployeeResponseVO findByEnroll(String enroll){
        log.info("Start findByEnroll with ENROLL: {}", enroll);

        var employee = Optional.ofNullable(repository.findByEnroll(enroll).orElseThrow(
              () -> new ObjectNotFoundExeption("Employee not found.", HttpStatus.NOT_FOUND)));

        var address = addressApiIntegration.findByZipCode(employee.get().getAddressZipCode());
        log.info("Address found: {}", address);

        return EmployeeMapper.fromEmployee(employee, address);
    }
}
