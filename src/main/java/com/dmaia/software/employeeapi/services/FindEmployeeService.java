package com.dmaia.software.employeeapi.services;

import com.dmaia.software.provider.model.GetEmployeeResponseVO;


public interface FindEmployeeService {
    GetEmployeeResponseVO findByEnroll(String enroll);

}
