package com.acme.app1.repositories;

import com.acme.app1.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
/*
save(), findAll(), count(), delete()…
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByEmail(String email);
}
