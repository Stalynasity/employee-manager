package com.empresa.employee_manager.Repository;

import com.empresa.employee_manager.Model.Departamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}