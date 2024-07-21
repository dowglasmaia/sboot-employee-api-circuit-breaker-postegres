package com.dmaia.software.employeeapi.services.impl;

import com.dmaia.software.employeeapi.domain.Employee;
import com.dmaia.software.employeeapi.exeptions.UnprocessableEntityExeption;
import com.dmaia.software.employeeapi.integrations.AddressApiIntegration;
import com.dmaia.software.employeeapi.repository.EmployeeRepository;
import com.dmaia.software.employeeapi.services.CreateService;
import com.dmaia.software.employeeapi.services.mapper.EmployeeMapper;
import com.dmaia.software.provider.model.CrateEmployeeRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateServiceImp implements CreateService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private AddressApiIntegration addressApiIntegration;

    @Override
    public Employee create(CrateEmployeeRequestVO employeeRequest){
        log.info("Start create with employeeRequest: {}", employeeRequest);

        this.isUnicCPF(employeeRequest.getCpf());
        this.isUnicEmail(employeeRequest.getEmail());

        var address = addressApiIntegration.findByZipCode(employeeRequest.getZipCode());

        var employee = EmployeeMapper.toEmployee(employeeRequest);
        employee.setAddressZipCode(address.getZip());

        try {

            return repository.save(employee);

        } catch (Exception ex) {
            log.error("Fail in CREATE New Employee, Error: ", ex.getMessage());
            throw new UnprocessableEntityExeption("There was a failure when trying to save employee data.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private boolean isUnicEmail(String email){
        if (!repository.findByEmail(email).isPresent()) {
            log.info("isUnicEmail");
            return true;
        }
        log.error("isNotUnicEmail");
        throw new UnprocessableEntityExeption("email already registered in the database", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private boolean isUnicCPF(String cpf){
        if (!repository.findByCpf(cpf).isPresent()) {
            log.info("isUnicCPF");
            return true;
        }
        log.error("isNotUnicEmail");
        throw new UnprocessableEntityExeption("CPF already registered in the database", HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
