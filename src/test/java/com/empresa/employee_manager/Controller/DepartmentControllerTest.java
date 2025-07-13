package com.empresa.employee_manager.Controller;

import com.empresa.employee_manager.Model.BaseResponse;
import com.empresa.employee_manager.Model.DTOS.Departamento.DepartamentoRequestDTO;
import com.empresa.employee_manager.Model.DTOS.Departamento.DepartamentoResponseDTO;
import com.empresa.employee_manager.Service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private DepartmentService departmentService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testCreateDepartment() throws Exception {
        DepartamentoRequestDTO request = new DepartamentoRequestDTO("TI");
        DepartamentoResponseDTO response = new DepartamentoResponseDTO(1L, "TI", "ACTIVO");

        Mockito.when(departmentService.createDepartment(any(DepartamentoRequestDTO.class)))
            .thenReturn(ResponseEntity.ok(new BaseResponse<>(true, response, "Departamento creado correctamente")));

        mockMvc.perform(MockMvcRequestBuilders.post("/department/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.nombre").value("TI"))
                .andExpect(jsonPath("$.message").value("Departamento creado correctamente"));
    }

    @Test
    void testDeleteDepartment() throws Exception {
        Mockito.when(departmentService.deleteDepartment(1L))
                .thenReturn(ResponseEntity.ok(new BaseResponse<>(true, null, "Departamento eliminado")));

        mockMvc.perform(MockMvcRequestBuilders.post("/department/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Departamento eliminado"));
    }
}
