insert into generos(id,genero) values(1,'Masculino');
insert into generos(id,genero) values(2,'Femenino');
insert into estados_civiles(id,estado_civil) values(1,'Soltero(a)');
insert into estados_civiles(id,estado_civil) values(2,'Casado(a)');
insert into estados_civiles(id,estado_civil) values(3,'Divorciado(a)');
insert into estados_civiles(id,estado_civil) values(4,'Viudo(a)');
insert into estados_civiles(id,estado_civil) values(5,'Unión Libre');
insert into estados_civiles(id,estado_civil) values(6,'Religioso(a)');
insert into estados_civiles(id,estado_civil) values(8,'Separado(a)');
insert into facultades(id,facultad) values(1,'Ciencias Agrarias y del Ambiente');
insert into facultades(id,facultad) values(2,'Ciencias Básicas');
insert into facultades(id,facultad) values(3,'Ciencias Empresariales');
insert into facultades(id,facultad) values(4,'Ciencias de la Salud');
insert into facultades(id,facultad) values(5,'Educación, Artes y Humanidades');
insert into facultades(id,facultad) values(6,'Ingeniería');
insert into departamentos(id,departamento,id_facultad) values(1,'Sistemas e Informática',6);
insert into departamentos(id,departamento,id_facultad) values(2,'Pedagogía, Andragogía, Comunicación y Multimedios',5);
insert into departamentos(id,departamento,id_facultad) values(3,'Humanidades, Sociales e Idiomas',5);
insert into departamentos(id,departamento,id_facultad) values(4,'Atención Clínica y Rehabilitación',4);
insert into departamentos(id,departamento,id_facultad) values(5,'Promoción, Protección',4);
insert into departamentos(id,departamento,id_facultad) values(6,'Fluidos y Térmicas',6);
insert into departamentos(id,departamento,id_facultad) values(7,'Construcciones Civiles',6);
insert into departamentos(id,departamento,id_facultad) values(8,'Matemáticas y Estadística',2);
insert into departamentos(id,departamento,id_facultad) values(9,'Física',2);
insert into departamentos(id,departamento,id_facultad) values(10,'Biología',2);
insert into departamentos(id,departamento,id_facultad) values(11,'Química',2);
insert into departamentos(id,departamento,id_facultad) values(12,'Electricidad y Electrónica',6);
insert into departamentos(id,departamento,id_facultad) values(13,'Medio Ambiente y Planes de Estudio de Ing. ambiental e Ing. Agroindustrial',1);
insert into departamentos(id,departamento,id_facultad) values(14,'Ciencias Agrícolas y Pecuarias y Programa Ing. Pecuaria',1);
insert into departamentos(id,departamento,id_facultad) values(15,'Ciencias Contables y Programa de Contaduría Pública',3);
insert into departamentos(id,departamento,id_facultad) values(16,'Ciencias Administrativas y Plan de Estudios de Administración de Empresas',3);
insert into programas(id,codigo,programa,id_facultad) values(1,'111','Ingeniería Civil',6);
insert into programas(id,codigo,programa,id_facultad) values(2,'115','Ingeniería de Sistemas',6);
insert into programas(id,codigo,programa,id_facultad) values(3,'164','Ingeniería Agroindustrial',6);
insert into programas(id,codigo,programa,id_facultad) values(4,'150','Arquitectura',5);
insert into programas(id,codigo,programa,id_facultad) values(5,'126','Comercio Internacional',3);
insert into programas(id,codigo,programa,id_facultad) values(6,'122','Contaduría Pública',3);
insert into programas(id,codigo,programa,id_facultad) values(7,'180','Enfermería',4);
insert into programas(id,codigo,programa,id_facultad) values(8,'134','Trabajo Social',5);
insert into tipos_documento(id,tipo_documento,descripcion) values(1,'CC','Cedula Ciudadania');
insert into tipos_documento(id,tipo_documento,descripcion) values(2,'DE','Documento de Identidad Extranjera');
insert into tipos_documento(id,tipo_documento,descripcion) values(3,'CE','Cedula de Extranjeria');
insert into tipos_documento(id,tipo_documento,descripcion) values(4,'TI','Tarjeta de Identidad');
insert into tipos_documento(id,tipo_documento,descripcion) values(5,'PS','Pasaporte');
insert into tipos_documento(id,tipo_documento,descripcion) values(6,'CA','Certificado Cabildo');
insert into tipos_persona(id,tipo_persona) values(1,'Estudiante');
insert into tipos_persona(id,tipo_persona) values(2,'Docente');
insert into tipos_persona(id,tipo_persona) values(3,'Administrativo');
insert into tipos_persona(id,tipo_persona) values(4,'Externo');

insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (1,'atalaya','jhocel@gmail.com','10905015','Jhocel','Duvan','Suescun','Torres','32184602',1,1,'jhocel','$2a$10$n8.oDbyejLtZAcpKKRSsfuUSGMJ6LZ.qNmTURpganDyTZ.s6F.vkm',1,'170','54','54001','1997-01-03','2015-01-06',1,1);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (2,'limonar','javier@gmail.com','1515436','Javier','Eduardo','Calderon','Villamizar','32318485',1,1,'javier','$2a$10$aMefgC8nnLjkzHfYjTjA6eKpdjqhVOlVczgvtJqAD0EUFaWO3U0JO',1,'170','54','54239','1948-10-22','1966-07-04',1,1);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (3,'ceci','janes@gmail.com','1051869','Janes',null,'Duran','Sierra','31482102',1,1,'janes','$2a$10$nUMgvSKj1bmtQ.wmDGxu4uioN0GW16uzJUQ0A0aDl.nW4KjU2pPma',1,'170','54','54001','1997-01-03','2015-01-06',2,1);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (4,'libertad','jose@gmail.com','159847','Jose','Andres','Hernandez','Florez','31651231',1,1,'jose','$2a$10$JzE1av/GQv2Vfbg4UnN2.OIGgV.esym88yyGuAaiWKX/emJ6NSfCK',1,'170','54','54239','1948-10-22','1966-07-04',3,1);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (5,'zulima','dumar@gmail.com','10694894','Dumar','Yeksel','Basto','Moreno','321515402',1,3,'dumar','$2a$10$NiEunU/JrCcRN3/eIsyJNOaeVBI9.H.5A62GVISEKA4YMCLiXZ0hW',1,'170','54','54001','1997-01-03','2015-01-06',5,1);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (6,'lleras','rafael@gmail.com','10518498','Jose','Rafael','Cano','Pabon','32318794',1,1,'rafael','$2a$10$eY49DH.rQf5xKaB2MRU/4OE2uFeXM5sj1w90sTn9QsnC9/O6BtMAe',1,'170','54','54239','1948-10-22','1966-07-04',5,1);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (7,'barrio blanco','maria@gmail.com','108484','Maria','Alejandra','Mendoza','Serrano','3148492102',1,2,'alejandra','$2a$10$GtFcCQ06SgmB4f5M9u.nbuVvHdeP2izJCld.uukBFwlB6uzsTLE.2',1,'170','54','54001','1997-01-03','2015-01-06',4,2);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (8,'quinta oriental','fabian@gmail.com','1051484','Jeison','Fabian','Suarez','Ruiz','32321531',1,1,'fabian','$2a$10$bfsaFSDiZPsOgbjSEIR2guA9f1KSLVGt3UHxGL5F1J/LkANusberu',1,'170','54','54239','1948-10-22','1966-07-04',8,1);

insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (10,'ceiba','yhuver@gmail.com','15845','Yhuver','Andrey','Quintero','Niño','32378741',1,3,'yhuver','$2a$10$agPJ4bVyDr9MrKFFn0IdcOzymOIWfkHDrThQrX5cbla7xcpAI/YIe',1,'170','54','54239','1948-10-22','1966-07-04',4,1);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (11,'prados norte','carla@gmail.com','14854898','Carla','Elena','Ochoa','Florez','321156402',1,1,'carla','$2a$10$CjxUPn9nRlH1PxSKBWo9re10x/pke6u5pgsDKFdPgLF87uVJ9gNye',1,'170','54','54001','1997-01-03','2015-01-06',6,2);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (12,'garcia herreros','deysi@gmail.com','18456','Deysi','Yuliet','Rincon','Medina','323654891',1,1,'deysi','$2a$10$g8Fg3YE0367m3oDfDDCB6u20qy8/m78momOy3lR8woM9lFNLuXcJa',1,'170','54','54239','1948-10-22','1966-07-04',2,2);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (13,'motilones','daniela@gmail.com','1087454','Daniela',null,'Rojas','Bernal','321024184',1,1,'daniela','$2a$10$A.xBF7LRJIcXr8BW5qE/oe9mSA9Fqih5dip6B/OpT8rwFLc63Z.u2',1,'170','54','54001','1997-01-03','2015-01-06',4,2);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (14,'prados del este','william@gmail.com','848456','William','David','Sierra','Lobo','32566531',1,2,'william','$2a$10$AaKEGvcbrrF5aGGBUpXKT.0tn.gy3KQeLhScczT4LDjD0s7oiWbCu',1,'170','54','54239','1948-10-22','1966-07-04',5,1);

insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (16,'sayago','luis@gmail.com','151615','Luis','Alberto','Perez','Angarita','32387841',1,1,'luis','$2a$10$d7PEEWDt8ZaYpObqQuAYFeExmsQk.NtYsHrlc2kYCXaFL/LMHmn4m',1,'170','54','54239','1948-10-22','1966-07-04',8,1);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (17,'san luis','karim@gmail.com','184561','Karim',null,'Mustafa','Hernandez','321054652',1,2,'karim','$2a$10$h7LkE9vpNQ3KUS0feNg7d.8rf4VarXB0dN6/hgy0Rr/2DUp5Yhd9u',1,'170','54','54001','1997-01-03','2015-01-06',4,1);

insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (19,'kiosco','hamer@gmail.com','12185489','Hamer',null,'Castellanos','Fuentes','321548402',1,3,'hamer','$2a$10$4F.324L/xcPH0cZDQ.kG5.t5SDomJTWqEUvXuB2E0TTnEV09hI8O2',1,'170','54','54001','1997-01-03','2015-01-06',6,1);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (20,'aeropuerto','karen@gmail.com','1034879','Karen','Lizeth','Ramos','Quintero','3231849',1,1,'karen','$2a$10$q8LJg09XvSEr1s5ePIu6COl52IYF/GrJVoePYzbl0z.8bBN7.0hTS',1,'170','54','54239','1948-10-22','1966-07-04',1,2);

insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (21,'sdhfishfiks','madarme@gmail.com','87984651','Marco','Antonio','Adarme','Jaimes','3157654',2,1,'marco','$2y$12$ZtRDeYhOkJ4CIVoHq6rXFe880TXfvPecO84ehzs6AWHqzqTCLACdG',1,'170','05','05001','2002-08-12','2020-06-21',8,1);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (22,'hnfisdfiksd','jairofuentes@gmail.com','89787554','Jairo','Alberto','Fuentes','Camargo','354156481',2,1,'jairo','$2y$12$m2ZNAm7Y/AhZX6L26ysWpuEZiICqgbNVnNYfWLwyCwY2jJNiKLst2',1,'170','68','68001','1995-04-17','2013-03-07',2,1);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (23,'safhiahfhian','oscargallardo@gmail.com','78968754','Oscar','Alberto','Gallardo','Perez','3465455464',2,1,'oscar','$2y$12$.0WE21UcXFmZ0v86bicaN.YWOlLZrH5MrTinJS5AKmUhyuQ/vSlQW',1,'408',null,null,'1985-11-04','2003-10-17',4,1);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (24,'uhsfuahn','jairo@gmail.com','89484854','Jairo','Wilgberto','Cely','Niño','31587654',2,1,'cely','$2a$10$VBK3Yrn/JMIJ6.wqJBtdI.Y203/8s99N3vMkphI8BXzVK6d60rv0i',1,'170','05','05001','2002-08-12','2020-06-21',8,1);

insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (26,'safuabujs','mariela@gmail.com','6987418754','Lina','Mariela','Ardila','Marin','348455464',2,1,'mariela','$2a$10$5UoetcXYKzSUE8sZBQmlH.Xw7tx/zakSoNminmS33RQGt0t.r6GJe',1,'408',null,null,'1985-11-04','2003-10-17',6,2);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (27,'shfuahsfish','olga@gmail.com','841584651','Maria','Olga','Caceres','Carvajal','321487654',2,1,'olga','$2a$10$PKiS04lPKqQyNiVLNrlZTOJFVOGRvWbmS1QONq1JR6IK3EGDF6AwO',1,'170','05','05001','2002-08-12','2020-06-21',5,2);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (28,'ushfuahsbfu','claudia@gmail.com','874587554','Claudia','Yamile','Gomez','Llanez','354156481',2,1,'claudia','$2a$10$n2KzljRmeJTPXupW/dJ2IOwIr.Ma5JZrwQ/2GXwmhGzsYNP3ApiYG',1,'170','68','68001','1995-04-17','2013-03-07',2,2);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (29,'fdsiuahfi','jessica@gmail.com','98768754','Jessica','Lorena','Leal','Pabon','3514455464',2,1,'jessica','$2a$10$c5kwuKK0LMze/iqB8op3e.nRvZM7G8PobQ/IfMJsdhOfYhZ8ut2jm',1,'408',null,null,'1985-11-04','2003-10-17',4,2);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (30,'hfuashfuish','hugo@gmail.com','84184651','Hugo','Enrique','Maldonado',null,'37887654',2,1,'hugo','$2a$10$4unT6JVFsq1iwTZjWYUudOrV3xl1wrzZs1fMcgzPHzTnL0ITSQ6Su',1,'170','05','05001','2002-08-12','2020-06-21',3,1);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (31,'sfhsuifhu','ludwig@gmail.com','8987554','Ludwig','Enrique','Sierra','Higuera','398156481',2,1,'ludwig','$2a$10$m/0/mfDlg7d5FBSbOjLTtO.PHFeGWcQ.ePoilr1rg8vm11jsLQclK',1,'170','68','68001','1995-04-17','2013-03-07',2,1);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (32,'hdfosuhguihdf','rene@gmail.com','6468754','Carlos','René','Angarita','Sanguino','3487455464',2,1,'rene','$2a$10$h42757GiQwiY8ljRUHXfAOJxx1.gxCbR3dhMzawE8ciTcRif4k/ty',1,'408',null,null,'1985-11-04','2003-10-17',1,1);


insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (33,'jndksnf','mariat@gmail.com','451','maria','teresa','tovar','no sé el otro','86545',3,1,'mariat','$2y$12$kZilElWv1J9L4vZnyZeo.e3.XpgCOXy9FC9TOzAN9kJ8QQdtqhI3a',1,'152',null,null,'1978-07-24','1990-11-12',2,2);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (34,'dnkisndgks','pedro@gmail.com','1561','pedro','npi','llanos','npi x2','8754',3,1,'pedro','$2y$12$Pf.esTZF8K56QzyElVOPHeQHLLktyT9MPHF8FQ0sVkZBmkh3cVV/C',1,'192',null,null,'1994-05-10','2012-09-25',5,1);
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (35,'gjfgjdh','carlos@gmail.com','8645','carlos','antonio','perez','rodriguez','86545',4,1,'carlos','$2y$12$YDPFttCM69kWxKG5amI53ewmrgrDllKrOtMI8YNsKnlUwkOZQrgKa',1,'218',null,null,'1956-01-08','1970-12-10',8,1);


insert into estudiantes (codigo,id_persona,id_programa) values ('115-1241',1,2);
insert into estudiantes (codigo,id_persona,id_programa) values ('115-1229',2,2);
insert into estudiantes (codigo,id_persona,id_programa) values ('164-4552',3,3);
insert into estudiantes (codigo,id_persona,id_programa) values ('150-1956',4,4);
insert into estudiantes (codigo,id_persona,id_programa) values ('126-8465',5,5);
insert into estudiantes (codigo,id_persona,id_programa) values ('122-2151',6,6);
insert into estudiantes (codigo,id_persona,id_programa) values ('180-1845',7,7);
insert into estudiantes (codigo,id_persona,id_programa) values ('134-4865',8,8);
insert into estudiantes (codigo,id_persona,id_programa) values ('115-3547',10,2);
insert into estudiantes (codigo,id_persona,id_programa) values ('164-8941',11,3);
insert into estudiantes (codigo,id_persona,id_programa) values ('150-6542',12,4);
insert into estudiantes (codigo,id_persona,id_programa) values ('126-8796',13,5);
insert into estudiantes (codigo,id_persona,id_programa) values ('122-3214',14,6);
insert into estudiantes (codigo,id_persona,id_programa) values ('134-8978',16,8);
insert into estudiantes (codigo,id_persona,id_programa) values ('111-1562',17,1);
insert into estudiantes (codigo,id_persona,id_programa) values ('164-3546',19,3);
insert into estudiantes (codigo,id_persona,id_programa) values ('150-8861',20,4);


insert into docentes (id_persona,codigo,id_departamento) values (21,'05848',1);
insert into docentes (id_persona,codigo,id_departamento) values (22,'08465',1);
insert into docentes (id_persona,codigo,id_departamento) values (23,'01516',1);
insert into docentes (id_persona,codigo,id_departamento) values (24,'05154',1);
insert into docentes (id_persona,codigo,id_departamento) values (26,'84456',7);
insert into docentes (id_persona,codigo,id_departamento) values (27,'51515',4);
insert into docentes (id_persona,codigo,id_departamento) values (28,'14848',3);
insert into docentes (id_persona,codigo,id_departamento) values (29,'14488',4);
insert into docentes (id_persona,codigo,id_departamento) values (30,'84862',7);
insert into docentes (id_persona,codigo,id_departamento) values (31,'65348',8);
insert into docentes (id_persona,codigo,id_departamento) values (32,'52514',1);


insert into administrativos (id_persona,cargo) values (33,'secretaria');
insert into administrativos (id_persona,cargo) values (34,'vicerector de extension');

insert into externos (id_persona,profesion) values (35,'ing de sistemas');


insert into roles(id,authority,id_persona) values(1,'ROLE_ESTUDIANTE',1);
insert into roles(id,authority,id_persona) values(2,'ROLE_BECA',1);
insert into roles(id,authority,id_persona) values(3,'ROLE_ESTUDIANTE',2);
insert into roles(id,authority,id_persona) values(4,'ROLE_ESTUDIANTE',3);
insert into roles(id,authority,id_persona) values(5,'ROLE_ESTUDIANTE',4);
insert into roles(id,authority,id_persona) values(6,'ROLE_ESTUDIANTE',5);
insert into roles(id,authority,id_persona) values(7,'ROLE_ESTUDIANTE',6);
insert into roles(id,authority,id_persona) values(8,'ROLE_ESTUDIANTE',7);
insert into roles(id,authority,id_persona) values(9,'ROLE_ESTUDIANTE',8);
insert into roles(id,authority,id_persona) values(11,'ROLE_ESTUDIANTE',10);
insert into roles(id,authority,id_persona) values(12,'ROLE_ESTUDIANTE',11);
insert into roles(id,authority,id_persona) values(13,'ROLE_ESTUDIANTE',12);
insert into roles(id,authority,id_persona) values(14,'ROLE_ESTUDIANTE',13);
insert into roles(id,authority,id_persona) values(15,'ROLE_ESTUDIANTE',14);
insert into roles(id,authority,id_persona) values(17,'ROLE_ESTUDIANTE',16);
insert into roles(id,authority,id_persona) values(18,'ROLE_ESTUDIANTE',17);
insert into roles(id,authority,id_persona) values(20,'ROLE_ESTUDIANTE',19);
insert into roles(id,authority,id_persona) values(21,'ROLE_ESTUDIANTE',20);


