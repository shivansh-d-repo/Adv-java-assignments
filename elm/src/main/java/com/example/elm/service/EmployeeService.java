package com.example.elm.service;

import com.example.elm.entity.Employee;
import com.example.elm.repository.EmployeeRepository;
import com.example.elm.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repo;
    public EmployeeService(EmployeeRepository repo) { this.repo = repo; }

    public List<Employee> getAll() { return repo.findAll(); }

    public Employee create(Employee e) { return repo.save(e); }

    public Employee update(Long id, Employee updated) {
        Employee e = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        e.setName(updated.getName());
        e.setEmail(updated.getEmail());
        e.setDepartment(updated.getDepartment());
        return repo.save(e);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Employee not found");
        repo.deleteById(id);
    }

    public Employee getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    public long count() { return repo.count(); }
}
