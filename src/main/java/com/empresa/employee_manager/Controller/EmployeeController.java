package com.empresa.employee_manager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.employee_manager.Model.Empleado;
import com.empresa.employee_manager.Service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create/{departmentId}")
    public Empleado createEmployee(@PathVariable Long departmentId, @RequestBody Empleado employee) {
        return employeeService.createEmployee(departmentId, employee);
    }

    @PostMapping("/delete/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    @GetMapping("/highestSalary")
    public Empleado getHighestSalaryEmployee() {
        return employeeService.getHighestSalaryEmployee();
    }

    @GetMapping("/lowerAge")
    public Empleado getYoungestEmployee() {
        return employeeService.getYoungestEmployee();
    }

    @GetMapping("/countLastMonth")
    public Long countEmployeesLastMonth() {
        return employeeService.countEmployeesLastMonth();
    }
}