insert into roles(id,authority,id_persona) values(22,'ROLE_DOCENTE',21);
insert into roles(id,authority,id_persona) values(23,'ROLE_DOCENTE',22);
insert into roles(id,authority,id_persona) values(24,'ROLE_DOCENTE',23);
insert into roles(id,authority,id_persona) values(25,'ROLE_DOCENTE',24);
insert into roles(id,authority,id_persona) values(27,'ROLE_DOCENTE',26);
insert into roles(id,authority,id_persona) values(28,'ROLE_DOCENTE',27);
insert into roles(id,authority,id_persona) values(29,'ROLE_DOCENTE',28);
insert into roles(id,authority,id_persona) values(30,'ROLE_DOCENTE',29);
insert into roles(id,authority,id_persona) values(31,'ROLE_DOCENTE',30);
insert into roles(id,authority,id_persona) values(32,'ROLE_DOCENTE',31);
insert into roles(id,authority,id_persona) values(33,'ROLE_DOCENTE',32);



insert into roles(id,authority,id_persona) values(34,'ROLE_DIRPROGRAMA',23);
insert into roles(id,authority,id_persona) values(35,'ROLE_ADMINISTRATIVO',33);
insert into roles(id,authority,id_persona) values(36,'ROLE_ADMINISTRATIVO',34);
insert into roles(id,authority,id_persona) values(37,'ROLE_SUPERADMIN',34);
insert into roles(id,authority,id_persona) values(38,'ROLE_EXTERNO',35);


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
insert into clasificacion_cine(id,clasificacion_cine) values(223,'Filosofía y ética');
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
insert into clasificacion_cine(id,clasificacion_cine) values(533,'Física');
insert into clasificacion_cine(id,clasificacion_cine) values(541,'Matemáticas');
insert into clasificacion_cine(id,clasificacion_cine) values(542,'Estadística');
insert into clasificacion_cine(id,clasificacion_cine) values(611,'Uso de computadores');
insert into clasificacion_cine(id,clasificacion_cine) values(612,'Diseño y administración de redes y bases de datos');
insert into clasificacion_cine(id,clasificacion_cine) values(613,'Desarrollo y análisis de software y aplicaciones');
insert into clasificacion_cine(id,clasificacion_cine) values(711,'Ingeniería y procesos químicos');
insert into clasificacion_cine(id,clasificacion_cine) values(712,'Tecnología de protección del medio ambiente');
insert into clasificacion_cine(id,clasificacion_cine) values(713,'Electricidad y energía');
insert into clasificacion_cine(id,clasificacion_cine) values(714,'Electrónica y automatización');
insert into clasificacion_cine(id,clasificacion_cine) values(715,'Mecánica y profesiones afines a la metalistería');
insert into clasificacion_cine(id,clasificacion_cine) values(716,'Vehículos, barcos y aeronaves motorizadas');
insert into clasificacion_cine(id,clasificacion_cine) values(721,'Procesamiento de alimentos');
insert into clasificacion_cine(id,clasificacion_cine) values(722,'Materiales (vidrio, papel, plástico y madera)');
insert into clasificacion_cine(id,clasificacion_cine) values(723,'Producción textiles (ropa, calzado y artículos de cuero)');
insert into clasificacion_cine(id,clasificacion_cine) values(724,'Minería y extracción');
insert into clasificacion_cine(id,clasificacion_cine) values(731,'Arquitectura y urbanismo');
insert into clasificacion_cine(id,clasificacion_cine) values(732,'Construcción e ingeniería civil');
insert into clasificacion_cine(id,clasificacion_cine) values(811,'Producción agrícola y ganadera');
insert into clasificacion_cine(id,clasificacion_cine) values(812,'Horticultura');
insert into clasificacion_cine(id,clasificacion_cine) values(821,'Silvicultura');
insert into clasificacion_cine(id,clasificacion_cine) values(831,'Pesca');
insert into clasificacion_cine(id,clasificacion_cine) values(841,'Veterinaria');
insert into clasificacion_cine(id,clasificacion_cine) values(911,'Odontología');
insert into clasificacion_cine(id,clasificacion_cine) values(912,'Medicina');
insert into clasificacion_cine(id,clasificacion_cine) values(913,'Enfermería y partería');
insert into clasificacion_cine(id,clasificacion_cine) values(914,'Tecnología de diagnóstico y tratamiento médico');
insert into clasificacion_cine(id,clasificacion_cine) values(915,'Terapia y rehabilitación');
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

insert into tipos_beneficiarios(id,tipo_beneficiario) values(1,'Estudiante de la IES');
insert into tipos_beneficiarios(id,tipo_beneficiario) values(2,'Graduado de la IES');
insert into tipos_beneficiarios(id,tipo_beneficiario) values(3,'Profesor de la IES');
insert into tipos_beneficiarios(id,tipo_beneficiario) values(4,'Administrativo de la IES');
insert into tipos_beneficiarios(id,tipo_beneficiario) values(5,'Persona no vinculada a la IES');

