package com.example.elm.model;

import com.example.elm.entity.LeaveRequest.Status;

/**
 * Simple filter object for LeaveRequest queries.
 * Used in LeaveRequestController to filter by employee and/or status.
 */
public class LeaveRequestFilter {

    private Long employeeId;
    private Status status;

    public LeaveRequestFilter() {}

    public LeaveRequestFilter(Long employeeId, Status status) {
        this.employeeId = employeeId;
        this.status = status;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LeaveRequestFilter{" +
                "employeeId=" + employeeId +
                ", status=" + status +
                '}';
    }
}
