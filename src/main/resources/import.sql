insert into generos(id,genero,descripcion) values (1,'Masculino','Hombre');
insert into generos(id,genero,descripcion) values (2,'Femenino','Mujer');
insert into estados_civiles(id,estado_civil) values (1,'Soltero(a)');
insert into estados_civiles(id,estado_civil) values (2,'Casado(a)');
insert into estados_civiles(id,estado_civil) values (3,'Divorciado(a)');
insert into estados_civiles(id,estado_civil) values (4,'Viudo(a)');
insert into estados_civiles(id,estado_civil) values (5,'Unión Libre');
insert into estados_civiles(id,estado_civil) values (6,'Religioso(a)');
insert into estados_civiles(id,estado_civil) values (8,'Separado(a)');
insert into facultades(id,facultad) values (1,'Ciencias Agrarias y del Ambiente');
insert into facultades(id,facultad) values (2,'Ciencias Básicas');
insert into facultades(id,facultad) values (3,'Ciencias Empresariales');
insert into facultades(id,facultad) values (4,'Ciencias de la Salud');
insert into facultades(id,facultad) values (5,'Educación, Artes y Humanidades');
insert into facultades(id,facultad) values (6,'Ingeniería');
insert into departamentos(id,departamento,id_facultad) values (1,'Sistemas e Informática',6);
insert into departamentos(id,departamento,id_facultad) values (2,'Pedagogía, Andragogía, Comunicación y Multimedios',5);
insert into departamentos(id,departamento,id_facultad) values (3,'Humanidades, Sociales e Idiomas',5);
insert into departamentos(id,departamento,id_facultad) values (4,'Atención Clínica y Rehabilitación',4);
insert into departamentos(id,departamento,id_facultad) values (5,'Promoción, Protección',4);
insert into departamentos(id,departamento,id_facultad) values (6,'Fluidos y Térrmicas',6);
insert into departamentos(id,departamento,id_facultad) values (7,'Construcciones Civiles',6);
insert into departamentos(id,departamento,id_facultad) values (8,'Matemáticas y Estadística',2);
insert into departamentos(id,departamento,id_facultad) values (9,'Física',2);
insert into departamentos(id,departamento,id_facultad) values (10,'Biología',2);
insert into departamentos(id,departamento,id_facultad) values (11,'Química',2);
insert into departamentos(id,departamento,id_facultad) values (12,'Electricidad y Electrónica',6);
insert into departamentos(id,departamento,id_facultad) values (13,'Medio Ambiente y Planes de Estudio de Ing. ambiental e Ing. Agroindustrial',1);
insert into departamentos(id,departamento,id_facultad) values (14,'Ciencias Agrícolas y Pecuarias y Programa Ing. Pecuaria',1);
insert into departamentos(id,departamento,id_facultad) values (15,'Ciencias Contables y Programa de Contaduría Pública',3);
insert into departamentos(id,departamento,id_facultad) values (16,'Ciencias Administrativas y Plan de Estudios de Administración de Empresas',3);

insert into tipos_documento(id,tipo_documento,descripcion) values(1,'CC','Cedula Ciudadania');
insert into tipos_documento(id,tipo_documento,descripcion) values(2,'DE','Documento de Identidad Extranjera');
insert into tipos_documento(id,tipo_documento,descripcion) values(3,'CE','Cedula de Extranjeria');
insert into tipos_documento(id,tipo_documento,descripcion) values(4,'TI','Tarjeta de Identidad');
insert into tipos_documento(id,tipo_documento,descripcion) values(5,'PS','Pasaporte');
insert into tipos_documento(id,tipo_documento,descripcion) values(6,'CA','Certificado Cabildo');
insert into tipos_persona(id,tipo_persona) values(1,'Estudiante');
insert into tipos_persona(id,tipo_persona) values(2,'Docente');
insert into tipos_persona(id,tipo_persona) values(3,'Administrativo');
insert into tipos_persona(id,tipo_persona) values(4,'Graduado');
insert into tipos_persona(id,tipo_persona) values(5,'Externo');


insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (1,'atalaya','jhocelsuescun199746@gmail.com','10905015','Jhocel','Duvan','Suescun','Torres','32184602',1,0,0,0,0,1,'jhocel','$2a$10$n8.oDbyejLtZAcpKKRSsfuUSGMJ6LZ.qNmTURpganDyTZ.s6F.vkm',1,'170','54','54001','1997-01-03','2015-01-06',1,1,'1','1608851065549');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (2,'limonar','irgentorresm@gmail.com','1515436','Javier','Eduardo','Calderon','Villamizar','32318485',1,0,0,0,0,1,'javier','$2a$10$aMefgC8nnLjkzHfYjTjA6eKpdjqhVOlVczgvtJqAD0EUFaWO3U0JO',1,'170','54','54239','1948-10-22','1966-07-04',1,1,'1','1608851099799');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (3,'ceci','irmatom.02@gmail.com','1051869','Janes',null,'Duran','Sierra','31482102',1,0,0,0,0,1,'janes','$2a$10$nUMgvSKj1bmtQ.wmDGxu4uioN0GW16uzJUQ0A0aDl.nW4KjU2pPma',1,'170','54','54001','1997-01-03','2015-01-06',2,1,'1','1608851124501');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (4,'libertad','jhocelduvanst@ufps.edu.co','159847','Jose','Andres','Hernandez','Florez','31651231',1,0,0,0,0,1,'jose','$2a$10$JzE1av/GQv2Vfbg4UnN2.OIGgV.esym88yyGuAaiWKX/emJ6NSfCK',1,'170','54','54239','1948-10-22','1966-07-04',3,1,'1','1608851151098');

insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (5,'zulima','dumar.l.27@gmail.com','10694894','Dumar','Yeksel','Basto','Moreno','321515402',1,0,0,0,0,3,'dumar','$2a$10$NiEunU/JrCcRN3/eIsyJNOaeVBI9.H.5A62GVISEKA4YMCLiXZ0hW',1,'170','54','54001','1997-01-03','2015-01-06',5,1,'1','1608851176200');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (6,'lleras','rafael@gmail.com','10518498','Jose','Rafael','Cano','Pabon','32318794',1,0,0,0,0,1,'rafael','$2a$10$eY49DH.rQf5xKaB2MRU/4OE2uFeXM5sj1w90sTn9QsnC9/O6BtMAe',1,'170','54','54239','1948-10-22','1966-07-04',5,1,'1','1608851217367');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (7,'barrio blanco','maria@gmail.com','108484','Maria','Alejandra','Mendoza','Serrano','3148492102',1,0,0,0,0,2,'alejandra','$2a$10$GtFcCQ06SgmB4f5M9u.nbuVvHdeP2izJCld.uukBFwlB6uzsTLE.2',1,'170','54','54001','1997-01-03','2015-01-06',4,2,'1','1608851241658');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (8,'quinta oriental','fabian@gmail.com','1051484','Jeison','Fabian','Suarez','Ruiz','32321531',1,0,0,0,0,1,'fabian','$2a$10$bfsaFSDiZPsOgbjSEIR2guA9f1KSLVGt3UHxGL5F1J/LkANusberu',1,'170','54','54239','1948-10-22','1966-07-04',8,1,'1','1608851267567');

insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (10,'ceiba','auramoreno543@gmail.com','15845','Yhuver','Andrey','Quintero','NiÃ±o','32378741',0,0,0,1,0,3,'yhuver','$2a$10$agPJ4bVyDr9MrKFFn0IdcOzymOIWfkHDrThQrX5cbla7xcpAI/YIe',1,'170','54','54239','1948-10-22','1966-07-04',4,1,'1','1608851287377');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (11,'prados norte','carla@gmail.com','14854898','Carla','Elena','Ochoa','Florez','321156402',1,0,0,0,0,1,'carla','$2a$10$CjxUPn9nRlH1PxSKBWo9re10x/pke6u5pgsDKFdPgLF87uVJ9gNye',1,'170','54','54001','1997-01-03','2015-01-06',6,2,'1','1608851310468');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (12,'garcia herreros','deysi@gmail.com','18456','Deysi','Yuliet','Rincon','Medina','323654891',1,0,0,0,0,1,'deysi','$2a$10$g8Fg3YE0367m3oDfDDCB6u20qy8/m78momOy3lR8woM9lFNLuXcJa',1,'170','54','54239','1948-10-22','1966-07-04',2,2,'1','1608851335669');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (13,'motilones','daniela@gmail.com','1087454','Daniela',null,'Rojas','Bernal','321024184',1,0,0,0,0,1,'daniela','$2a$10$A.xBF7LRJIcXr8BW5qE/oe9mSA9Fqih5dip6B/OpT8rwFLc63Z.u2',1,'170','54','54001','1997-01-03','2015-01-06',4,2,'1','1608851358607');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (14,'prados del este','william@gmail.com','848456','William','David','Sierra','Lobo','32566531',1,0,0,0,0,2,'william','$2a$10$AaKEGvcbrrF5aGGBUpXKT.0tn.gy3KQeLhScczT4LDjD0s7oiWbCu',1,'170','54','54239','1948-10-22','1966-07-04',5,1,'1','1608851383657');

insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (16,'sayago','luis@gmail.com','151615','Luis','Alberto','Perez','Angarita','32387841',1,0,0,0,0,1,'luis','$2a$10$d7PEEWDt8ZaYpObqQuAYFeExmsQk.NtYsHrlc2kYCXaFL/LMHmn4m',1,'170','54','54239','1948-10-22','1966-07-04',8,1,'1','1608851420118');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (17,'san luis','karim@gmail.com','184561','Karim',null,'Mustafa','Hernandez','321054652',1,0,0,0,0,2,'karim','$2a$10$h7LkE9vpNQ3KUS0feNg7d.8rf4VarXB0dN6/hgy0Rr/2DUp5Yhd9u',1,'170','54','54001','1997-01-03','2015-01-06',4,1,'1','1608851451862');

insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (19,'kiosco','hamer@gmail.com','12185489','Hamer',null,'Castellanos','Fuentes','321548402',1,0,0,0,0,3,'hamer','$2a$10$4F.324L/xcPH0cZDQ.kG5.t5SDomJTWqEUvXuB2E0TTnEV09hI8O2',1,'170','54','54001','1997-01-03','2015-01-06',6,1,'1','1608851481558');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (20,'aeropuerto','karen@gmail.com','1034879','Karen','Lizeth','Ramos','Quintero','3231849',1,0,0,0,0,1,'karen','$2a$10$q8LJg09XvSEr1s5ePIu6COl52IYF/GrJVoePYzbl0z.8bBN7.0hTS',1,'170','54','54239','1948-10-22','1966-07-04',1,2,'1','1608851530429');

insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (21,'sdhfishfiks','jhocelsuescun199721@gmail.com','87984651','Marco','Antonio','Adarme','Jaimes','3157654',0,1,0,0,0,1,'marco','$2y$12$ZtRDeYhOkJ4CIVoHq6rXFe880TXfvPecO84ehzs6AWHqzqTCLACdG',1,'170','05','05001','2002-08-12','2020-06-21',8,1,'2','1608851585843');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (22,'hnfisdfiksd','jairofuentes@gmail.com','89787554','Jairo','Alberto','Fuentes','Camargo','354156481',0,1,0,0,0,1,'jairo','$2y$12$m2ZNAm7Y/AhZX6L26ysWpuEZiICqgbNVnNYfWLwyCwY2jJNiKLst2',1,'170','68','68001','1995-04-17','2013-03-07',2,1,'2','1608851606871');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (23,'safhiahfhian','jhocelsuescun199764@gmail.com','78968754','Oscar','Alberto','Gallardo','Perez','3465455464',0,1,0,0,0,1,'oscar','$2y$12$.0WE21UcXFmZ0v86bicaN.YWOlLZrH5MrTinJS5AKmUhyuQ/vSlQW',1,'408',null,null,'1985-11-04','2003-10-17',4,1,'2','1608851664732');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (24,'uhsfuahn','jairo@gmail.com','89484854','Jairo','Wilgberto','Cely','NiÃ±o','31587654',0,1,0,0,0,1,'cely','$2a$10$VBK3Yrn/JMIJ6.wqJBtdI.Y203/8s99N3vMkphI8BXzVK6d60rv0i',1,'170','05','05001','2002-08-12','2020-06-21',8,1,'2','1608851684604');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (25,'hsifais','pilar@gmail.com','847854','Judith','del Pilar','Rodriguez','Tenjo','3154887',0,1,0,0,0,1,'pilar','$2a$10$KdwIuJc.yZimQMiE/6VrEOqUry3QFC/BV3e1f3JapsHaG.BbtB/Ru',1,'170','05','05001','2000-04-17','2020-01-19',3,2,'2','1608851704923');

insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (26,'safuabujs','mariela@gmail.com','6987418754','Lina','Mariela','Ardila','Marin','348455464',0,1,0,0,0,1,'mariela','$2a$10$5UoetcXYKzSUE8sZBQmlH.Xw7tx/zakSoNminmS33RQGt0t.r6GJe',1,'408',null,null,'1985-11-04','2003-10-17',6,2,'2','1608851734307');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (27,'shfuahsfish','olga@gmail.com','841584651','Maria','Olga','Caceres','Carvajal','321487654',0,1,0,0,0,1,'olga','$2a$10$PKiS04lPKqQyNiVLNrlZTOJFVOGRvWbmS1QONq1JR6IK3EGDF6AwO',1,'170','05','05001','2002-08-12','2020-06-21',5,2,'2','1608851750807');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (28,'ushfuahsbfu','jhocelsuescun1997325@gmail.com','874587554','Claudia','Yamile','Gomez','Llanez','354156481',0,1,0,0,0,1,'claudia','$2a$10$n2KzljRmeJTPXupW/dJ2IOwIr.Ma5JZrwQ/2GXwmhGzsYNP3ApiYG',1,'170','68','68001','1995-04-17','2013-03-07',2,2,'2','1608851790516');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (29,'fdsiuahfi','jessica@gmail.com','98768754','Jessica','Lorena','Leal','Pabon','3514455464',0,1,0,0,0,1,'jessica','$2a$10$c5kwuKK0LMze/iqB8op3e.nRvZM7G8PobQ/IfMJsdhOfYhZ8ut2jm',1,'408',null,null,'1985-11-04','2003-10-17',4,2,'2','1608851812624');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (30,'hfuashfuish','hugo@gmail.com','84184651','Hugo','Enrique','Maldonado',null,'37887654',0,1,0,0,0,1,'hugo','$2a$10$4unT6JVFsq1iwTZjWYUudOrV3xl1wrzZs1fMcgzPHzTnL0ITSQ6Su',1,'170','05','05001','2002-08-12','2020-06-21',3,1,'2','1608851830313');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (31,'sfhsuifhu','ludwig@gmail.com','8987554','Ludwig','Enrique','Sierra','Higuera','398156481',0,1,0,0,0,1,'ludwig','$2a$10$m/0/mfDlg7d5FBSbOjLTtO.PHFeGWcQ.ePoilr1rg8vm11jsLQclK',1,'170','68','68001','1995-04-17','2013-03-07',2,1,'2','1608851856461');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (32,'hdfosuhguihdf','rene@gmail.com','6468754','Carlos','RenÃ©','Angarita','Sanguino','3487455464',0,1,0,0,0,1,'rene','$2a$10$h42757GiQwiY8ljRUHXfAOJxx1.gxCbR3dhMzawE8ciTcRif4k/ty',1,'408',null,null,'1985-11-04','2003-10-17',1,1,'2','1608851875646');


insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (33,'jndksnf','mariat@gmail.com','451','Maria','Teresa','Tovar','Arteaga','86545',0,0,1,0,0,1,'mariat','$2y$12$kZilElWv1J9L4vZnyZeo.e3.XpgCOXy9FC9TOzAN9kJ8QQdtqhI3a',1,'152',null,null,'1978-07-24','1990-11-12',2,2,'3','1608851894969');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (34,'dnkisndgks','jhocelsuescun1997@gmail.com','1561','Pedro','Jose','Llanos','Ortega','8754',0,0,1,0,0,1,'pedro','$2y$12$Pf.esTZF8K56QzyElVOPHeQHLLktyT9MPHF8FQ0sVkZBmkh3cVV/C',1,'192',null,null,'1994-05-10','2012-09-25',5,1,'3,4,5','1608851928232');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (35,'gjfgjdh','carlos@gmail.com','8645','Carlos','Antonio','Perez','Rodriguez','86545',0,0,0,0,1,1,'carlos','$2y$12$YDPFttCM69kWxKG5amI53ewmrgrDllKrOtMI8YNsKnlUwkOZQrgKa',1,'218',null,null,'1956-01-08','1970-12-10',8,1,'5','1608851955465');




insert into docentes (id_persona,codigo,id_departamento,estado) values (21,'05848',1,1);
insert into docentes (id_persona,codigo,id_departamento,estado) values (22,'08465',1,1);
insert into docentes (id_persona,codigo,id_departamento,estado) values (23,'01516',1,1);
insert into docentes (id_persona,codigo,id_departamento,estado) values (24,'05154',1,1);
insert into docentes (id_persona,codigo,id_departamento,estado) values (25,'03546',1,1);
insert into docentes (id_persona,codigo,id_departamento,estado) values (26,'84456',7,1);
insert into docentes (id_persona,codigo,id_departamento,estado) values (27,'51515',4,1);
insert into docentes (id_persona,codigo,id_departamento,estado) values (28,'14848',3,1);
insert into docentes (id_persona,codigo,id_departamento,estado) values (29,'14488',4,1);
insert into docentes (id_persona,codigo,id_departamento,estado) values (30,'84862',7,1);
insert into docentes (id_persona,codigo,id_departamento,estado) values (31,'65348',8,1);
insert into docentes (id_persona,codigo,id_departamento,estado) values (32,'52514',1,1);

