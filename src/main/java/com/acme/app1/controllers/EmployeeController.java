package com.acme.app1.controllers;

import com.acme.app1.dto.EmployeeDto;
import com.acme.app1.exceptions.EmployeeNotFoundException;
import com.acme.app1.models.Employee;
import com.acme.app1.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

/*
@RestController est utilisé pour créer des API RESTful. Il s'agit d'une combinaison des annotations @Controller et @ResponseBody.
*/

@RestController
@RequestMapping("/api")
public class EmployeeController {

    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

/*
    @GetMapping(value = "/employees/x")
    public List<Employee> getAllEmployeesX() {
        return employeeService.getEmployeesX();
    }
*/

    // custom
    @GetMapping(value = "/employees/x")
    public List<Employee> getAllByLastname() {
        return employeeService.getAllByLastname();
    }

    // normally
    @GetMapping(value = "/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    @GetMapping(value = "/employees/{id}")
    public Employee getEmployeeById(@PathVariable("id") @Min(1) int id) {
        Employee employee = employeeService.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with " + id + " is not found"));

        return employee;
    }

    @PostMapping(value = "/employees")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
        Optional<Employee> employeeDb = employeeService.findByEmail(employee.getEmail());

        if (!employeeDb.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.OK);
    }

    @PutMapping(value = "/employees/{id}")
    public Employee updateEmployee(@PathVariable("id") @Min(1) int id, @Valid @RequestBody Employee employee) {
        Employee employeeDb = employeeService.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with " + id + " is not found"));

        employee.setEmail(employeeDb.getEmail());
        employee.setFirstname(employeeDb.getFirstname());
        employee.setLastname(employeeDb.getLastname());

        return employeeService.save(employee);
    }

    @DeleteMapping(value = "/employees/{id}")
    public String deleteEmployee(@PathVariable("id") @Min(1) int id) {
        Employee employeeDb = employeeService.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with " + id + " is not found"));

        employeeService.deleteById(id);

        return "Employee with ID :"+id+" is deleted";
    }

/*
    @GetMapping(value="/students")
    public List<Student> getAllStudents(){
        return studentservice.getAllStudents();
    }

    @GetMapping(value="/students/{id}")
    public Student getStudentById(@PathVariable("id") @Min(1) int id) {
        Student std = studentservice.findById(id)
                                    .orElseThrow(()->new StudentNotFoundException("Student with "+id+" is Not Found!"));
        return std;
    }

    @PostMapping(value="/students")
    public Student addStudent(@Valid @RequestBody Student std) {
        return studentservice.save(std);
    }

    @PutMapping(value="/students/{id}")
    public Student updateStudent(@PathVariable("id") @Min(1) int id, @Valid @RequestBody Student newstd) {
        Student stdu = studentservice.findById(id)
                                     .orElseThrow(()->new StudentNotFoundException("Student with "+id+" is Not Found!"));
        stdu.setFirstname(newstd.getFirstname());
        stdu.setLastname(newstd.getLastname());
        stdu.setEmail(newstd.getEmail());
        return studentservice.save(stdu);
    }

    @DeleteMapping(value="/students/{id}")
    public String deleteStudent(@PathVariable("id") @Min(1) int id) {
        Student std = studentservice.findById(id)
                                     .orElseThrow(()->new StudentNotFoundException("Student with "+id+" is Not Found!"));
        studentservice.deleteById(std.getId());
        return "Student with ID :"+id+" is deleted";
    }
*/
}
