package com.empresa.employee_manager.Repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.employee_manager.Model.Empleado;

public interface EmployeeRepository extends JpaRepository<Empleado, Long> {

    Empleado findTopByOrderBySalarioDesc();
    Empleado findTopByOrderByEdadAsc();
    Long countByFechaIngresoAfter(LocalDate date);
}