package com.empresa.employee_manager.Model.DTOS.Departamento;

public class DepartamentoResponseDTO {

    private Long id;
    private String nombre;
    private String estado; // "ACTIVO" o "INACTIVO"

    public DepartamentoResponseDTO() {}

    public DepartamentoResponseDTO(Long id, String nombre, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
