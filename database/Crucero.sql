create database if not exists bd_crucero;
use bd_crucero;

-- Tabla rolUsuario--
CREATE TABLE rolUsuario(
    id int auto_increment,
    nombre_rol varchar(20) not null,
    primary key(id)
);

-- Tabla usuario--
CREATE TABLE usuario(
    id int auto_increment,
    id_rol int not null,
    rut_usuario int UNSIGNED UNIQUE not null,
    dv_usuario char(1),
    nombre varchar(20) not null,	
    ap_paterno varchar(20) not null,
    ap_materno varchar(20) not null,
    user_name varchar(20) not null,
    correo varchar(50) not null,
    pass varchar(255) not null,
    PRIMARY KEY (id),
    FOREIGN KEY (id_rol) REFERENCES rolUsuario (id)
);

-- Tabla puertoOrigen--
CREATE TABLE puertoOrigen(
    id int auto_increment,
    numero_puerto int not null,
    nombre_puerto varchar(40) not null,
    PRIMARY KEY(id)
);

-- Tabla puertoDestino--
CREATE TABLE puertoDestino(
    id int auto_increment,
    numero_puerto int not null,
    nombre_puerto varchar(40) not null,
    PRIMARY KEY(id)
);

-- Tabla barco--
CREATE TABLE barco(
    id int auto_increment,
    patente varchar(20) not null,
    nombre_barco varchar(30) not null,
    PRIMARY KEY(id)
);

-- Tabla tipoHabitacion--
CREATE TABLE tipoHabitacion(
    id int auto_increment,
    nombre varchar(20) not null,
    cantidad_personas int,
    PRIMARY KEY(id)
);

-- Tabla habitacion--
CREATE TABLE habitacion(
    id int auto_increment,
    id_tipo_habitacion int not null,
    numero_habitacion int UNSIGNED not null,
    PRIMARY KEY(id),
    FOREIGN KEY(id_tipo_habitacion) REFERENCES tipoHabitacion(id)
);

-- Tabla formaPago--
CREATE TABLE formaPago(
    id int auto_increment,
    nombre varchar(20) not null,
    primary key(id)
);

-- Tabla compra--
CREATE TABLE compra(
    id int auto_increment,
    id_usuario int not null,
    id_origen int not null,
    id_destino int not null,
    id_barco int not null,
    id_habitacion int not null,
    id_forma_pago int not null,
    fecha_enbarque date not null,
    fecha_desenbarque date not null,
    numero_pasajeros int not null,
    valor_pasaje int not null,
    PRIMARY KEY(id),
    FOREIGN KEY(id_usuario) REFERENCES usuario(id),
    FOREIGN KEY(id_origen) REFERENCES puertoOrigen(id),
    FOREIGN KEY(id_destino) REFERENCES puertoDestino(id),
    FOREIGN KEY(id_barco) REFERENCES barco(id),
    FOREIGN KEY(id_habitacion) REFERENCES habitacion(id),
    FOREIGN KEY(id_forma_pago) REFERENCES formaPago(id)
);

-- Tabla pasaje--
CREATE TABLE pasaje(
    id int auto_increment,
    id_compra int not null,
    PRIMARY KEY(id),
    FOREIGN KEY(id_compra) REFERENCES compra(id)
);

-- Insertar datos necesarios--
alter table rolusuario auto_increment = 1;
insert into rolusuario (nombre_rol) values ('Estandar');
insert into rolusuario (nombre_rol) values ('Administrador');

-- Insertar datos barcos--
alter table barco auto_increment = 1;
insert into barco(patente,nombre_barco) values ('ABCD1234', 'Royal Caribbean');
insert into barco(patente,nombre_barco) values ('MVNT9574', 'MSC Cruceros');
insert into barco(patente,nombre_barco) values ('PEJT5154', 'Costa Cruceros');
insert into barco(patente,nombre_barco) values ('IDJE7651', 'Celebrity Infinity Cruises');
insert into barco(patente,nombre_barco) values ('NKFS8510', 'Carnival Cruises');
insert into barco(patente,nombre_barco) values ('AWCV9512', 'Pullmantur Cruises');

