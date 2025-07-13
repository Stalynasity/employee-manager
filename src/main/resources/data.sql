
CREATE TABLE IF NOT EXISTS departamento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    estado INT
);

INSERT INTO departamento (nombre, estado) VALUES ('Recursos Humanos', 1);
INSERT INTO departamento (nombre, estado) VALUES ('Tecnologias de la Informacion', 1);
INSERT INTO departamento (nombre, estado) VALUES ('Finanzas', 1);
INSERT INTO departamento (nombre, estado) VALUES ('Ventas', 1);
INSERT INTO departamento (nombre, estado) VALUES ('Atencion al Cliente', 1);

CREATE TABLE IF NOT EXISTS empleado (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(255),
    apellidos VARCHAR(255),
    edad INT,
    rol VARCHAR(255),
    salario DECIMAL(10, 2),
    fechaIngreso DATE,
    estado INT,
    departamento_id BIGINT,
    FOREIGN KEY (departamento_id) REFERENCES departamento(id)
);


INSERT INTO empleado (nombres, apellidos, edad, rol, salario, fechaIngreso, estado, departamento_id)
VALUES ('Juan', 'Perez', 30, 'Desarrollador', 3000.00, '2021-01-15', 1, 1);

INSERT INTO empleado (nombres, apellidos, edad, rol, salario, fechaIngreso, estado, departamento_id)
VALUES ('Maria', 'Lopez', 25, 'Analista', 2500.00, '2022-02-01', 1, 2);

INSERT INTO empleado (nombres, apellidos, edad, rol, salario, fechaIngreso, estado, departamento_id)
VALUES ('Luis', 'Gomez', 28, 'Tester', 2200.00, '2022-06-10', 1, 2);

INSERT INTO empleado (nombres, apellidos, edad, rol, salario, fechaIngreso, estado, departamento_id)
VALUES ('Ana', 'Ruiz', 35, 'Contador', 2700.00, '2020-09-01', 1, 3);

INSERT INTO empleado (nombres, apellidos, edad, rol, salario, fechaIngreso, estado, departamento_id)
VALUES ('Carlos', 'Santos', 40, 'Vendedor', 2900.00, '2019-03-20', 1, 4);
