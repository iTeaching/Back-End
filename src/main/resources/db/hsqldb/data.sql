-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (1,1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('alumno','alumno1',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (2,1,'alumno','alumno');

-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('alumno2','alumno2',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (4,1,'alumno2','alumno');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('prof','prof1',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (3,1,'prof','profesor');

INSERT INTO profesor VALUES (1,1,'profesormolon@gmail.com','profesor', 'molon','666111333', 'prof');
INSERT INTO alumno VALUES (1,1,'alumnomolon@gmail.com','alumno', 'molon','666111334', 1, 'alumno');

INSERT INTO salas VALUES (1,1,'sala1',1);

INSERT INTO anuncio VALUES (1,1,'Física', 'Clases muy baratas','15','Clases de fisica', 1)

-- Un profesor y un alumno
INSERT INTO profesor(id,version,email,first_name,last_name,telephone,username) VALUES (1,1,'vet1@gmail.com','Pedro','Baños',648264572,'vet1');
INSERT INTO alumno(id,version,email,first_name,last_name,telephone,username,profesores_id) VALUES (1,1,'owner1@gmail.com','Juan','Fernandez',678222534,'owner1',1);
INSERT INTO alumno(id,version,email,first_name,last_name,telephone,username,profesores_id) VALUES (2,1,'owner2@gmail.com','Alonso','Ferraro',698222534,'owner2',1);

INSERT INTO anuncio(id,version,asignatura,descripcion,precio,titulo,profesor_id) VALUES (1,1,'Fisica','Clases de fisica',12.99,'Clases de fisica',1);
--INSERT INTO anuncio(id,version,asignatura,descripcion,precio,titulo,profesor_id) VALUES (2,1,'Lengua','Clases de lengua',10.95,'Clases de lengua',1);