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

--INSERTAR SALAS

INSERT INTO sala(id, nombre, url, profesor) VALUES (1,'Sala1','https://acme.whereby.com/008509eb-3d6a-43ce-bd86-2712cc02c40b',1);
INSERT INTO sala(id, nombre, url, profesor) VALUES (2,'Sala2','https://acme.whereby.com/63e93f87-f406-47ed-8a47-628bfabd7757',1);
INSERT INTO sala(id, nombre, url, profesor) VALUES (3,'Sala3','https://acme.whereby.com/e8bbb974-fe56-497c-9159-27337fa89617',2);
INSERT INTO sala(id, nombre, url, profesor) VALUES (4,'Sala4','https://acme.whereby.com/abf6eb78-0007-4110-a900-184da12b000f',2);


--INSERTAR ALUMNOS A SALAS
INSERT INTO sala_alumno(sala_id, alumno_id) VALUES (1,1);
INSERT INTO sala_alumno(sala_id, alumno_id) VALUES (1,2);
INSERT INTO sala_alumno(sala_id, alumno_id) VALUES (2,1);
INSERT INTO sala_alumno(sala_id, alumno_id) VALUES (3,2);

--INSERTAR ANUNCIOS
INSERT INTO anuncio(id, asignatura, descripcion, precio, titulo, profesor_id) VALUES (1,'Lengua', 'Clases muy baratas','15','Clases de lengua', 1);
INSERT INTO anuncio(id, asignatura, descripcion, precio, titulo, profesor_id) VALUES (2,'Física', 'Clases de calidad','17','Clases de física', 2);
