create schema bd_crucero;
use bd_crucero;

--Tabla usuario
CREATE TABLE usuario(
	id_usuario int auto increment not null,
	id_rol int not null,
	rut_usuario varchar(20) not null,
	nombre varchar(20) not null,	
	ap_paterno varchar(20) not null,
	ap_materno varchar(20) not null,
	user_name varchar(20) not null,
	correo varchar(50) not null,
	pass varchar(20) not null,
	CONSTRAINT usuario PRIMARY KEY (rut_usuario),
	FOREIGN KEY (id_rol) REFERENCES rolUsuario (id_rol)
);

--Tabla rolUsuario
CREATE TABLE rolUsuario(
	id_rol int auto increment not null,
	rut varchar(20) not null,
	nombre_rol varchar(20) not null,
	CONSTRAINT rolUsuario PRIMARY KEY()
);

--Tabla pasaje
CREATE TABLE pasaje(
	id_pasaje int auto increment not null,
	id_compra int not null,
	rut_usuario varchar(20) not null,
	CONSTRAINT pasaje PRIMARY KEY(id_pasaje),
	CONSTRAINT id_compra FOREIGN KEY compra REFERENCES(id_compra),
	CONSTRAINT rut_usuario FOREIGN KEY usuario REFERENCES(rut_usuario)
);

--Tabla compra
CREATE TABLE compra(
	id_compra int auto increment not null,
	rut_usuario varchar(20) not null,
	id_origen int not null,
	id_destino int not null,
	patente varchar(20) not null,
	id_tipo_habitacion int not null,
	fecha_enbarque date not null,
	fecha_desenbarque date,
	numero_pasajeros int not null,
	valor_pasaje int not null,
	CONSTRAINT compra PRIMARY KEY(id_compra),
	CONSTRAINT rut_usuario FOREIGN KEY usuario REFERENCES(rut_usuario),
	CONSTRAINT id_origen FOREIGN KEY puertoOrigen REFERENCES(id_origen),
	CONSTRAINT id_destino FOREIGN KEY puertoDestino REFERENCES(id_destino),
	CONSTRAINT patente FOREIGN KEY barco REFERENCES(patente),
	CONSTRAINT id_tipo_habitacion FOREIGN KEY tipoHabitacion REFERENCES(id_tipo_habitacion)
);

--Tabla formaPago
CREATE TABLE formaPago(
	id int auto increment not null,	
	nombre varchar(20) not null,
	CONSTRAINT formaPago PRIMARY KEY()
);

--Tabla puertoOrigen
CREATE TABLE puertoOrigen(
	id_origen int not null,
	numero_puerto int not null,
	nombre_puerto varchar(20) not null,
	CONSTRAINT puertoOrigen PRIMARY KEY(id_origen)
);

--Tabla puertoDestino
CREATE TABLE puertoDestino(
	id_destino int not null,
	numero_puerto int not null,
	nombre_puerto varchar(20) not null,
	CONSTRAINT puertoDestino PRIMARY KEY(id_destino)
);

--Tabla barco
CREATE TABLE barco(
	id int auto increment not null,
	patente varchar(20) not null,
	nombre_barco varchar(20) not null,
	CONSTRAINT barco PRIMARY KEY(patente)
);

--Tabla habitacion
CREATE TABLE habitacion(
	id_habitacion int auto increment not null,
	id_tipo_habitacion int not null,
	numero_habitacion not null,
	CONSTRAINT habitacion PRIMARY KEY(id_habitacion)
);

--Tabla tipoHabitacion
CREATE TABLE tipoHabitacion(
	id_tipo_habitacion int auto increment not null,
	nombre varchar(20) not null,
	cantidad_personas int,
	CONSTRAINT tipoHabitacion PRIMARY KEY(id_tipo_habitacion)
);

