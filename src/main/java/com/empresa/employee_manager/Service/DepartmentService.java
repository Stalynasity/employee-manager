package com.empresa.employee_manager.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.empresa.employee_manager.Model.BaseResponse;
import com.empresa.employee_manager.Model.DTOS.Departamento.*;
import com.empresa.employee_manager.Repository.DepartamentoRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartamentoRepository departmentRepository;

    public ResponseEntity<BaseResponse<DepartamentoResponseDTO>> createDepartment(DepartamentoRequestDTO dto) {
        try {
            Departamento departamento = new Departamento();
            departamento.setNombre(dto.getNombre());
            departamento.setEstado(Departamento.Estado.ACTIVO);

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
            if (!departmentRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new BaseResponse<>(false, null, "Departamento no encontrado")
                );
            }

            departmentRepository.deleteById(id);
            return ResponseEntity.ok(new BaseResponse<>(true, null, "Departamento eliminado"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new BaseResponse<>(false, null, "Error al eliminar el departamento")
            );
        }
    }
}