CREATE SCHEMA IF NOT EXISTS testdb;

USE testdb;

DROP TABLE IF EXISTS clientes;
CREATE TABLE IF NOT EXISTS clientes (
    id bigint(20) NOT NULL,
    direccion varchar(100) NOT NULL,
    edad int(11) DEFAULT NULL,
    genero varchar(255) DEFAULT NULL,
    identificacion varchar(50) DEFAULT NULL,
    nombre varchar(255) NOT NULL,
    telefono varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    estado bit(1) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY UKf47j7qamy79vhyo3v1j1oekkv (nombre)
    );
INSERT INTO clientes (id, direccion, edad, genero, identificacion, nombre, telefono, password, estado) VALUES  (359, 'Venezuela', 29, 'M', 'Alejandro', 'Alejandro', '04123515554', '123456', '1');
INSERT INTO clientes (id, direccion, edad, genero, identificacion, nombre, telefono, password, estado) VALUES  (360, 'Venezuela', 29, 'M', 'Alejandro 1', 'Alejandro 1', '04123515554', '123456', '1');
INSERT INTO clientes (id, direccion, edad, genero, identificacion, nombre, telefono, password, estado) VALUES  (361, 'Venezuela', 29, 'M', 'Alejandro 2', 'Alejandro 2', '04123515554', '123456', '1');
INSERT INTO clientes (id, direccion, edad, genero, identificacion, nombre, telefono, password, estado) VALUES  (362, 'Venezuela', 29, 'M', 'Alejandro 3', 'Alejandro 3', '04123515554', '123456', '1');
