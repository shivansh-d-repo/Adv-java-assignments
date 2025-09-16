package com.example.elm.controller;

import com.example.elm.service.EmployeeService;
import com.example.elm.service.LeaveRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    private final EmployeeService employeeService;
    private final LeaveRequestService leaveService;

    public DashboardController(EmployeeService e, LeaveRequestService l) {
        this.employeeService = e; this.leaveService = l;
    }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model m) {
        m.addAttribute("totalEmployees", employeeService.count());
        m.addAttribute("totalLeaves", leaveService.count());
        m.addAttribute("pendingLeaves", leaveService.countPending());
        return "dashboard";
    }
}
