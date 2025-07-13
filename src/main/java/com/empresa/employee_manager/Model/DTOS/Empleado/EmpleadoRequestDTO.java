package com.empresa.employee_manager.Model.DTOS.Empleado;

public class EmpleadoRequestDTO {
    private String nombres;
    private String apellidos;
    private Integer edad;
    private String rol;
    private Double salario;
    private String fechaIngreso;
    private String fechaSalida;
    private int estado; // 0 o 1

    public EmpleadoRequestDTO(String nombres, String apellidos, Integer edad, String rol, Double salario,
            String fechaIngreso, String fechaSalida, int estado) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.rol = rol;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.estado = estado;
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}