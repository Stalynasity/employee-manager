package com.empresa.employee_manager.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.employee_manager.Model.DTOS.Empleado.EmpleadoModel;

public interface EmployeeRepository extends JpaRepository<EmpleadoModel, Long> {
    Long countByFechaIngresoAfter(LocalDate date);
    List<EmpleadoModel> findAllByOrderByFechaIngresoAsc();
}