package com.dmaia.software.employeeapi.services;

import com.dmaia.software.employeeapi.domain.Employee;
import com.dmaia.software.provider.model.CrateEmployeeRequestVO;

public interface CreateService {
    Employee create(CrateEmployeeRequestVO employee);
}
