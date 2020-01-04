insert into programas(codigo,programa) values('111','civil');
insert into programas(codigo,programa) values('115','sistemas');
insert into tipos_documento(id,tipo_documento) values(1,'cc');
insert into tipos_documento(id,tipo_documento) values(2,'ti');
insert into tipos_persona(id,tipo_persona) values(1,'Estudiante');
insert into tipos_persona(id,tipo_persona) values(2,'Docente');
insert into tipos_persona(id,tipo_persona) values(3,'Administrativo');
insert into tipos_persona(id,tipo_persona) values(4,'Externo');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled) values (1,'atalaya','jhocel@gmail.com','10905','jhocel','duvan','suescun','torres','32102',1,1,'jhocel','$2a$10$n8.oDbyejLtZAcpKKRSsfuUSGMJ6LZ.qNmTURpganDyTZ.s6F.vkm',1);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled) values (2,'limonar','javier@gmail.com','15154','javier','eduardo','calderon','villamizar','3231',1,1,'javier','$2a$10$aMefgC8nnLjkzHfYjTjA6eKpdjqhVOlVczgvtJqAD0EUFaWO3U0JO',1);
insert into estudiantes (consecutivo,id_persona,codigo_programa) values ('1241',1,'115');
insert into estudiantes (consecutivo,id_persona,codigo_programa) values ('1229',2,'115');
insert into roles(id,authority,id_persona) values(1,'ROLE_ADMIN',1);
insert into roles(id,authority,id_persona) values(2,'ROLE_USER',1);
insert into roles(id,authority,id_persona) values(3,'ROLE_USER',2);

