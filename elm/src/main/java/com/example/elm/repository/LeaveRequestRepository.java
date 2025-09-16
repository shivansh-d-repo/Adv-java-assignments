package com.example.elm.repository;

import com.example.elm.entity.LeaveRequest;
import com.example.elm.entity.LeaveRequest.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByStatus(Status status);
    List<LeaveRequest> findByEmployeeId(Long employeeId);
    List<LeaveRequest> findByEmployeeIdAndStatus(Long employeeId, Status status);
}