insert into tipos_educacion_continua(id,tipo_educacion_continua) values(1,'Curso');
insert into tipos_educacion_continua(id,tipo_educacion_continua) values(2,'Diplomado');
insert into tipos_educacion_continua(id,tipo_educacion_continua) values(3,'Simposio');
insert into tipos_educacion_continua(id,tipo_educacion_continua) values(4,'Taller');
insert into tipos_educacion_continua(id,tipo_educacion_continua) values(5,'Seminario');
insert into tipos_educacion_continua(id,tipo_educacion_continua) values(6,'Congreso');

insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,id_tipo_beneficiario) values(1,'CIINATIC','2020-01-10','2020-01-15','2020-01-08','contenido generalll CIINATIC',10,'BBBBB',30000,26,6,'/uploads/educacion-continua/1/portada.jpg',21,1,'001',711,1);
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,id_tipo_beneficiario) values(2,'EISI','2020-02-14','2020-02-17','2020-02-14','contenido generalll EISI',100,'aaaa',52000,40,4,'/uploads/educacion-continua/2/portada.jpg',27,8,'002',711,2);
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,id_tipo_beneficiario) values(3,'BIENAL','2020-02-25','2020-02-29','2020-02-24','contenido generalll de la bienal',10,'jdihsig',20000,35,1,'/uploads/educacion-continua/3/portada.png',29,3,'001',541,1);
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,id_tipo_beneficiario) values(4,'EISI2','2020-03-05','2020-03-10','2020-03-07','contenido generalll del eisi2',10,'indgisng',34800,20,2,'/uploads/educacion-continua/4/portada.jpg',30,6,'001',1021,3);
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,id_tipo_beneficiario) values(5,'BIENAL2','2020-04-20','2020-04-20','2020-04-18','contenido generalll de la bienal2',10,'nsiengtisent',78647,25,3,'/uploads/educacion-continua/5/portada.jpg',21,4,'001',521,1);
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,id_tipo_beneficiario) values(6,'CIINATIC3','2020-05-12','2020-05-14','2020-05-13','contenido generalll del ciinatic3',10,'isjign',640000,40,5,'/uploads/educacion-continua/6/portada.jpg',22,2,'001',711,5);
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,id_tipo_beneficiario) values(7,'RALLY MATEMATICO','2020-06-25','2020-06-25','2020-06-22','contenido generalll del rally',10,'sjdisjgi',25500,60,6,'/uploads/educacion-continua/7/portada.jpg',23,1,'002',923,3);
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,id_tipo_beneficiario) values(8,'II Ingeniería Civil','2020-07-10','2020-07-13','2020-07-09','contenido generalll del congreso de civil',10,'sjgisigj',100000,70,1,'/uploads/educacion-continua/8/portada.jpg',32,5,'002',412,4);
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,id_tipo_beneficiario) values(9,'Encuentro Internacional','2020-07-25','2020-07-30','2020-07-20','contenido generalll de ciencias aplicadas',10,'ijdsgijs',67800,10,2,'/uploads/educacion-continua/9/portada.jpg',31,6,'002',711,5);
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,id_tipo_beneficiario) values(10,'Bienal3','2020-08-18','2020-08-21','2020-08-16','contenido generalll de la bienal3',10,'ijdisjg',34500,36,3,'/uploads/educacion-continua/10/portada.jpg',21,8,'003',417,3);

insert into jornadas(id,fecha,hora_inicio,hora_fin,educacion_continua_id) values(1,'2016-04-06','2016-04-08 08:00:00','2016-04-02 12:00:00',1);
insert into jornadas(id,fecha,hora_inicio,hora_fin,educacion_continua_id) values(2,'2016-04-06','2016-04-08 14:00:00','2016-04-02 18:00:00',1);
insert into jornadas(id,fecha,hora_inicio,hora_fin,educacion_continua_id) values(3,'2019-10-10','2019-10-10 08:00:00','2019-10-10 12:00:00',2);





insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (2, 'Q29uZ3Jlc29fQ0lJTkFUSUNfQXNpc3RlbnRlXzE1MTU0MzZfSmF2aWVyX0NhbGRlcm9uXzAzLTA0LTIwMjAgMDI6MDM=', '/uploads/educacion-continua/1/qr-participantes/1515436.png', 1, 2, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (3, 'Q29uZ3Jlc29fQ0lJTkFUSUNfQXNpc3RlbnRlXzEwNTE0ODRfSmVpc29uX1N1YXJlel8wMy0wNC0yMDIwIDAxOjU5', '/uploads/educacion-continua/1/qr-participantes/1051484.png', 1, 8, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (4, 'Q29uZ3Jlc29fQ0lJTkFUSUNfQXNpc3RlbnRlXzE1OTg0N19Kb3NlX0hlcm5hbmRlel8wMy0wNC0yMDIwIDAyOjA1', '/uploads/educacion-continua/1/qr-participantes/159847.png', 1, 4, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (5, 'Q3Vyc29fQklFTkFMX0FzaXN0ZW50ZV8xMDkwNTAxNV9KaG9jZWxfU3Vlc2N1bl8wMy0wNC0yMDIwIDE0OjAz', '/uploads/educacion-continua/3/qr-participantes/10905015.png', 3, 1, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (6, 'RGlwbG9tYWRvX0VJU0kyX0FzaXN0ZW50ZV8xMDkwNTAxNV9KaG9jZWxfU3Vlc2N1bl8wMy0wNC0yMDIwIDE0OjA0', '/uploads/educacion-continua/4/qr-participantes/10905015.png', 4, 1, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (7, 'U2VtaW5hcmlvX0NJSU5BVElDM19Bc2lzdGVudGVfMTA5MDUwMTVfSmhvY2VsX1N1ZXNjdW5fMDMtMDQtMjAyMCAxNDowNA==', '/uploads/educacion-continua/6/qr-participantes/10905015.png', 6, 1, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (8, 'Q3Vyc29fSUkgSW5nZW5pZXLDrWEgQ2l2aWxfQXNpc3RlbnRlXzEwOTA1MDE1X0pob2NlbF9TdWVzY3VuXzAzLTA0LTIwMjAgMTQ6MDQ=', '/uploads/educacion-continua/8/qr-participantes/10905015.png', 8, 1, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (9, 'U2ltcG9zaW9fQmllbmFsM19Bc2lzdGVudGVfMTUxNTQzNl9KYXZpZXJfQ2FsZGVyb25fMDMtMDQtMjAyMCAxNDowNQ==', '/uploads/educacion-continua/10/qr-participantes/1515436.png', 10, 2, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (10, 'RGlwbG9tYWRvX0VuY3VlbnRybyBJbnRlcm5hY2lvbmFsX0FzaXN0ZW50ZV8xNTE1NDM2X0phdmllcl9DYWxkZXJvbl8wMy0wNC0yMDIwIDE0OjA1', '/uploads/educacion-continua/9/qr-participantes/1515436.png', 9, 2, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (11, 'U2ltcG9zaW9fQklFTkFMMl9Bc2lzdGVudGVfMTUxNTQzNl9KYXZpZXJfQ2FsZGVyb25fMDMtMDQtMjAyMCAxNDowNQ==', '/uploads/educacion-continua/5/qr-participantes/1515436.png', 5, 2, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (12, 'RGlwbG9tYWRvX0VJU0kyX0FzaXN0ZW50ZV8xNTE1NDM2X0phdmllcl9DYWxkZXJvbl8wMy0wNC0yMDIwIDE0OjA2', '/uploads/educacion-continua/4/qr-participantes/1515436.png', 4, 2, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (13, 'U2ltcG9zaW9fQklFTkFMMl9Bc2lzdGVudGVfMTA1MTg2OV9KYW5lc19EdXJhbl8wMy0wNC0yMDIwIDE0OjEw', '/uploads/educacion-continua/5/qr-participantes/1051869.png', 5, 3, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (14, 'Q29uZ3Jlc29fUkFMTFkgTUFURU1BVElDT19Bc2lzdGVudGVfMTA1MTg2OV9KYW5lc19EdXJhbl8wMy0wNC0yMDIwIDE0OjEw', '/uploads/educacion-continua/7/qr-participantes/1051869.png', 7, 3, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (15, 'U2ltcG9zaW9fQmllbmFsM19Bc2lzdGVudGVfMTA1MTg2OV9KYW5lc19EdXJhbl8wMy0wNC0yMDIwIDE0OjEw', '/uploads/educacion-continua/10/qr-participantes/1051869.png', 10, 3, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (16, 'RGlwbG9tYWRvX0VuY3VlbnRybyBJbnRlcm5hY2lvbmFsX0FzaXN0ZW50ZV8xMDUxODY5X0phbmVzX0R1cmFuXzAzLTA0LTIwMjAgMTQ6MTE=', '/uploads/educacion-continua/9/qr-participantes/1051869.png', 9, 3, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (17, 'VGFsbGVyX0VJU0lfQXNpc3RlbnRlXzE1OTg0N19Kb3NlX0hlcm5hbmRlel8wMy0wNC0yMDIwIDE0OjEx', '/uploads/educacion-continua/2/qr-participantes/159847.png', 2, 4, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (18, 'RGlwbG9tYWRvX0VJU0kyX0FzaXN0ZW50ZV8xNTk4NDdfSm9zZV9IZXJuYW5kZXpfMDMtMDQtMjAyMCAxNDoxMQ==', '/uploads/educacion-continua/4/qr-participantes/159847.png', 4, 4, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (19, 'U2VtaW5hcmlvX0NJSU5BVElDM19Bc2lzdGVudGVfMTU5ODQ3X0pvc2VfSGVybmFuZGV6XzAzLTA0LTIwMjAgMTQ6MTI=', '/uploads/educacion-continua/6/qr-participantes/159847.png', 6, 4, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (20, 'Q3Vyc29fSUkgSW5nZW5pZXLDrWEgQ2l2aWxfQXNpc3RlbnRlXzE1OTg0N19Kb3NlX0hlcm5hbmRlel8wMy0wNC0yMDIwIDE0OjEy', '/uploads/educacion-continua/8/qr-participantes/159847.png', 8, 4, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (21, 'Q29uZ3Jlc29fQ0lJTkFUSUNfQXNpc3RlbnRlXzEwNjk0ODk0X0R1bWFyX0Jhc3RvXzAzLTA0LTIwMjAgMTQ6MTI=', '/uploads/educacion-continua/1/qr-participantes/10694894.png', 1, 5, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (22, 'RGlwbG9tYWRvX0VJU0kyX0FzaXN0ZW50ZV8xMDY5NDg5NF9EdW1hcl9CYXN0b18wMy0wNC0yMDIwIDE0OjEz', '/uploads/educacion-continua/4/qr-participantes/10694894.png', 4, 5, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (23, 'Q29uZ3Jlc29fUkFMTFkgTUFURU1BVElDT19Bc2lzdGVudGVfMTA2OTQ4OTRfRHVtYXJfQmFzdG9fMDMtMDQtMjAyMCAxNDoxMw==', '/uploads/educacion-continua/7/qr-participantes/10694894.png', 7, 5, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (24, 'U2ltcG9zaW9fQmllbmFsM19Bc2lzdGVudGVfMTA2OTQ4OTRfRHVtYXJfQmFzdG9fMDMtMDQtMjAyMCAxNDoxMw==', '/uploads/educacion-continua/10/qr-participantes/10694894.png', 10, 5, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (25, 'Q3Vyc29fQklFTkFMX0FzaXN0ZW50ZV8xMDUxODQ5OF9Kb3NlX0Nhbm9fMDMtMDQtMjAyMCAxNDoxMw==', '/uploads/educacion-continua/3/qr-participantes/10518498.png', 3, 6, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (26, 'U2VtaW5hcmlvX0NJSU5BVElDM19Bc2lzdGVudGVfMTA1MTg0OThfSm9zZV9DYW5vXzAzLTA0LTIwMjAgMTQ6MTQ=', '/uploads/educacion-continua/6/qr-participantes/10518498.png', 6, 6, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (27, 'RGlwbG9tYWRvX0VuY3VlbnRybyBJbnRlcm5hY2lvbmFsX0FzaXN0ZW50ZV8xMDUxODQ5OF9Kb3NlX0Nhbm9fMDMtMDQtMjAyMCAxNDoxNA==', '/uploads/educacion-continua/9/qr-participantes/10518498.png', 9, 6, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (28, 'Q29uZ3Jlc29fUkFMTFkgTUFURU1BVElDT19Bc2lzdGVudGVfMTA4NDg0X01hcmlhX01lbmRvemFfMDMtMDQtMjAyMCAxNDoxNQ==', '/uploads/educacion-continua/7/qr-participantes/108484.png', 7, 7, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (29, 'U2ltcG9zaW9fQklFTkFMMl9Bc2lzdGVudGVfMTA4NDg0X01hcmlhX01lbmRvemFfMDMtMDQtMjAyMCAxNDoxNQ==', '/uploads/educacion-continua/5/qr-participantes/108484.png', 5, 7, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (30, 'Q3Vyc29fQklFTkFMX0FzaXN0ZW50ZV8xMDg0ODRfTWFyaWFfTWVuZG96YV8wMy0wNC0yMDIwIDE0OjE1', '/uploads/educacion-continua/3/qr-participantes/108484.png', 3, 7, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (31, 'U2ltcG9zaW9fQklFTkFMMl9Bc2lzdGVudGVfMTA1MTQ4NF9KZWlzb25fU3VhcmV6XzAzLTA0LTIwMjAgMTQ6MTY=', '/uploads/educacion-continua/5/qr-participantes/1051484.png', 5, 8, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,educacion_continua_id,id_persona,id_tipo_participante) values (32, 'RGlwbG9tYWRvX0VuY3VlbnRybyBJbnRlcm5hY2lvbmFsX0FzaXN0ZW50ZV8xMDUxNDg0X0plaXNvbl9TdWFyZXpfMDMtMDQtMjAyMCAxNDoxNg==', '/uploads/educacion-continua/9/qr-participantes/1051484.png', 9, 8, 1);

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


insert into informes_snies(id,anio,informe_cursos,informe_educacion_continua,informe_participante) values (1,'2020','/reportes_snies/informe_cursos_snies/2020.xlsx','/reportes_snies/informe_educacion_continua_snies/2020.xlsx',null);
insert into informes_snies(id,anio,informe_cursos,informe_educacion_continua,informe_participante) values (2,'2019','/reportes_snies/informe_cursos_snies/2019.xlsx','/reportes_snies/informe_educacion_continua_snies/2019.xlsx',null);
