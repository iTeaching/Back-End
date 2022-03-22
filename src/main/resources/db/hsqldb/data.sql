--ADMIN
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,version,username,authority) VALUES (1,1,'admin1','admin');

