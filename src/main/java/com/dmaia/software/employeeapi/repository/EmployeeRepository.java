package com.dmaia.software.employeeapi.repository;

import com.dmaia.software.employeeapi.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    Optional<Employee> findByEnroll(String enroll);

    Optional<Employee> findByEmail(String email);

    Optional<Object> findByCpf(String cpf);
}
