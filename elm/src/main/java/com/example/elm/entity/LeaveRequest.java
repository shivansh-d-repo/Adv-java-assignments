package com.example.elm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "leave_request")
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many-to-one relationship
    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @NotNull(message = "Leave type is required")
    @Size(max = 50)
    private String leaveType; // e.g., Sick, Casual, Paid

    @NotNull(message = "Start date required")
    private LocalDate startDate;

    @NotNull(message = "End date required")
    private LocalDate endDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    public enum Status {
        PENDING, APPROVED, REJECTED
    }

    // add validation helper: ensure endDate >= startDate in service or custom validator
    public LeaveRequest() {}
    // getters/setters...
}