insert into programas(id,codigo,programa,id_facultad,id_director) values(1,'111','Ingeniería Civil',6,23);
insert into programas(id,codigo,programa,id_facultad,id_director) values(2,'115','Ingeniería de Sistemas',6,28);
insert into programas(id,codigo,programa,id_facultad,id_director) values(3,'164','Ingeniería Agroindustrial',6,32);
insert into programas(id,codigo,programa,id_facultad,id_director) values(4,'150','Arquitectura',5,31);
insert into programas(id,codigo,programa,id_facultad,id_director) values(5,'126','Comercio Internacional',3,26);
insert into programas(id,codigo,programa,id_facultad,id_director) values(6,'122','Contaduría Pública',3,30);
insert into programas(id,codigo,programa,id_facultad,id_director) values(7,'180','Enfermería',4,22);
insert into programas(id,codigo,programa,id_facultad,id_director) values(8,'134','Trabajo Social',5,25);



insert into estudiantes (codigo,id_persona,id_programa) values ('1151241',1,2);
insert into estudiantes (codigo,id_persona,id_programa) values ('1151229',2,2);
insert into estudiantes (codigo,id_persona,id_programa) values ('1644552',3,3);
insert into estudiantes (codigo,id_persona,id_programa) values ('1501956',4,4);
insert into estudiantes (codigo,id_persona,id_programa) values ('1268465',5,5);
insert into estudiantes (codigo,id_persona,id_programa) values ('1222151',6,6);
insert into estudiantes (codigo,id_persona,id_programa) values ('1801845',7,7);
insert into estudiantes (codigo,id_persona,id_programa) values ('1344865',8,8);
insert into estudiantes (codigo,id_persona,id_programa) values ('1648941',11,3);
insert into estudiantes (codigo,id_persona,id_programa) values ('1506542',12,4);
insert into estudiantes (codigo,id_persona,id_programa) values ('1268796',13,5);
insert into estudiantes (codigo,id_persona,id_programa) values ('1223214',14,6);
insert into estudiantes (codigo,id_persona,id_programa) values ('1348978',16,8);
insert into estudiantes (codigo,id_persona,id_programa) values ('1111562',17,1);
insert into estudiantes (codigo,id_persona,id_programa) values ('1643546',19,3);
insert into estudiantes (codigo,id_persona,id_programa) values ('1508861',20,4);

insert into graduados (anio,id_persona,id_programa) values ('2015',10,2);

insert into administrativos (id_persona,cargo) values (33,'secretaria');
insert into administrativos (id_persona,cargo) values (34,'vicerector de extension');

insert into externos (id_persona,profesion) values (35,'ing de sistemas');

insert into roles (id, authority) values (1,'ROLE_SUPERADMIN');
insert into roles (id, authority) values (2,'ROLE_MANAECCU');
insert into roles (id, authority) values (3,'ROLE_MANPEOPLE');
insert into roles (id, authority) values (4,'ROLE_ATTENDANCE');
insert into roles (id, authority) values (5,'ROLE_USER');
insert into roles (id, authority) values (6,'ROLE_SNIES');

##roles superadmin
insert into personas_x_roles (id_persona, id_rol) values (34,1);
insert into personas_x_roles (id_persona, id_rol) values (34,5);

##rol gallardo-civil
insert into personas_x_roles (id_persona, id_rol) values (23,2);
insert into personas_x_roles (id_persona, id_rol) values (23,3);
insert into personas_x_roles (id_persona, id_rol) values (23,5);
insert into personas_x_roles (id_persona, id_rol) values (23,6);

insert into roles_personas_programas_ec (id_persona, id_rol, id_programa) values (23,2,1);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (23,3,1);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (23,3,3);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (23,3,4);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (23,3,5);
insert into rol_persona_programa_per (id_persona, id_rol, id_tipo_persona,id_programa) values (23,3,1,1);
insert into rol_persona_programa_per (id_persona, id_rol, id_tipo_persona,id_programa) values (23,3,4,1);


##rol claudia-sistemas
insert into personas_x_roles (id_persona, id_rol) values (28,2);
insert into personas_x_roles (id_persona, id_rol) values (28,3);
insert into personas_x_roles (id_persona, id_rol) values (28,5);
insert into personas_x_roles (id_persona, id_rol) values (28,6);

insert into roles_personas_programas_ec (id_persona, id_rol, id_programa) values (28,2,2);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (28,3,1);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (28,3,2);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (28,3,3);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (28,3,4);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (28,3,5);
insert into rol_persona_programa_per (id_persona, id_rol, id_tipo_persona,id_programa) values (28,3,1,2);
insert into rol_persona_programa_per (id_persona, id_rol, id_tipo_persona,id_programa) values (28,3,4,2);
insert into rol_persona_depto_per (id_persona, id_rol, id_tipo_persona,id_depto) values (28,3,2,4);
insert into rol_persona_depto_per (id_persona, id_rol, id_tipo_persona,id_depto) values (28,3,2,8);

##rol rene-agroindustrial
insert into personas_x_roles (id_persona, id_rol) values (32,2);
insert into personas_x_roles (id_persona, id_rol) values (32,3);
insert into personas_x_roles (id_persona, id_rol) values (32,5);
insert into personas_x_roles (id_persona, id_rol) values (32,6);

insert into roles_personas_programas_ec (id_persona, id_rol, id_programa) values (32,2,3);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (32,3,1);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (32,3,3);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (32,3,4);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (32,3,5);
insert into rol_persona_programa_per (id_persona, id_rol, id_tipo_persona,id_programa) values (32,3,1,3);
insert into rol_persona_programa_per (id_persona, id_rol, id_tipo_persona,id_programa) values (32,3,4,3);


##rol ludwing-arquitectura
insert into personas_x_roles (id_persona, id_rol) values (31,2);
insert into personas_x_roles (id_persona, id_rol) values (31,3);
insert into personas_x_roles (id_persona, id_rol) values (31,5);
insert into personas_x_roles (id_persona, id_rol) values (31,6);

insert into roles_personas_programas_ec (id_persona, id_rol, id_programa) values (31,2,4);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (31,3,1);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (31,3,3);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (31,3,4);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (31,3,5);
insert into rol_persona_programa_per (id_persona, id_rol, id_tipo_persona,id_programa) values (31,3,1,4);
insert into rol_persona_programa_per (id_persona, id_rol, id_tipo_persona,id_programa) values (31,3,4,4);

##rol lina mariela-comercio
insert into personas_x_roles (id_persona, id_rol) values (26,2);
insert into personas_x_roles (id_persona, id_rol) values (26,3);
insert into personas_x_roles (id_persona, id_rol) values (26,5);
insert into personas_x_roles (id_persona, id_rol) values (26,6);

insert into roles_personas_programas_ec (id_persona, id_rol, id_programa) values (26,2,5);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (26,3,1);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (26,3,3);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (26,3,4);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (26,3,5);
insert into rol_persona_programa_per (id_persona, id_rol, id_tipo_persona,id_programa) values (26,3,1,5);
insert into rol_persona_programa_per (id_persona, id_rol, id_tipo_persona,id_programa) values (26,3,4,5);


##rol maldonado-contaduria
insert into personas_x_roles (id_persona, id_rol) values (30,2);
insert into personas_x_roles (id_persona, id_rol) values (30,3);
insert into personas_x_roles (id_persona, id_rol) values (30,5);
insert into personas_x_roles (id_persona, id_rol) values (30,6);

insert into roles_personas_programas_ec (id_persona, id_rol, id_programa) values (30,2,6);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (30,3,1);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (30,3,3);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (30,3,4);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (30,3,5);
insert into rol_persona_programa_per (id_persona, id_rol, id_tipo_persona,id_programa) values (30,3,1,6);
insert into rol_persona_programa_per (id_persona, id_rol, id_tipo_persona,id_programa) values (30,3,4,6);


##rol fuentes-enfermeria
insert into personas_x_roles (id_persona, id_rol) values (22,2);
insert into personas_x_roles (id_persona, id_rol) values (22,3);
insert into personas_x_roles (id_persona, id_rol) values (22,5);
insert into personas_x_roles (id_persona, id_rol) values (30,6);

