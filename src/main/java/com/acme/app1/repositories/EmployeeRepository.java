package com.acme.app1.repositories;

import com.acme.app1.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/*
save(), findAll(), count(), delete()…
 */

public interface EmployeeRepository extends JpaRepository<Employee, Integer>  {   //, ICustomEmployeeRepository {

    Optional<Employee> findByEmail(String email);

}
