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

-- One profesor
INSERT INTO users(username,password,enabled) VALUES ('profesor1','pr0f3s0r',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (4,1,'profesor1','profesor');

-- PROFESORES
INSERT INTO profesor VALUES (1, 1, 'jorgedfez@gmail.com', 'Jorge', 'Fernandez', '6085551023', 'profesor1');

-- ANUNCIOS
--INSERT INTO anuncio VALUES (1, 1, 'Fisica', 'Imparto clases de fisica',13.5, 'Fisica',  1);

INSERT INTO anuncio VALUES (1,1,'Matematicas', 'Imparto clases de matematicas de un nivel',10.50, 'Matematicas',  1);

INSERT INTO anuncio VALUES (2,1,'Fisica', 'Imparto clases de fisica de un nivel',13.50, 'Fisica',  1);

--INSERT INTO anuncio(titulo,descripcion,asignatura,precio,profesor_id) VALUES ('Matematicas', 'Imparto clases de matematicas de un nivel', 'Matematicas', 10.5,  1);

--INSERT INTO anuncio(id,titulo,descripcion,asignatura,precio,profesor_id) VALUES (2,'Fisica', 'Imparto clases de fisica de un nivel', 'Fisica', 12.5,  2);

--INSERT INTO anuncio(titulo,descripcion,asignatura,precio,profesor_id) VALUES ('Matematiqwefqecas', 'Imparto clases de matemqwedqweaticas de un nivel', 'Matematwqedqicas', 10.5,  1);



