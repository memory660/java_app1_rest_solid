package com.acme.app1.services;

import com.acme.app1.models.Employee;
import com.acme.app1.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
@Service spécifie l'intention que la classe annotée est une classe métier ! Elle indique à Spring de la prendre en charge et de l'intégrer dans le contexte de l'application.
*/
@Service
public class EmployeeService implements IEmployee {
    EmployeeRepository employeeRepo;

/*
@Autowired est utilisée pour l'injection automatique de dépendances.
*/
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }
/*
@Override indique qu'une déclaration de méthode est destinée à remplacer une déclaration de méthode dans un supertype.
*/
    @Override
    public Optional<Employee> findByEmail(String email) {
        return employeeRepo.findByEmail(email);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepo.findAll();
    }

    @Override
    public Optional<Employee> findById(int id) {
        return employeeRepo.findById(id);
    }

    @Override
    public Employee save(Employee std) {
        return employeeRepo.save(std);
    }

    @Override
    public void deleteById(int id) {
        employeeRepo.deleteById(id);
    }

    /*
            @Override
            public List<Student> getAllStudents() {
                // TODO Auto-generated method stub
                return studentrepo.findAll();
            }

            @Override
            public Optional<Student> findById(int id) {
                // TODO Auto-generated method stub
                return studentrepo.findById(id);
            }

            @Override
            public Optional<Student> findByEmail(String email) {
                // TODO Auto-generated method stub
                return studentrepo.findByEmail(email);
            }

            @Override
            public Student save(Student std) {
                // TODO Auto-generated method stub
                return studentrepo.save(std);
            }

            @Override
            public void deleteById(int id) {
                // TODO Auto-generated method stub
                studentrepo.deleteById(id);
            }
*/
}
