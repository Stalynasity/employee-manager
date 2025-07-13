package com.empresa.employee_manager.Model.DTOS.Empleado;

public class empleadoSalarioMasAltoResponseDTO {
    private String nombres;
    private String apellidos;
    private Double salario;
    
    public empleadoSalarioMasAltoResponseDTO(String nombres, String apellidos, Double salario) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.salario = salario;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public Double getSalario() {
        return salario;
    }
    public void setSalario(Double salario) {
        this.salario = salario;
    }
}
