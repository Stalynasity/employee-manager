package com.empresa.employee_manager.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.employee_manager.Model.BaseResponse;
import com.empresa.employee_manager.Model.DTOS.Empleado.*;
import com.empresa.employee_manager.Service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create/{departmentId}")
    public ResponseEntity<BaseResponse<EmpleadoResponseDTO>> createEmployee(
            @PathVariable Long departmentId,
            @RequestBody EmpleadoRequestDTO dto) {
        return employeeService.createEmployee(departmentId, dto);
    }

    @PostMapping("/delete/{employeeId}")
    public ResponseEntity<BaseResponse<Void>> deleteEmployee(@PathVariable Long employeeId) {
        try {
            employeeService.deleteEmployee(employeeId);
            return ResponseEntity.ok(new BaseResponse<>(true, null, "Empleado eliminado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new BaseResponse<>(false, null, "Error al eliminar el empleado"));
        }
    }

    @GetMapping("/highestSalary")
    public ResponseEntity<BaseResponse<empleadoSalarioMasAltoResponseDTO>> obtenerEmpleadoConSalarioMásAlto() {
        return employeeService.obtenerEmpleadoConSalarioMasAlto();
    }

    @GetMapping("/lowerAge")
    public ResponseEntity<BaseResponse<EmpleadoMasJovenResponseDTO>> obtenerEmpleadoMásJoven() {
        return employeeService.obtenerEmpleadoMasJoven();
    }

    @GetMapping("/countLastMonth")
    public ResponseEntity<BaseResponse<Long>> TotalEmpleadosElMesPasado() {
        return employeeService.totalEmpleadosUltimoMes();
    }

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<EmpleadoModel>>> listEmployees() {
        return employeeService.listEmployees();
    }
}