-- Insertar datos formas de pago--
alter table formaPago auto_increment = 1;
insert into formaPago(nombre) values ('WebPay');
insert into formaPago(nombre) values ('Credito');
insert into formaPago(nombre) values ('Paypal');

-- Insertar datos puertosOrigen--
alter table puertoOrigen auto_increment = 1;
insert into puertoOrigen(numero_puerto,nombre_puerto) values (1, 'Puerto Barcelona');
insert into puertoOrigen(numero_puerto,nombre_puerto) values (2, 'Puerto de Baleares');
insert into puertoOrigen(numero_puerto,nombre_puerto) values (3, 'Puerto de Santa Cruz de Tenerife');
insert into puertoOrigen(numero_puerto,nombre_puerto) values (4, 'Puerto de Las Palmas');
insert into puertoOrigen(numero_puerto,nombre_puerto) values (5, 'Puerto de Málaga');
insert into puertoOrigen(numero_puerto,nombre_puerto) values (6, 'Puerto de la Bahía de Cádiz');
insert into puertoOrigen(numero_puerto,nombre_puerto) values (7, 'Puerto de Valencia');
insert into puertoOrigen(numero_puerto,nombre_puerto) values (8, 'Puerto de Vigo');
insert into puertoOrigen(numero_puerto,nombre_puerto) values (9, 'Puerto de La Coruña');
insert into puertoOrigen(numero_puerto,nombre_puerto) values (10, 'Puerto de Cartagena');

-- Insertar datos puertosDestino--
alter table puertoDestino auto_increment = 1;
insert into puertoDestino(numero_puerto,nombre_puerto) values (1, 'Puerto Barcelona');
insert into puertoDestino(numero_puerto,nombre_puerto) values (2, 'Puerto de Baleares');
insert into puertoDestino(numero_puerto,nombre_puerto) values (3, 'Puerto de Santa Cruz de Tenerife');
insert into puertoDestino(numero_puerto,nombre_puerto) values (4, 'Puerto de Las Palmas');
insert into puertoDestino(numero_puerto,nombre_puerto) values (5, 'Puerto de Málaga');
insert into puertoDestino(numero_puerto,nombre_puerto) values (6, 'Puerto de la Bahía de Cádiz');
insert into puertoDestino(numero_puerto,nombre_puerto) values (7, 'Puerto de Valencia');
insert into puertoDestino(numero_puerto,nombre_puerto) values (8, 'Puerto de Vigo');
insert into puertoDestino(numero_puerto,nombre_puerto) values (9, 'Puerto de La Coruña');
insert into puertoDestino(numero_puerto,nombre_puerto) values (10, 'Puerto de Cartagena');

-- Insertar datos tipoHabitacion
alter table tipoHabitacion auto_increment = 1;
insert into tipoHabitacion(nombre,cantidad_personas) values ('Camarote Interior',10);
insert into tipoHabitacion(nombre,cantidad_personas) values ('Camarote Oceanview',20);
insert into tipoHabitacion(nombre,cantidad_personas) values ('Camarote con balcón',30);
insert into tipoHabitacion(nombre,cantidad_personas) values ('Suite',40);
insert into tipoHabitacion(nombre,cantidad_personas) values ('Suite de lujo',50);

--Insertar datos usuario
alter table usuario auto_increment = 1;
insert into usuario(id_rol,rut_usuario,dv_usuario,nombre,ap_paterno,ap_materno,user_name,correo,pass,) values (1,'17486227','3','Francisco','Rodriguez','Torres','Pancho','fcorodriguez@gmail.com','$2a$12$YvSkLIIiaU/M1aWre9FrVeBnZcxkdI4.VjctxQuniM.b9ze1EN/Aq');
insert into usuario(id_rol,rut_usuario,dv_usuario,nombre,ap_paterno,ap_materno,user_name,correo,pass,) values (2,'18464695','1','Lucas','Lizama','Monje','lucaslizama','lucaslizama3@hotmail.com','$2a$12$lMXksoqsluzWdzGpPVkLS.e2OaOvWq34D8egF/Jw0TIbElpmHMdya');