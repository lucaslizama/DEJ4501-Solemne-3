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
    nombre_puerto varchar(20) not null,
    PRIMARY KEY(id)
);

-- Tabla puertoDestino--
CREATE TABLE puertoDestino(
    id int auto_increment,
    numero_puerto int not null,
    nombre_puerto varchar(20) not null,
    PRIMARY KEY(id)
);

-- Tabla barco--
CREATE TABLE barco(
    id int auto_increment,
    patente varchar(20) not null,
    nombre_barco varchar(20) not null,
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
    fecha_desenbarque date,
    numero_pasajeros int not null,
    valor_pasaje int not null,
    PRIMARY KEY(id),
    FOREIGN KEY(id_usuario) REFERENCES usuario(id),
    foreign key(id_origen) references puertoOrigen(id),
    foreign key(id_destino) references puertoDestino(id),
    foreign key(id_barco) references barco(id),
    foreign key(id_habitacion) references habitacion(id),
    foreign key(id_forma_pago) references formaPago(id)
);

-- Tabla pasaje--
CREATE TABLE pasaje(
    id int auto_increment,
    id_compra int not null,
    PRIMARY KEY(id),
    FOREIGN KEY(id_compra) REFERENCES compra(id)
);

-- Insertar datos necesarios--
insert into rolusuario (nombre_rol) values ('Estandar');
insert into rolusuario (nombre_rol) values ('Administrador');

-- Insertar datos barcos--
insert into barco(patente,nombre_barco) values ('ABCD1234', 'holi');
insert into barco(patente,nombre_barco) values ('MVNT9574', 'holi2');
insert into barco(patente,nombre_barco) values ('PEJT5154', 'holi3');
insert into barco(patente,nombre_barco) values ('IDJE7651', 'holi4');

-- Insertar datos formas de pago--
insert into formaPago(nombre) values ('WebPay');
insert into formaPago(nombre) values ('Credito');
insert into formaPago(nombre) values ('Paypal');