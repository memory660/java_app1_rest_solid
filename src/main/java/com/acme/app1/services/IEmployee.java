package com.acme.app1.services;

import com.acme.app1.models.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployee {

    List<Employee> getAll();
    Optional<Employee> findById(int id);
    Optional<Employee> findByEmail(String email);
    Employee save(Employee std);
    void deleteById(int id);
}