insert into roles_personas_programas_ec (id_persona, id_rol, id_programa) values (1,2,7);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (22,3,1);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (22,3,3);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (22,3,4);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (22,3,5);
insert into rol_persona_programa_per (id_persona, id_rol, id_tipo_persona,id_programa) values (22,3,1,7);
insert into rol_persona_programa_per (id_persona, id_rol, id_tipo_persona,id_programa) values (22,3,4,7);


##rol pilar-trabajo social
insert into personas_x_roles (id_persona, id_rol) values (25,2);
insert into personas_x_roles (id_persona, id_rol) values (25,3);
insert into personas_x_roles (id_persona, id_rol) values (25,5);
insert into personas_x_roles (id_persona, id_rol) values (25,6);

insert into roles_personas_programas_ec (id_persona, id_rol, id_programa) values (25,2,8);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (25,3,1);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (25,3,3);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (25,3,4);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (25,3,5);
insert into rol_persona_programa_per (id_persona, id_rol, id_tipo_persona,id_programa) values (25,3,1,8);
insert into rol_persona_programa_per (id_persona, id_rol, id_tipo_persona,id_programa) values (25,3,4,8);


insert into personas_x_roles (id_persona, id_rol) values (1,5);
insert into personas_x_roles (id_persona, id_rol) values (2,5);
insert into personas_x_roles (id_persona, id_rol) values (3,5);
insert into personas_x_roles (id_persona, id_rol) values (4,5);
insert into personas_x_roles (id_persona, id_rol) values (5,5);
insert into personas_x_roles (id_persona, id_rol) values (6,5);
insert into personas_x_roles (id_persona, id_rol) values (7,5);
insert into personas_x_roles (id_persona, id_rol) values (8,5);
insert into personas_x_roles (id_persona, id_rol) values (9,5);
insert into personas_x_roles (id_persona, id_rol) values (10,5);

insert into personas_x_roles (id_persona, id_rol) values (11,5);
insert into personas_x_roles (id_persona, id_rol) values (12,5);
insert into personas_x_roles (id_persona, id_rol) values (13,5);
insert into personas_x_roles (id_persona, id_rol) values (14,5);
insert into personas_x_roles (id_persona, id_rol) values (15,5);
insert into personas_x_roles (id_persona, id_rol) values (16,5);
insert into personas_x_roles (id_persona, id_rol) values (17,5);
insert into personas_x_roles (id_persona, id_rol) values (18,5);
insert into personas_x_roles (id_persona, id_rol) values (19,5);
insert into personas_x_roles (id_persona, id_rol) values (20,5);


insert into personas_x_roles (id_persona, id_rol) values (21,5);
insert into personas_x_roles (id_persona, id_rol) values (24,5);
insert into personas_x_roles (id_persona, id_rol) values (27,5);
insert into personas_x_roles (id_persona, id_rol) values (29,5);





insert into tipos_participante(id,tipo_participante) values(1,'Asistente');
insert into tipos_participante(id,tipo_participante) values(2,'Ponente');


insert into clasificacion_cine(id,clasificacion_cine) values(11,'Programas y certificaciones básicas');
insert into clasificacion_cine(id,clasificacion_cine) values(21,'Alfabetización y aritmética elemental');
insert into clasificacion_cine(id,clasificacion_cine) values(31,'Competencias personales y desarrollo');
insert into clasificacion_cine(id,clasificacion_cine) values(111,'Ciencias de la educación');
insert into clasificacion_cine(id,clasificacion_cine) values(112,'Formación para docentes de educación pre-primaría');
insert into clasificacion_cine(id,clasificacion_cine) values(113,'Formación para docentes sin asignatura de especialización');
insert into clasificacion_cine(id,clasificacion_cine) values(114,'Formación para docentes con asignatura de especialización');
insert into clasificacion_cine(id,clasificacion_cine) values(211,'Técnicas audiovisuales y producción para medios de comunicación');
insert into clasificacion_cine(id,clasificacion_cine) values(212,'Diseño industrial, de modas e interiores');
insert into clasificacion_cine(id,clasificacion_cine) values(213,'Bellas artes');
insert into clasificacion_cine(id,clasificacion_cine) values(214,'Artesanías');
insert into clasificacion_cine(id,clasificacion_cine) values(215,'Música y artes escénicas');
insert into clasificacion_cine(id,clasificacion_cine) values(221,'Religión y Teología');
insert into clasificacion_cine(id,clasificacion_cine) values(222,'Historia y arqueología');
insert into clasificacion_cine(id,clasificacion_cine) values(223,'Filosofía y Ética');
insert into clasificacion_cine(id,clasificacion_cine) values(231,'Adquisición del lenguaje');
insert into clasificacion_cine(id,clasificacion_cine) values(232,'Literatura y lingüística');
insert into clasificacion_cine(id,clasificacion_cine) values(311,'Economía');
insert into clasificacion_cine(id,clasificacion_cine) values(312,'Ciencias políticas y educación cívica');
insert into clasificacion_cine(id,clasificacion_cine) values(313,'Psicología');
insert into clasificacion_cine(id,clasificacion_cine) values(314,'Sociología y estudios culturales');
insert into clasificacion_cine(id,clasificacion_cine) values(321,'Periodismo y reportajes');
insert into clasificacion_cine(id,clasificacion_cine) values(322,'Bibliotecología, información y archivología');
insert into clasificacion_cine(id,clasificacion_cine) values(411,'Contabilidad e impuestos');
insert into clasificacion_cine(id,clasificacion_cine) values(412,'Gestión financiera, administración bancaria y seguros');
insert into clasificacion_cine(id,clasificacion_cine) values(413,'Gestión y administración');
insert into clasificacion_cine(id,clasificacion_cine) values(414,'Mercadotecnia y publicidad');
insert into clasificacion_cine(id,clasificacion_cine) values(415,'Secretariado y trabajo de oficina');
insert into clasificacion_cine(id,clasificacion_cine) values(416,'Ventas al por mayor y al por menor');
insert into clasificacion_cine(id,clasificacion_cine) values(417,'Competencias laborales');
insert into clasificacion_cine(id,clasificacion_cine) values(421,'Derecho');
insert into clasificacion_cine(id,clasificacion_cine) values(511,'Biología');
insert into clasificacion_cine(id,clasificacion_cine) values(512,'Bioquímica');
insert into clasificacion_cine(id,clasificacion_cine) values(521,'Ciencias del medio ambiente');
insert into clasificacion_cine(id,clasificacion_cine) values(522,'Medio ambiente natural y vida silvestre');
insert into clasificacion_cine(id,clasificacion_cine) values(531,'Química');
insert into clasificacion_cine(id,clasificacion_cine) values(532,'Ciencias de la tierra');
insert into clasificacion_cine(id,clasificacion_cine) values(533,'FÃ­sica');
insert into clasificacion_cine(id,clasificacion_cine) values(541,'Matemáticas');
insert into clasificacion_cine(id,clasificacion_cine) values(542,'Estadística');
insert into clasificacion_cine(id,clasificacion_cine) values(611,'Uso de computadores');
insert into clasificacion_cine(id,clasificacion_cine) values(612,'Diseño y administración de redes y bases de datos');
insert into clasificacion_cine(id,clasificacion_cine) values(613,'Desarrollo y anÃ¡lisis de software y aplicaciones');
insert into clasificacion_cine(id,clasificacion_cine) values(711,'Ingeniería y procesos químicos');
insert into clasificacion_cine(id,clasificacion_cine) values(712,'Tecnologíaa de protección del medio ambiente');
insert into clasificacion_cine(id,clasificacion_cine) values(713,'Electricidad y energía');
insert into clasificacion_cine(id,clasificacion_cine) values(714,'Electrónica y automatización');
insert into clasificacion_cine(id,clasificacion_cine) values(715,'Mecánica y profesiones afines a la metalistería');
insert into clasificacion_cine(id,clasificacion_cine) values(716,'Vehículos, barcos y aeronaves motorizadas');
insert into clasificacion_cine(id,clasificacion_cine) values(721,'Procesamiento de alimentos');
insert into clasificacion_cine(id,clasificacion_cine) values(722,'Materiales (vidrio, papel, plástico y madera)');
insert into clasificacion_cine(id,clasificacion_cine) values(723,'Producción textiles (ropa, calzado y artículos de cuero)');
insert into clasificacion_cine(id,clasificacion_cine) values(724,'Mineríaa y extracción');
insert into clasificacion_cine(id,clasificacion_cine) values(731,'Arquitectura y urbanismo');
insert into clasificacion_cine(id,clasificacion_cine) values(732,'Construcción e ingenieríaa civil');
insert into clasificacion_cine(id,clasificacion_cine) values(811,'Producción agrícola y ganadera');
insert into clasificacion_cine(id,clasificacion_cine) values(812,'Horticultura');
insert into clasificacion_cine(id,clasificacion_cine) values(821,'Silvicultura');
insert into clasificacion_cine(id,clasificacion_cine) values(831,'Pesca');
insert into clasificacion_cine(id,clasificacion_cine) values(841,'Veterinaria');
insert into clasificacion_cine(id,clasificacion_cine) values(911,'Odontología');
insert into clasificacion_cine(id,clasificacion_cine) values(912,'Medicina');
insert into clasificacion_cine(id,clasificacion_cine) values(913,'Enfermería y partería');
insert into clasificacion_cine(id,clasificacion_cine) values(914,'Tecnología de diagnóstico y tratamiento médico');
insert into clasificacion_cine(id,clasificacion_cine) values(915,'Terapia y rehabilitaciÃ³n');
insert into clasificacion_cine(id,clasificacion_cine) values(916,'Farmacia');
insert into clasificacion_cine(id,clasificacion_cine) values(917,'Medicina y terapia tradicional y complementaria');
insert into clasificacion_cine(id,clasificacion_cine) values(921,'Asistencia a adultos mayores y discapacitados');
insert into clasificacion_cine(id,clasificacion_cine) values(922,'Asistencia a la infancia y servicios para jóvenes');
insert into clasificacion_cine(id,clasificacion_cine) values(923,'Trabajo social y orientación');
insert into clasificacion_cine(id,clasificacion_cine) values(1011,'Servicios domésticos');
insert into clasificacion_cine(id,clasificacion_cine) values(1012,'Peluquería y tratamientos de belleza');
insert into clasificacion_cine(id,clasificacion_cine) values(1013,'Hotelería, restaurantes y servicios de banquetes');
insert into clasificacion_cine(id,clasificacion_cine) values(1014,'Deportes');
insert into clasificacion_cine(id,clasificacion_cine) values(1015,'Viajes, turismo y actividades recreativas');
insert into clasificacion_cine(id,clasificacion_cine) values(1021,'Saneamiento de la comunidad');
insert into clasificacion_cine(id,clasificacion_cine) values(1022,'Salud y protección laboral');
insert into clasificacion_cine(id,clasificacion_cine) values(1031,'Educación militar y de defensa');
insert into clasificacion_cine(id,clasificacion_cine) values(1032,'Protección de las personas y de la propiedad');
insert into clasificacion_cine(id,clasificacion_cine) values(1041,'Servicios de transporte');

