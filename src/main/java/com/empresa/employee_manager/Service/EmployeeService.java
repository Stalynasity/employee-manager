package com.empresa.employee_manager.Service;

import com.empresa.employee_manager.Model.BaseResponse;
import com.empresa.employee_manager.Model.DTOS.Departamento.Departamento;
import com.empresa.employee_manager.Model.DTOS.Empleado.*;
import com.empresa.employee_manager.Model.DTOS.Empleado.EmpleadoModel.Estado;
import com.empresa.employee_manager.Repository.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartamentoRepository departmentRepository;

    public ResponseEntity<BaseResponse<EmpleadoResponseDTO>> createEmployee(Long departmentId, EmpleadoRequestDTO dto) {
        Optional<Departamento> optionalDept = departmentRepository.findById(departmentId);
        if (optionalDept.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponse<>(false, null, "Departamento no encontrado"));
        }

        Departamento department = optionalDept.get();

        EmpleadoModel employee = new EmpleadoModel();
        employee.setNombres(dto.getNombres());
        employee.setApellidos(dto.getApellidos());
        employee.setEdad(dto.getEdad());
        employee.setRol(dto.getRol());
        employee.setSalario(dto.getSalario());
        employee.setFechaIngreso(LocalDate.parse(dto.getFechaIngreso()));
        employee.setFechaSalida(dto.getFechaSalida() != null ? LocalDate.parse(dto.getFechaSalida()) : null);
        employee.setEstado(Estado.values()[dto.getEstado()]); //controlar estado en front
        employee.setDepartamento(department);

        EmpleadoModel saved = employeeRepository.save(employee);

        EmpleadoResponseDTO responseDTO = new EmpleadoResponseDTO(
                saved.getId(),
                saved.getNombres(),
                saved.getApellidos(),
                saved.getRol(),
                saved.getSalario(),
                saved.getEstado().name(),
                saved.getDepartamento().getNombre());

        return ResponseEntity.ok(new BaseResponse<>(true, responseDTO, "Empleado creado correctamente"));
    }

    public ResponseEntity<BaseResponse<Void>> deleteEmployee(Long employeeId) {

        try {
            Optional<EmpleadoModel> optionalEmp = employeeRepository.findById(employeeId);
            if (optionalEmp.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new BaseResponse<>(false, null, "Empleado no encontrado"));
            }

            EmpleadoModel employee = optionalEmp.get();
            employee.setEstado(Estado.I);
            employee.setFechaSalida(LocalDate.now());
            employeeRepository.save(employee);

            return ResponseEntity.ok(new BaseResponse<>(true, null, "Empleado eliminado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponse<>(false, null, "Error al eliminar el empleado"));
        }

    }

    public ResponseEntity<BaseResponse<empleadoSalarioMasAltoResponseDTO>> obtenerEmpleadoConSalarioMasAlto() {
        List<EmpleadoModel> empleados = employeeRepository.findAll();

        Optional<EmpleadoModel> empleadoOptional = empleados.stream()
                .filter(emp -> emp.getEstado() == Estado.A)
                .max(Comparator.comparing(EmpleadoModel::getSalario));

        if (empleadoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponse<>(false, null, "No se encontró ningún empleado activo"));
        }

        EmpleadoModel empleado = empleadoOptional.get();
        empleadoSalarioMasAltoResponseDTO dto = new empleadoSalarioMasAltoResponseDTO(
                empleado.getNombres(),
                empleado.getApellidos(),
                empleado.getSalario());

        return ResponseEntity.ok(new BaseResponse<>(true, dto, "Empleado con mayor salario encontrado"));
    }

    public ResponseEntity<BaseResponse<EmpleadoMasJovenResponseDTO>> obtenerEmpleadoMasJoven() {
        List<EmpleadoModel> empleados = employeeRepository.findAll();

        Optional<EmpleadoModel> empleadoOptional = empleados.stream()
                .filter(emp -> emp.getEstado() == Estado.A)
                .min(Comparator.comparing(EmpleadoModel::getEdad));

        if (empleadoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponse<>(false, null, "No se encontró ningún empleado activo"));
        }

        EmpleadoModel employee = empleadoOptional.get();
        EmpleadoMasJovenResponseDTO dto = new EmpleadoMasJovenResponseDTO(
                employee.getNombres(),
                employee.getApellidos(),
                employee.getEdad());

        return ResponseEntity.ok(new BaseResponse<>(true, dto, "Empleado más joven encontrado"));
    }

    public ResponseEntity<BaseResponse<Long>> totalEmpleadosUltimoMes() {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        Long count = employeeRepository.countByFechaIngresoAfter(oneMonthAgo);

        return ResponseEntity.ok(new BaseResponse<>(true, count, "Cantidad de empleados ingresados el último mes"));
    }

    public ResponseEntity<BaseResponse<List<EmpleadoModel>>> listEmployees() {
        try {
            List<EmpleadoModel> employees = employeeRepository.findAllByOrderByFechaIngresoAsc();
            return ResponseEntity.ok(new BaseResponse<>(true, employees, "Lista de empleados obtenida correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponse<>(false, null, "Error al obtener la lista de empleados"));
        }
    }
}