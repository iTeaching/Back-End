--ADMIN
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (1,1,'admin1','admin');
--INSERTAR ALUMNOS
INSERT INTO users(username,password,enabled) VALUES ('alumno1','alumno1',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (2,1,'alumno1','alumno');

INSERT INTO users(username,password,enabled) VALUES ('alumno2','alumno2',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (4,1,'alumno2','alumno');

--INSERTAR PROFESORES
INSERT INTO users(username,password,enabled) VALUES ('prof1','prof1',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (3,1,'prof1','profesor');

INSERT INTO users(username,password,enabled) VALUES ('prof2','prof2',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (5,1,'prof2','profesor');

--INSERTAR DATOS ALUMNOS Y PROFESORES
INSERT INTO profesor(id, version, email, first_name, last_name, telephone, username) VALUES (1,1,'profesormolon1@gmail.com','profesor1', 'molon1','666111333', 'prof1');
INSERT INTO profesor(id, version, email, first_name, last_name, telephone, username) VALUES (2,1,'profesormolon2@gmail.com','profesor2', 'molon2','666111343', 'prof2');

INSERT INTO alumno(id, version, email, first_name, last_name, telephone, username) VALUES (1,1,'alumnomolon1@gmail.com','alumno1','molon1','666111334','alumno1');
INSERT INTO alumno(id, version, email, first_name, last_name, telephone, username) VALUES (2,1,'alumnomolon2@gmail.com','alumno2','molon2','666111335','alumno2');

--INSERTAR SALAS
INSERT INTO sala(id, version, nombre, url, profesor) VALUES (1,1,'Sala1','https://acme.whereby.com/663e69ba-9374-44b8-92e1-2d8ba28dab21',1);
INSERT INTO sala(id, version, nombre, url, profesor) VALUES (2,1,'Sala2','https://acme.whereby.com/5fe20d98-e1c1-43b8-98d1-c914c7424572',1);
INSERT INTO sala(id, version, nombre, url, profesor) VALUES (3,1,'Sala3','https://acme.whereby.com/5ae44241-f173-4d66-bc96-cf058e0ca07a',2);

--INSERTAR ALUMNOS A SALAS
INSERT INTO sala_alumno(sala_id, alumno_id) VALUES (1,1);
INSERT INTO sala_alumno(sala_id, alumno_id) VALUES (1,2);
INSERT INTO sala_alumno(sala_id, alumno_id) VALUES (2,1);
INSERT INTO sala_alumno(sala_id, alumno_id) VALUES (3,2);

--INSERTAR ANUNCIOS
INSERT INTO anuncio(id, version, asignatura, descripcion, precio, titulo, profesor_id) VALUES (1,1,'Lengua', 'Clases muy baratas','15','Clases de lengua', 1);
INSERT INTO anuncio(id, version, asignatura, descripcion, precio, titulo, profesor_id) VALUES (2,1,'Física', 'Clases de calidad','17','Clases de física', 2);