insert into tipos_beneficiarios(id,tipo_beneficiario,id_tipo_persona,homologacion) values(1,'Estudiante de la IES', '1','Estudiantes');
insert into tipos_beneficiarios(id,tipo_beneficiario,id_tipo_persona,homologacion) values(2,'Graduado de la IES','4', 'Graduados');
insert into tipos_beneficiarios(id,tipo_beneficiario,id_tipo_persona,homologacion) values(3,'Profesor de la IES','2','Docentes');
insert into tipos_beneficiarios(id,tipo_beneficiario,id_tipo_persona,homologacion) values(4,'Administrativo de la IES','3','Administrativos');
insert into tipos_beneficiarios(id,tipo_beneficiario,id_tipo_persona,homologacion) values(5,'Persona no vinculada a la IES','5','Externos');

insert into tipos_educacion_continua(id,tipo_educacion_continua,estado_oficial) values(1,'Curso',1);
insert into tipos_educacion_continua(id,tipo_educacion_continua,estado_oficial) values(2,'Diplomado',1);
insert into tipos_educacion_continua(id,tipo_educacion_continua,estado_oficial) values(3,'Simposio',1);
insert into tipos_educacion_continua(id,tipo_educacion_continua,estado_oficial) values(4,'Taller',1);
insert into tipos_educacion_continua(id,tipo_educacion_continua,estado_oficial) values(5,'Seminario',1);
insert into tipos_educacion_continua(id,tipo_educacion_continua,estado_oficial) values(6,'Congreso',1);

insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo_inscripcion,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,lugar,estado,costo_educacion_continua,porcentaje_asistencia,id_acceso) values(1,'CIINATIC','2020-01-10','2020-01-15','2020-01-08','contenido generalll CIINATIC','10','BBBBB','30000','26',6,'files/uploads/educacion-continua/1/portada.jpg',28,1,'001',711,'Auditorio Biblioteca','Activo','252524',47,'1608777954324');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo_inscripcion,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,lugar,estado,costo_educacion_continua,porcentaje_asistencia,id_acceso) values(2,'EISI','2020-05-30','2020-06-10','2020-05-29','contenido generalll EISI','100','aaaa','52000','40',4,'files/uploads/educacion-continua/2/portada.jpg',27,8,'002',711,'Auditorio Eustorgio Colmenares Batista','Activo','56474',69,'1608778049390');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo_inscripcion,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,lugar,estado,costo_educacion_continua,porcentaje_asistencia,id_acceso) values(3,'SEMINARIO VIII ENCUENTRO SEMILLERO Y PROYECTO AULA','2020-02-25','2020-02-29','2020-02-24','contenido generalll de la bienal','10','jdihsig','20000','35',1,'files/uploads/educacion-continua/3/portada.png',29,3,'001',541,'Auditorio Postgrados','Activo','375474',36,'1608778068004');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo_inscripcion,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,lugar,estado,costo_educacion_continua,porcentaje_asistencia,id_acceso) values(4,'EISI2','2020-03-05','2020-03-10','2020-03-07','contenido generalll del eisi2','10','indgisng','34800','20',2,'files/uploads/educacion-continua/4/portada.jpg',30,6,'001',1021,'Auditorio Jorge Luis Acero JordÃ¡n','Activo','563573',54,'1608778102372');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo_inscripcion,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,lugar,estado,costo_educacion_continua,porcentaje_asistencia,id_acceso) values(5,'COMO INVESTIGAR CON DATOS ABIERTOS? GENERANDO CONOCIMIENTO CON DATOS ABIERTOS EN DIFERENTES PAGINAS','2020-04-20','2020-04-20','2020-04-18','contenido generalll de la bienal2','10','nsiengtisent','78647','25',3,'files/uploads/educacion-continua/5/portada.jpg',21,4,'001',521,'Hotel Casino Internacional','Activo','34634',64,'1608778117221');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo_inscripcion,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,lugar,estado,costo_educacion_continua,porcentaje_asistencia,id_acceso) values(6,'CIINATIC3','2020-05-12','2020-05-14','2020-05-13','contenido generalll del ciinatic3','10','isjign','640000','40',5,'files/uploads/educacion-continua/6/portada.jpg',22,2,'001',711,'Auditorio Eustorgio Colmenares Batista','Activo','57373',31,'1608778132097');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo_inscripcion,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,lugar,estado,costo_educacion_continua,porcentaje_asistencia,id_acceso) values(7,'RALLY MATEMATICO','2020-06-25','2020-06-25','2020-06-22','contenido generalll del rally','10','sjdisjgi','25500','60',6,'files/uploads/educacion-continua/7/portada.jpg',23,1,'002',923,'Auditorio Biblioteca','Activo','57373',20,'1608778145519');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo_inscripcion,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,lugar,estado,costo_educacion_continua,porcentaje_asistencia,id_acceso) values(8,'II IngenierÃ­a Civil','2020-07-10','2020-07-13','2020-07-09','contenido generalll del congreso de civil','10','sjgisigj','100000','70',1,'files/uploads/educacion-continua/8/portada.jpg',32,5,'002',412,'Hotel Bolivar Salon Principal','Activo','53737',15,'1608778162843');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo_inscripcion,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,lugar,estado,costo_educacion_continua,porcentaje_asistencia,id_acceso) values(9,'SEMINARIO X ENCUENTRO DE PROYECTOS DE SEMILLEROS DE INVESTIGACIÃ“N DE INGENIERÃ�A DE SISTEMAS','2020-07-25','2020-07-30','2020-07-20','contenido generalll de ciencias aplicadas','10','ijdsgijs','67800','10',2,'files/uploads/educacion-continua/9/portada.jpg',31,6,'002',711,'Auditorio J.J Maldonado 4 Piso SA','Activo','4357357',35,'1608778179292');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo_inscripcion,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,lugar,estado,costo_educacion_continua,porcentaje_asistencia,id_acceso) values(10,'Bienal3','2020-08-18','2020-09-21','2020-09-16','contenido generalll de la bienal3','10','ijdisjg','34500','36',3,'files/uploads/educacion-continua/10/portada.jpg',21,8,'003',417,'Auditorio Eustorgio Colmenares Batista','Activo','37373',10,'1608778195661');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo_inscripcion,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,lugar,estado,costo_educacion_continua,porcentaje_asistencia,id_acceso) values(11,'CIINATIC','2020-02-10','2020-03-15','2020-02-08','contenido generalll CIINATIC','10','BBBBB','30000','26',6,'files/uploads/educacion-continua/1/portada.jpg',28,1,'001',711,'Auditorio Biblioteca','Activo','252524',47,'1608778209461');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo_inscripcion,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,lugar,estado,costo_educacion_continua,porcentaje_asistencia,id_acceso) values(12,'EISI','2020-06-30','2020-07-10','2020-06-29','contenido generalll EISI','100','aaaa','52000','40',4,'files/uploads/educacion-continua/2/portada.jpg',27,8,'002',711,'Auditorio Eustorgio Colmenares Batista','Activo','56474',69,'1608778226538');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo_inscripcion,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,lugar,estado,costo_educacion_continua,porcentaje_asistencia,id_acceso) values(13,'SEMINARIO VIII ENCUENTRO SEMILLERO Y PROYECTO AULA','2020-04-25','2020-05-29','2020-05-24','contenido generalll de la bienal','10','jdihsig','20000','35',1,'files/uploads/educacion-continua/3/portada.png',29,3,'001',541,'Auditorio Postgrados','Activo','375474',36,'1608778249135');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo_inscripcion,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,lugar,estado,costo_educacion_continua,porcentaje_asistencia,id_acceso) values(14,'EISI2','2020-04-05','2020-05-10','2020-04-07','contenido generalll del eisi2','10','indgisng','34800','20',2,'files/uploads/educacion-continua/4/portada.jpg',30,6,'001',1021,'Auditorio Jorge Luis Acero JordÃ¡n','Activo','563573',54,'1608778268478');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo_inscripcion,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,lugar,estado,costo_educacion_continua,porcentaje_asistencia,id_acceso) values(15,'COMO INVESTIGAR CON DATOS ABIERTOS? GENERANDO CONOCIMIENTO CON DATOS ABIERTOS EN DIFERENTES PAGINAS','2020-05-20','2020-06-20','2020-06-18','contenido generalll de la bienal2','10','nsiengtisent','78647','25',3,'files/uploads/educacion-continua/5/portada.jpg',21,4,'001',521,'Hotel Casino Internacional','Activo','34634',64,'1608778387483');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo_inscripcion,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,lugar,estado,costo_educacion_continua,porcentaje_asistencia,id_acceso) values(16,'CIINATIC3','2020-06-12','2020-06-14','2020-06-13','contenido generalll del ciinatic3','10','isjign','640000','40',5,'files/uploads/educacion-continua/6/portada.jpg',22,2,'001',711,'Auditorio Eustorgio Colmenares Batista','Activo','57373',31,'1608778409920');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo_inscripcion,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,lugar,estado,costo_educacion_continua,porcentaje_asistencia,id_acceso) values(17,'RALLY MATEMATICO','2020-07-25','2020-07-25','2020-07-22','contenido generalll del rally','10','sjdisjgi','25500','60',6,'files/uploads/educacion-continua/7/portada.jpg',23,1,'002',923,'Auditorio Biblioteca','Activo','57373',20,'1608778424353');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo_inscripcion,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,lugar,estado,costo_educacion_continua,porcentaje_asistencia,id_acceso) values(18,'II IngenierÃ­a Civil','2020-08-10','2020-08-13','2020-08-09','contenido generalll del congreso de civil','10','sjgisigj','100000','70',1,'files/uploads/educacion-continua/8/portada.jpg',32,5,'002',412,'Hotel Bolivar Salon Principal','Activo','53737',15,'1608778444475');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo_inscripcion,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,lugar,estado,costo_educacion_continua,porcentaje_asistencia,id_acceso) values(19,'SEMINARIO X ENCUENTRO DE PROYECTOS DE SEMILLEROS DE INVESTIGACIÃ“N DE INGENIERÃ�A DE SISTEMAS','2020-08-25','2020-09-30','2020-08-20','contenido generalll de ciencias aplicadas','10','ijdsgijs','67800','10',2,'files/uploads/educacion-continua/9/portada.jpg',31,6,'002',711,'Auditorio J.J Maldonado 4 Piso SA','Activo','4357357',35,'1608778459636');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo_inscripcion,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,lugar,estado,costo_educacion_continua,porcentaje_asistencia,id_acceso) values(20,'Bienal3','2020-09-18','2020-10-21','2020-09-16','contenido generalll de la bienal3','10','ijdisjg','34500','36',3,'files/uploads/educacion-continua/10/portada.jpg',21,8,'003',417,'Auditorio Eustorgio Colmenares Batista','Activo','37373',10,'1608778471177');

