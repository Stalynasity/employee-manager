package com.empresa.employee_manager.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.empresa.employee_manager.Model.BaseResponse;
import com.empresa.employee_manager.Model.DTOS.Departamento.*;
import com.empresa.employee_manager.Model.DTOS.Departamento.Departamento.Estado;
import com.empresa.employee_manager.Repository.DepartamentoRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartamentoRepository departmentRepository;

    public ResponseEntity<BaseResponse<DepartamentoResponseDTO>> createDepartment(DepartamentoRequestDTO dto) {
        try {
            Departamento departamento = new Departamento();
            departamento.setNombre(dto.getNombre());
            departamento.setEstado(Departamento.Estado.A);

            Departamento saved = departmentRepository.save(departamento);

            return ResponseEntity.ok(new BaseResponse<>(
                true,
                new DepartamentoResponseDTO(
                    saved.getId(),
                    saved.getNombre(),
                    saved.getEstado().name()
                ),
                "Departamento creado correctamente"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new BaseResponse<>(false, null, "Error al crear el departamento")
            );
        }
    }

    public ResponseEntity<BaseResponse<Void>> deleteDepartment(Long id) {
    try {
                Optional<Departamento> optionalDepartamento = departmentRepository.findById(id);
                if (optionalDepartamento.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new BaseResponse<>(false, null, "Departamento no encontrado")
                    );
                }

                Departamento departamento = optionalDepartamento.get();
                departamento.setEstado(Estado.I);
                departmentRepository.save(departamento);

                return ResponseEntity.ok(new BaseResponse<>(true, null, "Departamento eliminado (lógicamente)"));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new BaseResponse<>(false, null, "Error al eliminar el departamento")
                );
            }
        }
    public ResponseEntity<BaseResponse<List<Departamento>>> listDepartments() {
    try {
        List<Departamento> departments = departmentRepository.findAllByOrderByNombreAsc();
        return ResponseEntity.ok(
            new BaseResponse<>(true, departments, "Lista de departamentos obtenida correctamente")
        );
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            new BaseResponse<>(false, null, "Error al obtener la lista de departamentos")
        );
    }
}
}