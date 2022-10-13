package com.acme.app1.repositories;

import com.acme.app1.models.Employee;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomEmployeeRepositoryImpl implements  ICustomEmployeeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> findEmployeesX() {
        return entityManager.createNativeQuery("SELECT e FROM employee e").getResultList();
    }

}
