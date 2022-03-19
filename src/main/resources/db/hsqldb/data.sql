--ADMIN
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (1,1,'admin1','admin');
--INSERTAR ALUMNOS
INSERT INTO users(username,password,enabled) VALUES ('alumno','alumno1',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (2,1,'alumno','alumno');

INSERT INTO users(username,password,enabled) VALUES ('alumno2','alumno2',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (4,1,'alumno2','alumno');

--INSERTAR PROFESORES
INSERT INTO users(username,password,enabled) VALUES ('prof','prof1',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (3,1,'prof','profesor');

INSERT INTO users(username,password,enabled) VALUES ('profes','prof1',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (5,1,'profes','profesor');
--INSERTAR DATOS ALUMNOS Y PROFESORES
INSERT INTO profesor VALUES (1,1,'profesormolon@gmail.com','profesor', 'molon','666111333', 'prof');
INSERT INTO alumno VALUES (1,1,'alumnomolon@gmail.com','alumno', 'molon','666111334', 1, 'alumno');
--INSERTAR SALAS
INSERT INTO salas VALUES (1,1,'sala1','https://acme.whereby.com/a05d837d-2aaf-47a2-abf2-21dc8121f1c7',1);
--INSERTAR ANUNCIOS
INSERT INTO anuncio VALUES (1,1,'Lengua', 'Clases muy baratas','15','Clases de lengua', 1);

