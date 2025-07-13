package com.empresa.employee_manager.Model.DTOS.Departamento;

public class DepartamentoRequestDTO {

    private String nombre;

    public DepartamentoRequestDTO(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
