package com.dmaia.software.employeeapi.services;

import com.dmaia.software.employeeapi.domain.Employee;

import java.util.Optional;


public interface EmployeeService {

    Employee create(Employee employee);

    Optional<Employee> findByEnroll(String enroll);

    Optional<Employee> findByEmail(String email);

    Optional<Object> findByCpf(String cpf);
}
