-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (1,1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (2,1,'owner1','alumno');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (3,1,'vet1','profesor');

-- One profesor
INSERT INTO users(username,password,enabled) VALUES ('profesor1','pr0f3s0r',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (4,1,'profesor1','profesor');

-- PROFESORES
INSERT INTO profesor VALUES (1, 1, 'jorgedfez@gmail.com', 'Jorge', 'Fernandez', '6085551023', 'profesor1');

-- ANUNCIOS
INSERT INTO anuncio VALUES (1, 1, 'Fisica', 'Imparto clases de fisica',13.5, 'Fisica',  1);

--INSERT INTO anuncio(titulo,descripcion,asignatura,precio,profesor_id) VALUES ('Matematicas', 'Imparto clases de matematicas de un nivel', 'Matematicas', 10.5,  1);
--INSERT INTO anuncio VALUES (3, 1, 'Tecnología', 'Imparto clases de tecnología',10.5, 'Tecnología',  1);



