create schema bd_crucero;
use bd_crucero;

--Tabla rolUsuario
CREATE TABLE rolUsuario(
    id int auto_increment,
    rut_usuario varchar(20) not null,
    nombre_rol varchar(20) not null,
    primary key(id)
);

--Tabla usuario
CREATE TABLE usuario(
    id int auto_increment,
    id_rol int not null,
    rut_usuario varchar(20) not null,
    nombre varchar(20) not null,	
    ap_paterno varchar(20) not null,
    ap_materno varchar(20) not null,
    user_name varchar(20) not null,
    correo varchar(50) not null,
    pass varchar(255) not null,
    PRIMARY KEY (id),
    FOREIGN KEY (id_rol) REFERENCES rolUsuario (id)
);

--Tabla puertoOrigen
CREATE TABLE puertoOrigen(
    id int auto_increment,
    numero_puerto int not null,
    nombre_puerto varchar(20) not null,
    PRIMARY KEY(id)
);

--Tabla puertoDestino
CREATE TABLE puertoDestino(
    id int auto_increment,
    numero_puerto int not null,
    nombre_puerto varchar(20) not null,
    PRIMARY KEY(id)
);

--Tabla barco
CREATE TABLE barco(
    id int auto_increment,
    patente varchar(20) not null,
    nombre_barco varchar(20) not null,
    PRIMARY KEY(id)
);

--Tabla tipoHabitacion
CREATE TABLE tipoHabitacion(
    id int auto_increment,
    nombre varchar(20) not null,
    cantidad_personas int,
    PRIMARY KEY(id)
);

--Tabla habitacion
CREATE TABLE habitacion(
    id int auto_increment,
    id_tipo_habitacion int not null,
    numero_habitacion int UNSIGNED not null,
    PRIMARY KEY(id),
    FOREIGN KEY(id_tipo_habitacion) REFERENCES tipoHabitacion(id)
);

--Tabla compra
CREATE TABLE compra(
    id int auto_increment,
    id_usuario int not null,
    id_origen int not null,
    id_destino int not null,
    id_barco int not null,
    id_habitacion int not null,
    fecha_enbarque date not null,
    fecha_desenbarque date,
    numero_pasajeros int not null,
    valor_pasaje int not null,
    PRIMARY KEY(id),
    FOREIGN KEY(id_usuario) REFERENCES usuario(id),
    FOREIGN KEY(id_origen) REFERENCES puertoOrigen(id),
    FOREIGN KEY(id_destino) REFERENCES puertoDestino(id),
    FOREIGN KEY(id_barco) REFERENCES barco(id),
    FOREIGN KEY(id_habitacion) REFERENCES habitacion(id)
);

--Tabla pasaje
CREATE TABLE pasaje(
    id int auto_increment,
    id_compra int not null,
    id_usuario int not null,
    PRIMARY KEY(id),
    FOREIGN KEY(id_compra) REFERENCES compra(id),
    FOREIGN KEY(id_usuario) REFERENCES usuario(id)
);

--Tabla formaPago
CREATE TABLE formaPago(
    id int auto_increment,	
    nombre varchar(20) not null,
    primary key(id)
);

