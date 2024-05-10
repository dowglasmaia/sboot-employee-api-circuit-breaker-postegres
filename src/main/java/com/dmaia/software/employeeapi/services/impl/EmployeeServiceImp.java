package com.dmaia.software.employeeapi.services.impl;

import com.dmaia.software.employeeapi.domain.Employee;
import com.dmaia.software.employeeapi.exeptions.ObjectNotFoundExeption;
import com.dmaia.software.employeeapi.exeptions.UnprocessableEntityExeption;
import com.dmaia.software.employeeapi.repository.EmployeeRepository;
import com.dmaia.software.employeeapi.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public Employee create(Employee employee) {
        this.isUnicCPF(employee.getCpf());
        this.isUnicEmail(employee.getEmail());

        try {
            return repository.save(employee);
        } catch (Exception ex) {
            throw new UnprocessableEntityExeption("There was a failure when trying to save employee data.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public Optional<Employee> findByEnroll(String enroll) {
        return Optional.ofNullable(repository.findByEnroll(enroll).orElseThrow(
                () -> new ObjectNotFoundExeption("Employee not found.", HttpStatus.NOT_FOUND)));

    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<Object> findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    private boolean isUnicEmail(String email){
        if (!repository.findByEmail(email).isPresent()) {
            return true;
        }
        throw new UnprocessableEntityExeption("email already registered in the database", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private boolean isUnicCPF(String cpf){
        if (!repository.findByCpf(cpf).isPresent()) {
            return true;
        }
        throw new UnprocessableEntityExeption("CPF already registered in the database", HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
