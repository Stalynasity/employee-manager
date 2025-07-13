package com.empresa.employee_manager.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.employee_manager.Model.DTOS.Departamento.Departamento;


@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    List<Departamento> findAllByOrderByNombreAsc();
}