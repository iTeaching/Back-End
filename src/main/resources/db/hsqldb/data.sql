--ADMIN
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
--INSERTAR ALUMNOS
INSERT INTO users(username,password,enabled) VALUES ('alumno1','alumno1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'alumno1','alumno');

INSERT INTO users(username,password,enabled) VALUES ('alumno2','alumno2',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'alumno2','alumno');

--INSERTAR PROFESORES
INSERT INTO users(username,password,enabled) VALUES ('prof1','prof1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'prof1','profesor');

INSERT INTO users(username,password,enabled) VALUES ('prof2','prof2',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'prof2','profesor');

--INSERTAR DATOS ALUMNOS Y PROFESORES
INSERT INTO profesor(id, email, first_name, last_name, telephone,division,puntuacion, username) VALUES (1,'profesormolon1@gmail.com','Roberto', 'Ruiz','666111333', '0','0','prof1');
INSERT INTO profesor(id, email, first_name, last_name, telephone, division,puntuacion,username) VALUES (2,'profesormolon2@gmail.com','Paco', 'Jemez','666111343','0','0', 'prof2');

INSERT INTO alumno(id, email, first_name, last_name, telephone, username) VALUES (1,'alumnomolon1@gmail.com','alumno1','molon1','666111334','alumno1');
INSERT INTO alumno(id, email, first_name, last_name, telephone, username) VALUES (2,'alumnomolon2@gmail.com','alumno2','molon2','666111335','alumno2');

--INSERTAR ASIGNATURAS

INSERT INTO asignatura(id, nombre, url, profesor, titulo_anuncio, descripcion, precio) VALUES (1,'Lengua','https://acme.whereby.com/ff7e44c0-50f5-4788-8dec-5d65e172ad2c',1,'Se ofertan clases de Lengua','descripcion del anuncio',15);
INSERT INTO asignatura(id, nombre, url, profesor, titulo_anuncio, descripcion, precio) VALUES (2,'Matematicas','https://acme.whereby.com/7917a31c-dfbe-4aed-a939-125d3ba5acfd',1,'Clases de matematicas baratas','descripcion del anuncio',17);
INSERT INTO asignatura(id, nombre, url, profesor, titulo_anuncio, descripcion, precio) VALUES (3,'Historia','https://acme.whereby.com/b081ceab-92c7-434e-b851-94a827e490a6',2,'Clases de calidad','descripcion del anuncio',14);
INSERT INTO asignatura(id, nombre, url, profesor, titulo_anuncio, descripcion, precio) VALUES (4,'Fisica','https://acme.whereby.com/0702a4a5-1bd8-49bf-9ede-59b9eca930d6',2,'Aprende fisica como nunca','descripcion del anuncio',16);
INSERT INTO asignatura(id, nombre, url, profesor, titulo_anuncio, descripcion, precio) VALUES (5,'Plastica','https://acme.whereby.com/d050a3cc-b3c5-4859-af38-81bf0898b5b4',2,'Clases de musica','descripcion del anuncio',12);


--INSERTAR ALUMNOS A ASIGNATURAS
INSERT INTO asignatura_alumno(sala_id, alumno_id) VALUES (1,1);
INSERT INTO asignatura_alumno(sala_id, alumno_id) VALUES (1,2);
INSERT INTO asignatura_alumno(sala_id, alumno_id) VALUES (2,1);
INSERT INTO asignatura_alumno(sala_id, alumno_id) VALUES (3,2);
