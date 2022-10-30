package com.acme.app1.services;

import com.acme.app1.models.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployee {

    List<Employee> getAll();

    // List<Employee> getEmployeesX();

    Optional<Employee> findById(long id);
    Optional<Employee> findByEmail(String email);
    Employee save(Employee std);
    void deleteById(long id);
    // custom
    List<Employee> getAllByLastname();
}
