insert into programas(codigo,programa) values('111','civil');
insert into programas(codigo,programa) values('115','sistemas');
insert into tipos_documento(id,tipo_documento) values(1,'cc');
insert into tipos_documento(id,tipo_documento) values(2,'ti');
insert into tipos_persona(id,tipo_persona) values(1,'estudiante');
insert into tipos_persona(id,tipo_persona) values(2,'docente');
insert into tipos_persona(id,tipo_persona) values(3,'administrativo');
insert into tipos_persona(id,tipo_persona) values(4,'externo');
insert into personas (id,direccion,email,numero_documento,password,primer_apellido,primer_nombre,segundo_apellido,segundo_nombre,telefono,id_tipo_persona,id_tipo_documento) values (1,'atalaya','jhocel@gmail.com','10905','jhocelpass','suescun','jhocel','duvan','torres','32102',1,1);
insert into personas (id,direccion,email,numero_documento,password,primer_apellido,primer_nombre,segundo_apellido,segundo_nombre,telefono,id_tipo_persona,id_tipo_documento) values (2,'limonar','javier@gmail.com','15154','javierpass','calderon','javier','eduardo','villamizar','3231',1,1);
insert into estudiantes (consecutivo,id_persona,codigo_programa) values ('1241',1,'115');
insert into estudiantes (consecutivo,id_persona,codigo_programa) values ('1229',2,'115');

