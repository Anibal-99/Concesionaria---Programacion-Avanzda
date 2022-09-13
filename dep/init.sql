\c postgres

DROP DATABASE IF EXISTS consdb;
CREATE DATABASE consdb;

\c consdb

CREATE TABLE pais (
	id serial PRIMARY KEY,
	nombre VARCHAR(60) NOT NULL,
	codigo VARCHAR(6) NOT NULL
);

CREATE TABLE marca (
	id serial PRIMARY KEY,
	nombre VARCHAR(60) NOT NULL,
	observacion TEXT,
	pais_id INT,
	CONSTRAINT FK_marca_pais FOREIGN KEY(pais_id)
		REFERENCES pais(id)
);

CREATE TABLE cliente (
	id serial PRIMARY KEY,
	nombre VARCHAR(60),
	apellido VARCHAR(60),
	razon_social VARCHAR(60),
	CUIT VARCHAR(60) NOT NULL,
	telefono VARCHAR(60),
	direccion VARCHAR(60),
	localidad VARCHAR(60),
	pais_id INT,
	CONSTRAINT FK_cliente_pais FOREIGN KEY(pais_id)
		REFERENCES pais(id)
);

CREATE TABLE modelo (
	id serial PRIMARY KEY,
	nombre VARCHAR(60),
	version VARCHAR(60),
	marca_id INT,
	CONSTRAINT FK_modelo_marca FOREIGN KEY(marca_id)
		REFERENCES marca(id)
);

CREATE TABLE auto(
	modelo_id INT,
	ano INT,
	observacion TEXT,
	precio NUMERIC(12, 2),
	CONSTRAINT FK_auto_modelo FOREIGN KEY(modelo_id)
		REFERENCES modelo(id)
);