package com.empresa.employee_manager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.empresa.employee_manager.Model.BaseResponse;
import com.empresa.employee_manager.Model.DTOS.Departamento.*;
import com.empresa.employee_manager.Service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<DepartamentoResponseDTO>> createDepartment(@RequestBody DepartamentoRequestDTO department) {
        return departmentService.createDepartment(department);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteDepartment(@PathVariable Long id) {
        return departmentService.deleteDepartment(id);
    }
}