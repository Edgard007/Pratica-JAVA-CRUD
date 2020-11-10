create database valladarespalaciosjocsantadeo;
	use valladarespalaciosjocsantadeo;

create table facultad(
codigoFacultad int primary key auto_increment,
nombre varchar(50),
telefono varchar(50),
borradoLogico int);

create table carrera(
codigoCarrera int primary key auto_increment,
nombre varchar(50),
contidadMateria int,
codigoFacultad int,
borradoLogico int);

create table estudiante(
codigoEstudiante int primary key auto_increment,
nombre varchar(50),
edad int,
genero varchar(50),
cum double,
intereses varchar(50),
codigoCarrera int,
borradoLogico int);

create table usuarios
(
id int(11) not null primary key auto_increment,
usuario varchar(45) not null,
password varchar(45) not null,
nombre varchar(80) not null,
correo varchar(45) not null,
borradoLogico int,
id_tipo int(11)
);


create table tipo_usuario
(
id int(11) not null primary key auto_increment,
nombre varchar(45) not null
);

alter table carrera add constraint fk_carrera_facultad foreign key(codigoFacultad) references facultad(codigoFacultad);
alter table estudiante add constraint fk_estudiante_carrera foreign key (codigoCarrera) references carrera(codigoCarrera);

insert into facultad values(1,"ciencias Agronomicas","2233-4455",1);
insert into facultad values(2,"Ciencias Economicas","2355-8878",1);
insert into facultad values(3,"Ciencias Naturales","2387-9806",1);
insert into facultad values(4,"Humanidades","2043-7674",1);
insert into facultad values(5,"Ingenieria y Arquitectura","2838-6543",1);
insert into facultad values(6,"Jurisprudencia","2343-7543",1);
insert into facultad values(7,"Medicina","2143-5467",1);
insert into facultad values(8,"Quimiica y Farmacia","2143-6467",1);

insert into carrera values(1,"Ingenieria Agronomica",5,1,1);
insert into carrera values(2,"Licenciatura en Economia",3,2,1);
insert into carrera values(3,"Licenciatura en Fisica",8,3,1);
insert into carrera values(4,"Licenciatura en Matematicas",3,3,1);
insert into carrera values(5,"Licenciatura Psicologica",2,4,1);
insert into carrera values(6,"Ingenieria Civil",4,5,1);
insert into carrera values(7,"Doctorado en Medicina",1,7,1);
insert into carrera values(8,"Licenciatura en Ciencias Juridicas",2,6,1);
insert into carrera values(9,"Licenciatura en Quimica y Farmacia",1,8,1);

insert into tipo_usuario values(1,"Administrador");
insert into tipo_usuario values(2,"Visitante");


insert into estudiante values(1,"Edgardo Palacios",21,"masculino",8.1,"musica,leer",7,1);
insert into estudiante values(2,"Denis Valladares",20,"masculino",8.9,"musica,comer",3,1);
insert into estudiante values(3,"Nelson Vasquez",20,"masculino",9.1,"musica,leer",4,1);
insert into estudiante values(4,"Josefa Escobar",21,"femenino",7.6,"musica,comer",1,1);
insert into estudiante values(5,"Lizbeth Lopez",19,"femenino",8.3,"comer,leer",6,1);
insert into estudiante values(6,"Daniela Ramirez",16,"femenino",7.7,"musica,leer,comer",2,1);
insert into estudiante values(7,"Pablo Escobar",23,"masculino",9.7,"musica,bailar",5,1);
insert into estudiante values(8,"Juan Perez",19,"masculino",9.0,"bailar,comer",8,1);

insert into usuarios values(1,"admin",Sha1("12345"),"Administrador","admin@mail.com",1,1);
insert into usuarios values(2,"edgard",Sha1("123456"),"Edgardo","edgard@mail.com",1,2);
insert into usuarios values(3,"denis",Sha1("123456"),"Denis","denis@mail.com",1,2);


select * from estudiante




select * from estudiante order by Cum desc limit 1

Select*from estudiante where edad>=18

select * from estudiante where codigoEstudiante=1

select * from carrera order by contidadMateria desc limit 1


SELECT estudiante.codigoEstudiante, estudiante.nombre,
                    estudiante.edad, estudiante.genero, estudiante.cum,
                    estudiante.intereses, carrera.nombre AS carrera, facultad.nombre AS facultad
                    FROM estudiante JOIN carrera
                    ON estudiante.codigoCarrera = carrera.codigoCarrera
                    JOIN facultad ON carrera.codigoFacultad = facultad.codigoFacultad
                   where facultad.nombre="Jurisprudencia"



                    