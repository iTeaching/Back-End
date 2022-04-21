--ADMIN
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
--INSERTAR ALUMNOS
INSERT INTO users(username,password,enabled) VALUES ('pepeperez','clave',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'pepeperez','alumno');

INSERT INTO users(username,password,enabled) VALUES ('manolofuentes','clave',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'manolofuentes','alumno');

--INSERTAR PROFESORES
INSERT INTO users(username,password,enabled) VALUES ('gonzalodiz','clave',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'gonzalodiz','profesor');

INSERT INTO users(username,password,enabled) VALUES ('javitorres','clave',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'javitorres','profesor');

--INSERTAR DATOS ALUMNOS Y PROFESORES
INSERT INTO profesor(id, email, first_name, last_name, telephone,division,puntuacion, username) VALUES (1,'gonzalodiz@gmail.com','Gonzalo', 'Diz','666111333', '0','0','gonzalodiz');
INSERT INTO profesor(id, email, first_name, last_name, telephone, division,puntuacion,username) VALUES (2,'javitorres@gmail.com','Javi', 'Torres','666111343','0','0', 'javitorres');

INSERT INTO alumno(id, email, first_name, last_name, telephone, username) VALUES (1,'pepeperez@gmail.com','Pepe','P&eacute;rez','666111334','pepeperez');
INSERT INTO alumno(id, email, first_name, last_name, telephone, username) VALUES (2,'manolofuentes@gmail.com','Manolo','Fuentes','666111335','manolofuentes');

--INSERTAR ASIGNATURAS

INSERT INTO asignatura(id, nombre, url, profesor, titulo_anuncio, descripcion, precio) VALUES (1,'PSM','https://acme.whereby.com/ff7e44c0-50f5-4788-8dec-5d65e172ad2c',1,'Se ofertan clases de PSM','Las clases se imparten jueves y viernes de 12:30 a 14:30',15);
INSERT INTO asignatura(id, nombre, url, profesor, titulo_anuncio, descripcion, precio) VALUES (2,'LI','https://acme.whereby.com/7917a31c-dfbe-4aed-a939-125d3ba5acfd',1,'Clases de LI baratas','Todos los lunes tendremos clases a las 18:00',17);
INSERT INTO asignatura(id, nombre, url, profesor, titulo_anuncio, descripcion, precio) VALUES (3,'ASR','https://acme.whereby.com/b081ceab-92c7-434e-b851-94a827e490a6',2,'Clases de calidad','Horario: L y X a las 20:00',14);
INSERT INTO asignatura(id, nombre, url, profesor, titulo_anuncio, descripcion, precio) VALUES (4,'FFI','https://acme.whereby.com/0702a4a5-1bd8-49bf-9ede-59b9eca930d6',2,'Aprende FFI como nunca','Contactar mediante el chat para acordar los horarios!',16);
INSERT INTO asignatura(id, nombre, url, profesor, titulo_anuncio, descripcion, precio) VALUES (5,'CED','https://acme.whereby.com/d050a3cc-b3c5-4859-af38-81bf0898b5b4',2,'Clases muy eficientes','Os espero los martes a las 16:30',12);


--INSERTAR ALUMNOS A ASIGNATURAS
INSERT INTO asignatura_alumno(sala_id, alumno_id) VALUES (1,1);
INSERT INTO asignatura_alumno(sala_id, alumno_id) VALUES (1,2);
INSERT INTO asignatura_alumno(sala_id, alumno_id) VALUES (2,1);
INSERT INTO asignatura_alumno(sala_id, alumno_id) VALUES (3,2);
INSERT INTO asignatura_alumno(sala_id, alumno_id) VALUES (3,1);

INSERT INTO clase(id, hora_comienzo, hora_fin, alumno, profesor, asignatura, estadoClase) VALUES (1, '10', '11', 1, 1, 1, 'solicitada');

INSERT INTO valoracion(id, puntuacion, comentario, profesor_id, asignatura_id, alumno_id) VALUES (1, 5.00, 'Muy bueno', 1, 1, 1);