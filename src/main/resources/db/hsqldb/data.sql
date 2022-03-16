-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (1,1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('alumno','alumno1',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (2,1,'alumno','alumno');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('prof','prof1',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (3,1,'prof','profesor');

INSERT INTO profesor VALUES (1,1,'profesormolon@gmail.com','profesor', 'molon','666111333', 'prof');
INSERT INTO alumno VALUES (1,1,'alumnomolon@gmail.com','alumno', 'molon','666111334', 1, 'alumno');

INSERT INTO anuncio VALUES (1,1,'Física', 'Clases muy baratas','15','Clases de fisica', 1)