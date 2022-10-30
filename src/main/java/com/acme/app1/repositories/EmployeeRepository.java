package com.acme.app1.repositories;

import com.acme.app1.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/*
save(), findAll(), count(), delete()…
 */

public interface EmployeeRepository extends JpaRepository<Employee, Long>  {   //, ICustomEmployeeRepository {

    Optional<Employee> findByEmail(String email);

    // custom
    @Query(value = "SELECT e FROM Employee e ORDER BY lastname")
    public List<Employee> findAllSortedByLastname();
}
