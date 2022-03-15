-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (1,1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (2,1,'owner1','alumno');
INSERT INTO users(username,password,enabled) VALUES ('owner2','0wn3r',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (3,1,'owner2','alumno');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (4,1,'vet1','profesor');
-- Un profesor y un alumno
INSERT INTO profesor(id,version,email,first_name,last_name,telephone,username) VALUES (1,1,'vet1@gmail.com','Pedro','Baños',648264572,'vet1');
INSERT INTO alumno(id,version,email,first_name,last_name,telephone,username,profesores_id) VALUES (1,1,'owner1@gmail.com','Juan','Fernandez',678222534,'owner1',1);
INSERT INTO alumno(id,version,email,first_name,last_name,telephone,username,profesores_id) VALUES (2,1,'owner2@gmail.com','Alonso','Ferraro',698222534,'owner2',1);

INSERT INTO anuncio(id,version,asignatura,descripcion,precio,titulo,profesor_id) VALUES (1,1,'Fisica','Clases de fisica',12.99,'Clases de fisica',1);
--INSERT INTO anuncio(id,version,asignatura,descripcion,precio,titulo,profesor_id) VALUES (2,1,'Lengua','Clases de lengua',10.95,'Clases de lengua',1);