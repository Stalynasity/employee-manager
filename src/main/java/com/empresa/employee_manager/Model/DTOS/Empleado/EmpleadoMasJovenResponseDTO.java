package com.empresa.employee_manager.Model.DTOS.Empleado;

public class EmpleadoMasJovenResponseDTO {
    private String nombres;
    private String apellidos;
    private int edad;
    
    public EmpleadoMasJovenResponseDTO(String nombres, String apellidos, int edad) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
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
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
}
