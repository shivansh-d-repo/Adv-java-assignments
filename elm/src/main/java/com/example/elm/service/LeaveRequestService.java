package com.example.elm.service;

import com.example.elm.entity.LeaveRequest;
import com.example.elm.entity.LeaveRequest.Status;
import com.example.elm.entity.Employee;
import com.example.elm.repository.LeaveRequestRepository;
import com.example.elm.repository.EmployeeRepository;
import com.example.elm.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class LeaveRequestService {
    private final LeaveRequestRepository repo;
    private final EmployeeRepository employeeRepo;

    public LeaveRequestService(LeaveRequestRepository repo, EmployeeRepository employeeRepo) {
        this.repo = repo;
        this.employeeRepo = employeeRepo;
    }

    public List<LeaveRequest> getAll() { return repo.findAll(); }

    public LeaveRequest create(LeaveRequest lr, Long employeeId) {
        Employee emp = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        // validate dates: endDate >= startDate
        if (lr.getEndDate().isBefore(lr.getStartDate())) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        lr.setEmployee(emp);
        lr.setStatus(Status.PENDING);
        return repo.save(lr);
    }

    public LeaveRequest approve(Long id) {
        LeaveRequest lr = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Leave not found"));
        lr.setStatus(Status.APPROVED);
        return repo.save(lr);
    }

    public LeaveRequest reject(Long id) {
        LeaveRequest lr = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Leave not found"));
        lr.setStatus(Status.REJECTED);
        return repo.save(lr);
    }

    public List<LeaveRequest> findByStatus(Status status) { return repo.findByStatus(status); }

    public List<LeaveRequest> findByEmployeeId(Long employeeId) { return repo.findByEmployeeId(employeeId); }

    public List<LeaveRequest> findByEmployeeAndStatus(Long employeeId, Status status) {
        return repo.findByEmployeeIdAndStatus(employeeId, status);
    }

    public long count() { return repo.count(); }

    public long countPending() { return repo.findByStatus(Status.PENDING).size(); }
}
