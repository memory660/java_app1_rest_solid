package com.acme.app1.repositories;

import com.acme.app1.dto.EmployeeDto;
import com.acme.app1.models.Employee;
import java.util.List;

public interface ICustomEmployeeRepository {

    List<Employee> findEmployeesX();
}