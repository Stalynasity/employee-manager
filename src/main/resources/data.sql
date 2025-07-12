INSERT INTO departamento (nombre, estado) VALUES ('Recursos Humanos', 'ACTIVO');
INSERT INTO departamento (nombre, estado) VALUES ('IT', 'ACTIVO');

INSERT INTO empleado (nombres, apellidos, edad, rol, salario, fecha_ingreso, estado, departamento_id) 
VALUES ('Juan', 'Perez', 28, 'Desarrollador', 3000, '2022-01-01', 'ACTIVO', 1);

INSERT INTO empleado (nombres, apellidos, edad, rol, salario, fecha_ingreso, estado, departamento_id) 
VALUES ('Maria', 'Lopez', 22, 'Analista', 2500, '2023-02-01', 'ACTIVO', 2);