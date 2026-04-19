package com.ds.sqs.taxation.employee.service;

import com.ds.sqs.taxation.employee.entity.Employee;
import com.ds.sqs.taxation.employee.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    public Employee findByCompanyAndNumber(Long companyId, String employeeNumber) {
        return repository.findByCompanyIdAndEmployeeNumber(companyId, employeeNumber)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }
}