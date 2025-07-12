package com.empresa.employee_manager.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.employee_manager.Model.Departamento;
import com.empresa.employee_manager.Model.Departamento.Estado;
import com.empresa.employee_manager.Repository.DepartamentoRepository;


@Service
public class DepartmentService {

    @Autowired
    private DepartamentoRepository departmentRepository;

    public Departamento createDepartment(Departamento department) {
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long departmentId) {
        Departamento department = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("Department not found"));
        department.setEstado(Estado.INACTIVO);
        departmentRepository.save(department);
    }
}