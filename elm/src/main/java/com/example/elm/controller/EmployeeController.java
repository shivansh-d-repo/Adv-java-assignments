package com.example.elm.controller;

import com.example.elm.entity.Employee;
import com.example.elm.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService service;
    public EmployeeController(EmployeeService service) { this.service = service; }

    @GetMapping
    public String list(Model m) {
        m.addAttribute("employees", service.getAll());
        return "employees";
    }

    @GetMapping("/new")
    public String createForm(Model m) {
        m.addAttribute("employee", new Employee());
        return "employee-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("employee") Employee employee,
                       BindingResult br) {
        if (br.hasErrors()) return "employee-form";
        service.create(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model m) {
        m.addAttribute("employee", service.getById(id));
        return "employee-form";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute Employee employee,
                         BindingResult br) {
        if (br.hasErrors()) return "employee-form";
        service.update(id, employee);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/employees";
    }
}