insert into educacion_continua_tipo_beneficiario (id, id_educacion_continua, id_tipo_beneficiario) values (1,1,1);
insert into educacion_continua_tipo_beneficiario (id, id_educacion_continua, id_tipo_beneficiario) values (2,2,2);
insert into educacion_continua_tipo_beneficiario (id, id_educacion_continua, id_tipo_beneficiario) values (3,3,3);
insert into educacion_continua_tipo_beneficiario (id, id_educacion_continua, id_tipo_beneficiario) values (4,4,4);
insert into educacion_continua_tipo_beneficiario (id, id_educacion_continua, id_tipo_beneficiario) values (5,5,5);
insert into educacion_continua_tipo_beneficiario (id, id_educacion_continua, id_tipo_beneficiario) values (6,6,1);
insert into educacion_continua_tipo_beneficiario (id, id_educacion_continua, id_tipo_beneficiario) values (7,7,2);
insert into educacion_continua_tipo_beneficiario (id, id_educacion_continua, id_tipo_beneficiario) values (8,8,3);
insert into educacion_continua_tipo_beneficiario (id, id_educacion_continua, id_tipo_beneficiario) values (9,9,4);
insert into educacion_continua_tipo_beneficiario (id, id_educacion_continua, id_tipo_beneficiario) values (10,10,5);
insert into educacion_continua_tipo_beneficiario (id, id_educacion_continua, id_tipo_beneficiario) values (11,1,2);
insert into educacion_continua_tipo_beneficiario (id, id_educacion_continua, id_tipo_beneficiario) values (12,2,4);
insert into educacion_continua_tipo_beneficiario (id, id_educacion_continua, id_tipo_beneficiario) values (13,3,1);
insert into educacion_continua_tipo_beneficiario (id, id_educacion_continua, id_tipo_beneficiario) values (14,4,5);
insert into educacion_continua_tipo_beneficiario (id, id_educacion_continua, id_tipo_beneficiario) values (15,5,1);
insert into educacion_continua_tipo_beneficiario (id, id_educacion_continua, id_tipo_beneficiario) values (16,10,4);
insert into educacion_continua_tipo_beneficiario (id, id_educacion_continua, id_tipo_beneficiario) values (17,20,4);
insert into educacion_continua_tipo_beneficiario (id, id_educacion_continua, id_tipo_beneficiario) values (18,20,5);
insert into educacion_continua_tipo_beneficiario (id, id_educacion_continua, id_tipo_beneficiario) values (19,18,1);
insert into educacion_continua_tipo_beneficiario (id, id_educacion_continua, id_tipo_beneficiario) values (20,18,2);

insert into jornadas(id,hora_inicio,hora_fin,educacion_continua_id) values(1,'2016-04-08 08:00:00','2016-04-02 12:00:00',1);
insert into jornadas(id,hora_inicio,hora_fin,educacion_continua_id) values(2,'2016-04-08 14:00:00','2016-04-02 18:00:00',1);
insert into jornadas(id,hora_inicio,hora_fin,educacion_continua_id) values(3, '2019-10-10 08:00:00','2019-10-10 12:00:00',2);



insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (2, 'MTY0XzFfM18xXzEwODQ4NF8xNS0wNC0yMDIwIDA0OjA1', 'files/uploads/educacion-continua/3/qr-participantes/108484.png', 'files/uploads/educacion-continua/3/tarjetas-inscripcion/inscripcion_108484.jpg', 3, 7, 1,2);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (3, 'MTUwXzNfNV8xXzEwODQ4NF8xNS0wNC0yMDIwIDA0OjA1', 'files/uploads/educacion-continua/5/qr-participantes/108484.png', 'files/uploads/educacion-continua/5/tarjetas-inscripcion/inscripcion_108484.jpg', 5, 7, 1,5);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (4, 'MTExXzZfN18xXzEwODQ4NF8xNS0wNC0yMDIwIDA0OjA1', 'files/uploads/educacion-continua/7/qr-participantes/108484.png', 'files/uploads/educacion-continua/7/tarjetas-inscripcion/inscripcion_108484.jpg', 7, 7, 1,2);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (5, 'MTExXzZfMV8xXzEwNjk0ODk0XzE1LTA0LTIwMjAgMDQ6MDc=', 'files/uploads/educacion-continua/1/qr-participantes/10694894.png', 'files/uploads/educacion-continua/1/tarjetas-inscripcion/inscripcion_10694894.jpg', 1, 5, 1,1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (6, 'MTM0XzNfMTBfMV8xMDY5NDg5NF8xNS0wNC0yMDIwIDA0OjA3', 'files/uploads/educacion-continua/10/qr-participantes/10694894.png', 'files/uploads/educacion-continua/10/tarjetas-inscripcion/inscripcion_10694894.jpg', 10, 5, 1,5);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (7, 'MTIyXzJfNF8xXzEwNjk0ODk0XzE1LTA0LTIwMjAgMDQ6MDg=', 'files/uploads/educacion-continua/4/qr-participantes/10694894.png', 'files/uploads/educacion-continua/4/tarjetas-inscripcion/inscripcion_10694894.jpg', 4, 5, 1,3);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (8, 'MTExXzZfN18xXzEwNjk0ODk0XzE1LTA0LTIwMjAgMDQ6MDg=', 'files/uploads/educacion-continua/7/qr-participantes/10694894.png', 'files/uploads/educacion-continua/7/tarjetas-inscripcion/inscripcion_10694894.jpg', 7, 5, 1,2);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (9, 'MTIyXzJfOV8xXzEwNTE0ODRfMTUtMDQtMjAyMCAwNDowOQ==', 'files/uploads/educacion-continua/9/qr-participantes/1051484.png', 'files/uploads/educacion-continua/9/tarjetas-inscripcion/inscripcion_1051484.jpg', 9, 8, 1,3);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (10, 'MTUwXzNfNV8xXzEwNTE0ODRfMTUtMDQtMjAyMCAwNDowOQ==', 'files/uploads/educacion-continua/5/qr-participantes/1051484.png', 'files/uploads/educacion-continua/5/tarjetas-inscripcion/inscripcion_1051484.jpg', 5, 8, 1,5);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (11, 'MTExXzZfMV8xXzEwNTE0ODRfMTUtMDQtMjAyMCAwNDowOQ==', 'files/uploads/educacion-continua/1/qr-participantes/1051484.png', 'files/uploads/educacion-continua/1/tarjetas-inscripcion/inscripcion_1051484.jpg', 1, 8, 1,1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (12, 'MTExXzZfN18xXzEwNTE4NjlfMTUtMDQtMjAyMCAwNDoxMg==', 'files/uploads/educacion-continua/7/qr-participantes/1051869.png', 'files/uploads/educacion-continua/7/tarjetas-inscripcion/inscripcion_1051869.jpg', 7, 3, 1,2);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (13, 'MTIyXzJfOV8xXzEwNTE4NjlfMTUtMDQtMjAyMCAwNDoxMg==', 'files/uploads/educacion-continua/9/qr-participantes/1051869.png', 'files/uploads/educacion-continua/9/tarjetas-inscripcion/inscripcion_1051869.jpg', 9, 3, 1,3);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (14, 'MTM0XzNfMTBfMV8xMDUxODY5XzE1LTA0LTIwMjAgMDQ6MTI=', 'files/uploads/educacion-continua/10/qr-participantes/1051869.png', 'files/uploads/educacion-continua/10/tarjetas-inscripcion/inscripcion_1051869.jpg', 10, 3, 1,5);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (15, 'MTUwXzNfNV8xXzEwNTE4NjlfMTUtMDQtMjAyMCAwNDoxMg==', 'files/uploads/educacion-continua/5/qr-participantes/1051869.png', 'files/uploads/educacion-continua/5/tarjetas-inscripcion/inscripcion_1051869.jpg', 5, 3, 1,5);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (16, 'MTIyXzJfOV8xXzE1MTU0MzZfMTUtMDQtMjAyMCAwNDoxNA==', 'files/uploads/educacion-continua/9/qr-participantes/1515436.png', 'files/uploads/educacion-continua/9/tarjetas-inscripcion/inscripcion_1515436.jpg', 9, 2, 1,3);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (17, 'MTM0XzNfMTBfMV8xNTE1NDM2XzE1LTA0LTIwMjAgMDQ6MTQ=', 'files/uploads/educacion-continua/10/qr-participantes/1515436.png', 'files/uploads/educacion-continua/10/tarjetas-inscripcion/inscripcion_1515436.jpg', 10, 2, 1,5);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (18, 'MTExXzZfMV8xXzE1MTU0MzZfMTUtMDQtMjAyMCAwNDoxNA==', 'files/uploads/educacion-continua/1/qr-participantes/1515436.png', 'files/uploads/educacion-continua/1/tarjetas-inscripcion/inscripcion_1515436.jpg', 1, 2, 1,1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona,id_tipo_persona) values (19, 'MTIyXzJfNF8xXzE1MTU0MzZfMTUtMDQtMjAyMCAwNDoxNA==', 'files/uploads/educacion-continua/4/qr-participantes/1515436.png', 'files/uploads/educacion-continua/4/tarjetas-inscripcion/inscripcion_1515436.jpg', 4, 2, 1,3);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (20, 'MTUwXzNfNV8xXzE1MTU0MzZfMTUtMDQtMjAyMCAwNDoxNA==', 'files/uploads/educacion-continua/5/qr-participantes/1515436.png', 'files/uploads/educacion-continua/5/tarjetas-inscripcion/inscripcion_1515436.jpg', 5, 2, 1,5);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (21, 'MTI2XzFfOF8xXzE1OTg0N18xNS0wNC0yMDIwIDA0OjE2', 'files/uploads/educacion-continua/8/qr-participantes/159847.png', 'files/uploads/educacion-continua/8/tarjetas-inscripcion/inscripcion_159847.jpg', 8, 4, 1,2);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (22, 'MTExXzZfMV8xXzE1OTg0N18xNS0wNC0yMDIwIDA0OjE2', 'files/uploads/educacion-continua/1/qr-participantes/159847.png', 'files/uploads/educacion-continua/1/tarjetas-inscripcion/inscripcion_159847.jpg', 1, 4, 1,1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (23, 'MTM0XzRfMl8xXzE1OTg0N18xNS0wNC0yMDIwIDA0OjE3', 'files/uploads/educacion-continua/2/qr-participantes/159847.png', 'files/uploads/educacion-continua/2/tarjetas-inscripcion/inscripcion_159847.jpg', 2, 4, 1,4);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (24, 'MTIyXzJfNF8xXzE1OTg0N18xNS0wNC0yMDIwIDA0OjE3', 'files/uploads/educacion-continua/4/qr-participantes/159847.png', 'files/uploads/educacion-continua/4/tarjetas-inscripcion/inscripcion_159847.jpg', 4, 4, 1,3);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (25, 'MTE1XzVfNl8xXzE1OTg0N18xNS0wNC0yMDIwIDA0OjE4', 'files/uploads/educacion-continua/6/qr-participantes/159847.png', 'files/uploads/educacion-continua/6/tarjetas-inscripcion/inscripcion_159847.jpg', 6, 4, 1,4);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (26, 'MTY0XzFfM18xXzEwOTA1MDE1XzE1LTA0LTIwMjAgMDQ6MTk=', 'files/uploads/educacion-continua/3/qr-participantes/10905015.png', 'files/uploads/educacion-continua/3/tarjetas-inscripcion/inscripcion_10905015.jpg', 3, 1, 1,2);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (27, 'MTIyXzJfNF8xXzEwOTA1MDE1XzE1LTA0LTIwMjAgMDQ6MTk=', 'files/uploads/educacion-continua/4/qr-participantes/10905015.png', 'files/uploads/educacion-continua/4/tarjetas-inscripcion/inscripcion_10905015.jpg', 4, 1, 1,3);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (28, 'MTE1XzVfNl8xXzEwOTA1MDE1XzE1LTA0LTIwMjAgMDQ6MTk=', 'files/uploads/educacion-continua/6/qr-participantes/10905015.png', 'files/uploads/educacion-continua/6/tarjetas-inscripcion/inscripcion_10905015.jpg', 6, 1, 1,4);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (29, 'MTI2XzFfOF8xXzEwOTA1MDE1XzE1LTA0LTIwMjAgMDQ6MTk=', 'files/uploads/educacion-continua/8/qr-participantes/10905015.png', 'files/uploads/educacion-continua/8/tarjetas-inscripcion/inscripcion_10905015.jpg', 8, 1, 1,2);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (30, 'MTY0XzFfM18xXzEwNTE4NDk4XzE1LTA0LTIwMjAgMDQ6MjE=', 'files/uploads/educacion-continua/3/qr-participantes/10518498.png', 'files/uploads/educacion-continua/3/tarjetas-inscripcion/inscripcion_10518498.jpg', 3, 6, 1,2);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (31, 'MTE1XzVfNl8xXzEwNTE4NDk4XzE1LTA0LTIwMjAgMDQ6MjE=', 'files/uploads/educacion-continua/6/qr-participantes/10518498.png', 'files/uploads/educacion-continua/6/tarjetas-inscripcion/inscripcion_10518498.jpg', 6, 6, 1,4);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante,id_tipo_persona) values (32, 'MTIyXzJfOV8xXzEwNTE4NDk4XzE1LTA0LTIwMjAgMDQ6MjE=', 'files/uploads/educacion-continua/9/qr-participantes/10518498.png', 'files/uploads/educacion-continua/9/tarjetas-inscripcion/inscripcion_10518498.jpg', 9, 6, 1,3);


insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (1, NULL, NULL, 1, 1, 2);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (33, NULL, NULL, 1, 31, 2);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (34, NULL, NULL, 1, 11, 2);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (35, NULL, NULL, 7, 32, 2);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (36, NULL, NULL, 7, 26, 2);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (37, NULL, NULL, 3, 13, 2);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (38, NULL, NULL, 3, 11, 2);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (39, NULL, NULL, 3, 16, 2);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (40, NULL, NULL, 8, 17, 2);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (41, NULL, NULL, 8, 20, 2);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (42, NULL, NULL, 6, 22, 2);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (43, NULL, NULL, 6, 10, 2);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (44, NULL, NULL, 6, 12, 2);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (45, NULL, NULL, 5, 16, 2);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (46, NULL, NULL, 4, 17, 2);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (47, NULL, NULL, 4, 23, 2);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (48, NULL, NULL, 4, 24, 2);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (49, NULL, NULL, 4, 28, 2);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (50, NULL, NULL, 2, 31, 2);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (51, NULL, NULL, 9, 20, 2);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (52, NULL, NULL, 9, 26, 2);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (53, NULL, NULL, 10, 29, 2);


insert into ponentes(id_participante,tema) values(1,'hsufhdu');
insert into ponentes(id_participante,tema) values(33,'sjafijsa');
insert into ponentes(id_participante,tema) values(34,'algun shfishfi');
insert into ponentes(id_participante,tema) values(35,'aojfijsdgi');
insert into ponentes(id_participante,tema) values(36,'algundjngisn');
insert into ponentes(id_participante,tema) values(37,'algjidijsi');
insert into ponentes(id_participante,tema) values(38,'idjgijsi');
insert into ponentes(id_participante,tema) values(39,'nigdnsing');
insert into ponentes(id_participante,tema) values(40,'eauhriang');
insert into ponentes(id_participante,tema) values(41,'hafihansif');
insert into ponentes(id_participante,tema) values(42,'hsnfihasf');
insert into ponentes(id_participante,tema) values(43,'isfihaf');
insert into ponentes(id_participante,tema) values(44,'aisjfisja');
insert into ponentes(id_participante,tema) values(45,'alijsfijafa');
insert into ponentes(id_participante,tema) values(46,'uerhiehn');
insert into ponentes(id_participante,tema) values(47,'aijafiga');
insert into ponentes(id_participante,tema) values(48,'kasjdfmf');
insert into ponentes(id_participante,tema) values(49,'jdnfisjg');
insert into ponentes(id_participante,tema) values(50,'algnasna');
insert into ponentes(id_participante,tema) values(51,'aljfsjafema');
insert into ponentes(id_participante,tema) values(52,'idjfijdga');
insert into ponentes(id_participante,tema) values(53,'dsjggsij');

insert into asistentes(id_participante) values(2);
insert into asistentes(id_participante) values(3);
insert into asistentes(id_participante) values(4);
insert into asistentes(id_participante) values(5);
insert into asistentes(id_participante) values(6);
insert into asistentes(id_participante) values(7);
insert into asistentes(id_participante) values(8);
insert into asistentes(id_participante) values(9);
insert into asistentes(id_participante) values(10);
insert into asistentes(id_participante) values(11);
insert into asistentes(id_participante) values(12);
insert into asistentes(id_participante) values(13);
insert into asistentes(id_participante) values(14);
insert into asistentes(id_participante) values(15);
insert into asistentes(id_participante) values(16);
insert into asistentes(id_participante) values(17);
insert into asistentes(id_participante) values(18);
insert into asistentes(id_participante) values(19);
insert into asistentes(id_participante) values(20);
insert into asistentes(id_participante) values(21);
insert into asistentes(id_participante) values(22);
insert into asistentes(id_participante) values(23);
insert into asistentes(id_participante) values(24);
insert into asistentes(id_participante) values(25);
insert into asistentes(id_participante) values(26);
insert into asistentes(id_participante) values(27);
insert into asistentes(id_participante) values(28);
insert into asistentes(id_participante) values(29);
insert into asistentes(id_participante) values(30);
insert into asistentes(id_participante) values(31);
insert into asistentes(id_participante) values(32);

insert into asistencias(id,id_jornada,id_participante) values (1,1,1);
insert into asistencias(id,id_jornada,id_participante) values (2,2,5);
insert into asistencias(id,id_jornada,id_participante) values (3,1,11);
insert into asistencias(id,id_jornada,id_participante) values (4,2,18);
insert into asistencias(id,id_jornada,id_participante) values (5,1,22);
insert into asistencias(id,id_jornada,id_participante) values (6,2,22);
insert into asistencias(id,id_jornada,id_participante) values (7,1,33);
insert into asistencias(id,id_jornada,id_participante) values (8,1,34);

insert into asistencias(id,id_jornada,id_participante) values (9,1,5);
insert into asistencias(id,id_jornada,id_participante) values (10,1,18);


insert into informes_snies(id,fecha_inicio, fecha_fin,informe_cursos,informe_educacion_continua,informe_participante) values (1,'2020-06-15','2020-06-25','files/reportes_snies/informe_cursos_snies_2020.xlsx','files/reportes_snies/informe_educacion_continua_snies_2020.xlsx',null);
insert into informes_snies(id,fecha_inicio, fecha_fin,informe_cursos,informe_educacion_continua,informe_participante) values (2,'2020-07-15','2020-08-15','files/reportes_snies/informe_cursos_snies_2019.xlsx','files/reportes_snies/informe_educacion_continua_snies_2019.xlsx',null);

insert into diplomas(id,imagen_plantilla) value (1,null);

insert into elementos_diploma(id,x,y) value(1,80,150); /*imagen logo1*/
insert into elementos_diploma(id,x,y) value(2,765,150); /*imagen logo2*/
insert into elementos_diploma(id,x,y) value(3,485,180); /*texto titulo*/
insert into elementos_diploma(id,x,y) value(4,485,200); /*texto subtitulo*/

insert into elementos_diploma(id,x,y) value(5,485,600); 
insert into elementos_diploma(id,x,y) value(6,145,600); 
insert into elementos_diploma(id,x,y) value(7,530,600);
insert into elementos_diploma(id,x,y) value(8,345,590);

insert into imagenes_diploma(x,y,ruta) value(80,150,"/img/plantilla_diploma/logo1.jpg");
insert into imagenes_diploma(x,y,ruta) value(765,150,"/img/plantilla_diploma/logo2.jpg");

insert into imagenes_diploma (x,y,ruta,id_diploma) value (80,150,'files/uploads/educacion-continua/5/plantilla-diploma/senqsrlfibqjontwtzfd.png',1);
insert into imagenes_diploma (x,y,ruta,id_diploma) value (765,150,'files/uploads/educacion-continua/5/plantilla-diploma/fxybjytzzzppbdzagnjh.png',1);


insert into textos_diploma(x,y,categoria,texto) value(485,180,"titulo","");
insert into textos_diploma(x,y,categoria,texto) value(485,200,"subtitulo","");
insert into textos_diploma(x,y,categoria,texto,id_diploma) value (485,180,'titulo', 'Facultad de EducaasifciÃƒÂ³n, Artes y Humanidades',1);
insert into textos_diploma(x,y,categoria,texto,id_diploma) value (485,200,'subtitulo','Programa de Arquitectuigjsra',1);


insert into firmas_diploma (x,y,cargo,imagen_firma_digital,nombre,x_cargo,x_nombre,y_cargo,y_nombre,id_diploma) value (145,600,'Dir. Programa de Sistemas','files/uploads/educacion-continua/5/plantilla-diploma/lbexmznvprsuvzxckpev.png','PhD. Judith del Pilar Rodriguez Tenjo',280,280,627,615,1);
insert into firmas_diploma (x,y,cargo,imagen_firma_digital,nombre,x_cargo,x_nombre,y_cargo,y_nombre,id_diploma) value (530,600,'Docente Departamento Sistemas e InformÃ¡tica','files/uploads/educacion-continua/5/plantilla-diploma/xxhcsflkjclmycsivzlt.png','PhD. Marco Antonio Adarme Jaimes',665,665,627,615,1);

update educacion_continua set id_diploma='1' where id='5';
