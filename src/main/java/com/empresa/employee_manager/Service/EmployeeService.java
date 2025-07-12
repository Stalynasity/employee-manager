package com.empresa.employee_manager.Service;

import com.empresa.employee_manager.Model.Departamento;
import com.empresa.employee_manager.Model.Empleado;
import com.empresa.employee_manager.Repository.DepartamentoRepository;
import com.empresa.employee_manager.Repository.EmployeeRepository;
import com.empresa.employee_manager.Model.Empleado.Estado;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartamentoRepository departmentRepository;

    public Empleado createEmployee(Long departmentId, Empleado employee) {
        Departamento department = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("Department not found"));
        employee.setDepartamento(department);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long employeeId) {
        Empleado employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setEstado(Estado.INACTIVO);
        employeeRepository.save(employee);
    }

    public Empleado getHighestSalaryEmployee() {
        return employeeRepository.findTopByOrderBySalarioDesc();
    }

    public Empleado getYoungestEmployee() {
        return employeeRepository.findTopByOrderByEdadAsc();
    }

    public Long countEmployeesLastMonth() {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        return employeeRepository.countByFechaIngresoAfter(oneMonthAgo);
    }
}