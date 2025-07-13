package com.empresa.employee_manager.Controller;

import com.empresa.employee_manager.Model.BaseResponse;
import com.empresa.employee_manager.Model.DTOS.Empleado.*;
import com.empresa.employee_manager.Service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateEmployee() throws Exception {
        EmpleadoRequestDTO request = new EmpleadoRequestDTO("Juan", "Perez", 30, "Dev", 2000.0, "2023-05-01", null, 0);
        EmpleadoResponseDTO responseDTO = new EmpleadoResponseDTO(1L, "Juan", "Perez", "Dev", 2000.0, "ACTIVO", "TI");
        BaseResponse<EmpleadoResponseDTO> baseResponse = new BaseResponse<>(true, responseDTO, "Empleado creado correctamente");

        Mockito.when(employeeService.createEmployee(eq(1L), any())).thenReturn(ResponseEntity.ok(baseResponse));

        mockMvc.perform(post("/employee/create/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.nombres").value("Juan"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        Mockito.doAnswer(invocation -> null)
        .when(employeeService)
        .deleteEmployee(1L);

        mockMvc.perform(post("/employee/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Empleado eliminado correctamente"));
    }

    @Test
    void testGetEmpleadoConSalarioMasAlto() throws Exception {
        empleadoSalarioMasAltoResponseDTO dto = new empleadoSalarioMasAltoResponseDTO("Carlos", "Gomez", 3000.0);
        BaseResponse<empleadoSalarioMasAltoResponseDTO> response = new BaseResponse<>(true, dto, "Empleado con mayor salario encontrado");

        Mockito.when(employeeService.obtenerEmpleadoConSalarioMasAlto()).thenReturn(ResponseEntity.ok(response));

        mockMvc.perform(get("/employee/highestSalary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.nombres").value("Carlos"));
    }

    @Test
    void testGetEmpleadoMasJoven() throws Exception {
        EmpleadoMasJovenResponseDTO dto = new EmpleadoMasJovenResponseDTO("Lucia", "Ramos", 22);
        BaseResponse<EmpleadoMasJovenResponseDTO> response = new BaseResponse<>(true, dto, "Empleado más joven encontrado");

        Mockito.when(employeeService.obtenerEmpleadoMasJoven()).thenReturn(ResponseEntity.ok(response));

        mockMvc.perform(get("/employee/lowerAge"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.nombres").value("Lucia"));
    }

    @Test
    void testGetEmpleadosDelMesPasado() throws Exception {
        BaseResponse<Long> response = new BaseResponse<>(true, 5L, "Cantidad de empleados ingresados el último mes");

        Mockito.when(employeeService.totalEmpleadosUltimoMes()).thenReturn(ResponseEntity.ok(response));

        mockMvc.perform(get("/employee/countLastMonth"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(5));
    }
}
