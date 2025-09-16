package com.example.elm.controller;

import com.example.elm.entity.LeaveRequest;
import com.example.elm.entity.LeaveRequest.Status;
import com.example.elm.service.LeaveRequestService;
import com.example.elm.service.EmployeeService;
import com.example.elm.model.LeaveRequestFilter;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/leaves")
public class LeaveRequestController {
    private final LeaveRequestService leaveService;
    private final EmployeeService employeeService;

    public LeaveRequestController(LeaveRequestService ls, EmployeeService es) {
        this.leaveService = ls;
        this.employeeService = es;
    }

    @GetMapping
    public String list(@ModelAttribute("filter") LeaveRequestFilter filter, Model m) {
        // filtering logic: if filter has employeeId and/or status use repo methods
        if (filter.getEmployeeId() != null && filter.getStatus() != null) {
            m.addAttribute("leaves", leaveService.findByEmployeeAndStatus(filter.getEmployeeId(), filter.getStatus()));
        } else if (filter.getEmployeeId() != null) {
            m.addAttribute("leaves", leaveService.findByEmployeeId(filter.getEmployeeId()));
        } else if (filter.getStatus() != null) {
            m.addAttribute("leaves", leaveService.findByStatus(filter.getStatus()));
        } else {
            m.addAttribute("leaves", leaveService.getAll());
        }
        m.addAttribute("employees", employeeService.getAll());
        return "leaves";
    }

    @GetMapping("/new")
    public String newForm(Model m) {
        m.addAttribute("leaveRequest", new LeaveRequest());
        m.addAttribute("employees", employeeService.getAll());
        return "leave-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("leaveRequest") LeaveRequest leaveRequest,
                       BindingResult br,
                       @RequestParam Long employeeId) {
        if (br.hasErrors()) {
            return "leave-form";
        }
        // date validation
        if (leaveRequest.getEndDate().isBefore(leaveRequest.getStartDate())) {
            br.rejectValue("endDate", "error.leaveRequest", "End date can't be before start date");
            return "leave-form";
        }
        leaveService.create(leaveRequest, employeeId);
        return "redirect:/leaves";
    }

    @GetMapping("/approve/{id}")
    public String approve(@PathVariable Long id) {
        leaveService.approve(id);
        return "redirect:/leaves";
    }

    @GetMapping("/reject/{id}")
    public String reject(@PathVariable Long id) {
        leaveService.reject(id);
        return "redirect:/leaves";
    }
}
