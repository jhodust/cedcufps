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
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,id_tipo_persona,id_tipo_documento,username,password,enabled,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero) values (25,'hsifais','pilar@gmail.com','847854','Judith','del Pilar','Rodriguez','Tenjo','3154887',2,1,'pilar','$2a$10$KdwIuJc.yZimQMiE/6VrEOqUry3QFC/BV3e1f3JapsHaG.BbtB/Ru',1,'170','05','05001','2000-04-17','2020-01-19',3,2);

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




insert into docentes (id_persona,codigo,id_departamento) values (21,'05848',1);
insert into docentes (id_persona,codigo,id_departamento) values (22,'08465',1);
insert into docentes (id_persona,codigo,id_departamento) values (23,'01516',1);
insert into docentes (id_persona,codigo,id_departamento) values (24,'05154',1);
insert into docentes (id_persona,codigo,id_departamento) values (25,'03546',1);
insert into docentes (id_persona,codigo,id_departamento) values (26,'84456',7);
insert into docentes (id_persona,codigo,id_departamento) values (27,'51515',4);
insert into docentes (id_persona,codigo,id_departamento) values (28,'14848',3);
insert into docentes (id_persona,codigo,id_departamento) values (29,'14488',4);
insert into docentes (id_persona,codigo,id_departamento) values (30,'84862',7);
insert into docentes (id_persona,codigo,id_departamento) values (31,'65348',8);
insert into docentes (id_persona,codigo,id_departamento) values (32,'52514',1);

insert into programas(id,codigo,programa,id_facultad,id_director) values(1,'111','Ingeniería Civil',6,23);
insert into programas(id,codigo,programa,id_facultad,id_director) values(2,'115','Ingeniería de Sistemas',6,28);
insert into programas(id,codigo,programa,id_facultad,id_director) values(3,'164','Ingeniería Agroindustrial',6,32);
insert into programas(id,codigo,programa,id_facultad,id_director) values(4,'150','Arquitectura',5,31);
insert into programas(id,codigo,programa,id_facultad,id_director) values(5,'126','Comercio Internacional',3,26);
insert into programas(id,codigo,programa,id_facultad,id_director) values(6,'122','Contaduría Pública',3,30);
insert into programas(id,codigo,programa,id_facultad,id_director) values(7,'180','Enfermería',4,21);
insert into programas(id,codigo,programa,id_facultad,id_director) values(8,'134','Trabajo Social',5,25);



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


insert into administrativos (id_persona,cargo) values (33,'secretaria');
insert into administrativos (id_persona,cargo) values (34,'vicerector de extension');

insert into externos (id_persona,profesion) values (35,'ing de sistemas');



insert into roles(id,authority,id_persona) values(1,'ROLE_USER',1);
insert into roles(id,authority,id_persona) values(2,'ROLE_BECA',1);
insert into roles(id,authority,id_persona) values(3,'ROLE_USER',2);
insert into roles(id,authority,id_persona) values(4,'ROLE_USER',3);
insert into roles(id,authority,id_persona) values(5,'ROLE_USER',4);
insert into roles(id,authority,id_persona) values(6,'ROLE_USER',5);
insert into roles(id,authority,id_persona) values(7,'ROLE_USER',6);
insert into roles(id,authority,id_persona) values(8,'ROLE_USER',7);
insert into roles(id,authority,id_persona) values(9,'ROLE_USER',8);
insert into roles(id,authority,id_persona) values(11,'ROLE_USER',10);
insert into roles(id,authority,id_persona) values(12,'ROLE_USER',11);
insert into roles(id,authority,id_persona) values(13,'ROLE_USER',12);
insert into roles(id,authority,id_persona) values(14,'ROLE_USER',13);
insert into roles(id,authority,id_persona) values(15,'ROLE_USER',14);
insert into roles(id,authority,id_persona) values(17,'ROLE_USER',16);
insert into roles(id,authority,id_persona) values(18,'ROLE_USER',17);
insert into roles(id,authority,id_persona) values(20,'ROLE_USER',19);
insert into roles(id,authority,id_persona) values(21,'ROLE_USER',20);


insert into roles(id,authority,id_persona) values(22,'ROLE_USER',21);
insert into roles(id,authority,id_persona) values(23,'ROLE_USER',22);
insert into roles(id,authority,id_persona) values(24,'ROLE_USER',23);
insert into roles(id,authority,id_persona) values(25,'ROLE_USER',24);
insert into roles(id,authority,id_persona) values(26,'ROLE_USER',25);
insert into roles(id,authority,id_persona) values(27,'ROLE_USER',26);
insert into roles(id,authority,id_persona) values(28,'ROLE_USER',27);
insert into roles(id,authority,id_persona) values(29,'ROLE_USER',28);
insert into roles(id,authority,id_persona) values(30,'ROLE_USER',29);
insert into roles(id,authority,id_persona) values(31,'ROLE_USER',30);
insert into roles(id,authority,id_persona) values(32,'ROLE_USER',31);
insert into roles(id,authority,id_persona) values(33,'ROLE_USER',32);

insert into roles(id,authority,id_persona) values(34,'ROLE_DOCENTE',21);
insert into roles(id,authority,id_persona) values(35,'ROLE_DOCENTE',22);
insert into roles(id,authority,id_persona) values(36,'ROLE_DOCENTE',23);
insert into roles(id,authority,id_persona) values(37,'ROLE_DOCENTE',24);
insert into roles(id,authority,id_persona) values(38,'ROLE_DOCENTE',25);
insert into roles(id,authority,id_persona) values(39,'ROLE_DOCENTE',26);
insert into roles(id,authority,id_persona) values(40,'ROLE_DOCENTE',27);
insert into roles(id,authority,id_persona) values(41,'ROLE_DOCENTE',28);
insert into roles(id,authority,id_persona) values(42,'ROLE_DOCENTE',29);
insert into roles(id,authority,id_persona) values(43,'ROLE_DOCENTE',30);
insert into roles(id,authority,id_persona) values(44,'ROLE_DOCENTE',31);
insert into roles(id,authority,id_persona) values(45,'ROLE_DOCENTE',32);


insert into roles(id,authority,id_persona) values(46,'ROLE_DIRPROGRAMA',25);
insert into roles(id,authority,id_persona) values(47,'ROLE_DIRPROGRAMA',23);
insert into roles(id,authority,id_persona) values(48,'ROLE_USER',33);
insert into roles(id,authority,id_persona) values(49,'ROLE_USER',34);
insert into roles(id,authority,id_persona) values(50,'ROLE_SUPERADMIN',34);
insert into roles(id,authority,id_persona) values(51,'ROLE_USER',35);


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

insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,id_tipo_beneficiario,lugar) values(1,'CIINATIC','2020-01-10','2020-01-15','2020-01-08','contenido generalll CIINATIC',10,'BBBBB',30000,26,6,'/uploads/educacion-continua/1/portada.jpg',21,1,'001',711,1,'Auditorio Biblioteca');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,id_tipo_beneficiario,lugar) values(2,'EISI','2020-02-14','2020-02-17','2020-02-14','contenido generalll EISI',100,'aaaa',52000,40,4,'/uploads/educacion-continua/2/portada.jpg',27,8,'002',711,2,'Auditorio Eustorgio Colmenares Batista');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,id_tipo_beneficiario,lugar) values(3,'SEMINARIO VIII ENCUENTRO SEMILLERO Y PROYECTO AULA','2020-02-25','2020-02-29','2020-02-24','contenido generalll de la bienal',10,'jdihsig',20000,35,1,'/uploads/educacion-continua/3/portada.png',29,3,'001',541,1,'Auditorio Postgrados');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,id_tipo_beneficiario,lugar) values(4,'EISI2','2020-03-05','2020-03-10','2020-03-07','contenido generalll del eisi2',10,'indgisng',34800,20,2,'/uploads/educacion-continua/4/portada.jpg',30,6,'001',1021,3,'Auditorio Jorge Luis Acero Jordán');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,id_tipo_beneficiario,lugar) values(5,'COMO INVESTIGAR CON DATOS ABIERTOS? GENERANDO CONOCIMIENTO CON DATOS ABIERTOS EN DIFERENTES PAGINAS','2020-04-20','2020-04-20','2020-04-18','contenido generalll de la bienal2',10,'nsiengtisent',78647,25,3,'/uploads/educacion-continua/5/portada.jpg',21,4,'001',521,1,'Hotel Casino Internacional');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,id_tipo_beneficiario,lugar) values(6,'CIINATIC3','2020-05-12','2020-05-14','2020-05-13','contenido generalll del ciinatic3',10,'isjign',640000,40,5,'/uploads/educacion-continua/6/portada.jpg',22,2,'001',711,5,'Auditorio Eustorgio Colmenares Batista');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,id_tipo_beneficiario,lugar) values(7,'RALLY MATEMATICO','2020-06-25','2020-06-25','2020-06-22','contenido generalll del rally',10,'sjdisjgi',25500,60,6,'/uploads/educacion-continua/7/portada.jpg',23,1,'002',923,3,'Auditorio Biblioteca');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,id_tipo_beneficiario,lugar) values(8,'II Ingeniería Civil','2020-07-10','2020-07-13','2020-07-09','contenido generalll del congreso de civil',10,'sjgisigj',100000,70,1,'/uploads/educacion-continua/8/portada.jpg',32,5,'002',412,4,'Hotel Bolivar Salon Principal');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,id_tipo_beneficiario,lugar) values(9,'Encuentro Internacional','2020-07-25','2020-07-30','2020-07-20','contenido generalll de ciencias aplicadas',10,'ijdsgijs',67800,10,2,'/uploads/educacion-continua/9/portada.jpg',31,6,'002',711,5,'Auditorio J.J Maldonado 4 Piso SA');
insert into educacion_continua(id,nombre,fecha_inicio,fecha_fin,fecha_lim_inscripcion,contenido_general,cant_max_participantes,resumen,costo,duracion,id_tipo_educacion_continua,imagen,id_docente,id_programa,consecutivo,id_clasificacion_cine,id_tipo_beneficiario,lugar) values(10,'Bienal3','2020-08-18','2020-08-21','2020-08-16','contenido generalll de la bienal3',10,'ijdisjg',34500,36,3,'/uploads/educacion-continua/10/portada.jpg',21,8,'003',417,3,'Auditorio Eustorgio Colmenares Batista');

insert into jornadas(id,hora_inicio,hora_fin,educacion_continua_id) values(1,'2016-04-08 08:00:00','2016-04-02 12:00:00',1);
insert into jornadas(id,hora_inicio,hora_fin,educacion_continua_id) values(2,'2016-04-08 14:00:00','2016-04-02 18:00:00',1);
insert into jornadas(id,hora_inicio,hora_fin,educacion_continua_id) values(3, '2019-10-10 08:00:00','2019-10-10 12:00:00',2);






insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (2, 'MTY0XzFfM18xXzEwODQ4NF8xNS0wNC0yMDIwIDA0OjA1', '/uploads/educacion-continua/3/qr-participantes/108484.png', '/uploads/educacion-continua/3/tarjetas-inscripcion/inscripcion_108484.jpg', 3, 7, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (3, 'MTUwXzNfNV8xXzEwODQ4NF8xNS0wNC0yMDIwIDA0OjA1', '/uploads/educacion-continua/5/qr-participantes/108484.png', '/uploads/educacion-continua/5/tarjetas-inscripcion/inscripcion_108484.jpg', 5, 7, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (4, 'MTExXzZfN18xXzEwODQ4NF8xNS0wNC0yMDIwIDA0OjA1', '/uploads/educacion-continua/7/qr-participantes/108484.png', '/uploads/educacion-continua/7/tarjetas-inscripcion/inscripcion_108484.jpg', 7, 7, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (5, 'MTExXzZfMV8xXzEwNjk0ODk0XzE1LTA0LTIwMjAgMDQ6MDc=', '/uploads/educacion-continua/1/qr-participantes/10694894.png', '/uploads/educacion-continua/1/tarjetas-inscripcion/inscripcion_10694894.jpg', 1, 5, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (6, 'MTM0XzNfMTBfMV8xMDY5NDg5NF8xNS0wNC0yMDIwIDA0OjA3', '/uploads/educacion-continua/10/qr-participantes/10694894.png', '/uploads/educacion-continua/10/tarjetas-inscripcion/inscripcion_10694894.jpg', 10, 5, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (7, 'MTIyXzJfNF8xXzEwNjk0ODk0XzE1LTA0LTIwMjAgMDQ6MDg=', '/uploads/educacion-continua/4/qr-participantes/10694894.png', '/uploads/educacion-continua/4/tarjetas-inscripcion/inscripcion_10694894.jpg', 4, 5, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (8, 'MTExXzZfN18xXzEwNjk0ODk0XzE1LTA0LTIwMjAgMDQ6MDg=', '/uploads/educacion-continua/7/qr-participantes/10694894.png', '/uploads/educacion-continua/7/tarjetas-inscripcion/inscripcion_10694894.jpg', 7, 5, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (9, 'MTIyXzJfOV8xXzEwNTE0ODRfMTUtMDQtMjAyMCAwNDowOQ==', '/uploads/educacion-continua/9/qr-participantes/1051484.png', '/uploads/educacion-continua/9/tarjetas-inscripcion/inscripcion_1051484.jpg', 9, 8, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (10, 'MTUwXzNfNV8xXzEwNTE0ODRfMTUtMDQtMjAyMCAwNDowOQ==', '/uploads/educacion-continua/5/qr-participantes/1051484.png', '/uploads/educacion-continua/5/tarjetas-inscripcion/inscripcion_1051484.jpg', 5, 8, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (11, 'MTExXzZfMV8xXzEwNTE0ODRfMTUtMDQtMjAyMCAwNDowOQ==', '/uploads/educacion-continua/1/qr-participantes/1051484.png', '/uploads/educacion-continua/1/tarjetas-inscripcion/inscripcion_1051484.jpg', 1, 8, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (12, 'MTExXzZfN18xXzEwNTE4NjlfMTUtMDQtMjAyMCAwNDoxMg==', '/uploads/educacion-continua/7/qr-participantes/1051869.png', '/uploads/educacion-continua/7/tarjetas-inscripcion/inscripcion_1051869.jpg', 7, 3, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (13, 'MTIyXzJfOV8xXzEwNTE4NjlfMTUtMDQtMjAyMCAwNDoxMg==', '/uploads/educacion-continua/9/qr-participantes/1051869.png', '/uploads/educacion-continua/9/tarjetas-inscripcion/inscripcion_1051869.jpg', 9, 3, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (14, 'MTM0XzNfMTBfMV8xMDUxODY5XzE1LTA0LTIwMjAgMDQ6MTI=', '/uploads/educacion-continua/10/qr-participantes/1051869.png', '/uploads/educacion-continua/10/tarjetas-inscripcion/inscripcion_1051869.jpg', 10, 3, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (15, 'MTUwXzNfNV8xXzEwNTE4NjlfMTUtMDQtMjAyMCAwNDoxMg==', '/uploads/educacion-continua/5/qr-participantes/1051869.png', '/uploads/educacion-continua/5/tarjetas-inscripcion/inscripcion_1051869.jpg', 5, 3, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (16, 'MTIyXzJfOV8xXzE1MTU0MzZfMTUtMDQtMjAyMCAwNDoxNA==', '/uploads/educacion-continua/9/qr-participantes/1515436.png', '/uploads/educacion-continua/9/tarjetas-inscripcion/inscripcion_1515436.jpg', 9, 2, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (17, 'MTM0XzNfMTBfMV8xNTE1NDM2XzE1LTA0LTIwMjAgMDQ6MTQ=', '/uploads/educacion-continua/10/qr-participantes/1515436.png', '/uploads/educacion-continua/10/tarjetas-inscripcion/inscripcion_1515436.jpg', 10, 2, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (18, 'MTExXzZfMV8xXzE1MTU0MzZfMTUtMDQtMjAyMCAwNDoxNA==', '/uploads/educacion-continua/1/qr-participantes/1515436.png', '/uploads/educacion-continua/1/tarjetas-inscripcion/inscripcion_1515436.jpg', 1, 2, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (19, 'MTIyXzJfNF8xXzE1MTU0MzZfMTUtMDQtMjAyMCAwNDoxNA==', '/uploads/educacion-continua/4/qr-participantes/1515436.png', '/uploads/educacion-continua/4/tarjetas-inscripcion/inscripcion_1515436.jpg', 4, 2, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (20, 'MTUwXzNfNV8xXzE1MTU0MzZfMTUtMDQtMjAyMCAwNDoxNA==', '/uploads/educacion-continua/5/qr-participantes/1515436.png', '/uploads/educacion-continua/5/tarjetas-inscripcion/inscripcion_1515436.jpg', 5, 2, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (21, 'MTI2XzFfOF8xXzE1OTg0N18xNS0wNC0yMDIwIDA0OjE2', '/uploads/educacion-continua/8/qr-participantes/159847.png', '/uploads/educacion-continua/8/tarjetas-inscripcion/inscripcion_159847.jpg', 8, 4, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (22, 'MTExXzZfMV8xXzE1OTg0N18xNS0wNC0yMDIwIDA0OjE2', '/uploads/educacion-continua/1/qr-participantes/159847.png', '/uploads/educacion-continua/1/tarjetas-inscripcion/inscripcion_159847.jpg', 1, 4, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (23, 'MTM0XzRfMl8xXzE1OTg0N18xNS0wNC0yMDIwIDA0OjE3', '/uploads/educacion-continua/2/qr-participantes/159847.png', '/uploads/educacion-continua/2/tarjetas-inscripcion/inscripcion_159847.jpg', 2, 4, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (24, 'MTIyXzJfNF8xXzE1OTg0N18xNS0wNC0yMDIwIDA0OjE3', '/uploads/educacion-continua/4/qr-participantes/159847.png', '/uploads/educacion-continua/4/tarjetas-inscripcion/inscripcion_159847.jpg', 4, 4, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (25, 'MTE1XzVfNl8xXzE1OTg0N18xNS0wNC0yMDIwIDA0OjE4', '/uploads/educacion-continua/6/qr-participantes/159847.png', '/uploads/educacion-continua/6/tarjetas-inscripcion/inscripcion_159847.jpg', 6, 4, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (26, 'MTY0XzFfM18xXzEwOTA1MDE1XzE1LTA0LTIwMjAgMDQ6MTk=', '/uploads/educacion-continua/3/qr-participantes/10905015.png', '/uploads/educacion-continua/3/tarjetas-inscripcion/inscripcion_10905015.jpg', 3, 1, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (27, 'MTIyXzJfNF8xXzEwOTA1MDE1XzE1LTA0LTIwMjAgMDQ6MTk=', '/uploads/educacion-continua/4/qr-participantes/10905015.png', '/uploads/educacion-continua/4/tarjetas-inscripcion/inscripcion_10905015.jpg', 4, 1, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (28, 'MTE1XzVfNl8xXzEwOTA1MDE1XzE1LTA0LTIwMjAgMDQ6MTk=', '/uploads/educacion-continua/6/qr-participantes/10905015.png', '/uploads/educacion-continua/6/tarjetas-inscripcion/inscripcion_10905015.jpg', 6, 1, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (29, 'MTI2XzFfOF8xXzEwOTA1MDE1XzE1LTA0LTIwMjAgMDQ6MTk=', '/uploads/educacion-continua/8/qr-participantes/10905015.png', '/uploads/educacion-continua/8/tarjetas-inscripcion/inscripcion_10905015.jpg', 8, 1, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (30, 'MTY0XzFfM18xXzEwNTE4NDk4XzE1LTA0LTIwMjAgMDQ6MjE=', '/uploads/educacion-continua/3/qr-participantes/10518498.png', '/uploads/educacion-continua/3/tarjetas-inscripcion/inscripcion_10518498.jpg', 3, 6, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (31, 'MTE1XzVfNl8xXzEwNTE4NDk4XzE1LTA0LTIwMjAgMDQ6MjE=', '/uploads/educacion-continua/6/qr-participantes/10518498.png', '/uploads/educacion-continua/6/tarjetas-inscripcion/inscripcion_10518498.jpg', 6, 6, 1);
insert into participantes(id,codigoqr,imagen_codigo_qr,tarjeta_inscripcion,educacion_continua_id,id_persona,id_tipo_participante) values (32, 'MTIyXzJfOV8xXzEwNTE4NDk4XzE1LTA0LTIwMjAgMDQ6MjE=', '/uploads/educacion-continua/9/qr-participantes/10518498.png', '/uploads/educacion-continua/9/tarjetas-inscripcion/inscripcion_10518498.jpg', 9, 6, 1);


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

insert into diplomas(id,imagen_plantilla) value (1,null);

insert into elementos_diploma(id,x,y) value(1,80,120); /*imagen logo1*/
insert into elementos_diploma(id,x,y) value(2,750,120); /*imagen logo2*/
insert into elementos_diploma(id,x,y) value(3,485,170); /*texto titulo*/
insert into elementos_diploma(id,x,y) value(4,485,220); /*texto subtitulo*/

insert into elementos_diploma(id,x,y) value(5,145,600); 
insert into elementos_diploma(id,x,y) value(6,530,600); 
insert into elementos_diploma(id,x,y) value(7,80,150);
insert into elementos_diploma(id,x,y) value(8,765,150); 
insert into elementos_diploma(id,x,y) value(9,485,180); 
insert into elementos_diploma(id,x,y) value(10,485,200); 

insert into imagenes_diploma(id_elemento,ruta,ancho,alto) value(1,"/img/plantilla_diploma/logo1.jpg",150,150);
insert into imagenes_diploma(id_elemento,ruta,ancho,alto) value(2,"/img/plantilla_diploma/logo2.jpg",150,150);

insert into imagenes_diploma (id_elemento,alto,ancho,ruta,id_diploma) value (7,0,0,'/uploads/educacion-continua/5/plantilla-diploma/senqsrlfibqjontwtzfd.png',1);
insert into imagenes_diploma (id_elemento,alto,ancho,ruta,id_diploma) value (8,0,0,'/uploads/educacion-continua/5/plantilla-diploma/fxybjytzzzppbdzagnjh.png',1);


insert into textos_diploma(id_elemento,categoria,texto) value(3,"titulo","");
insert into textos_diploma(id_elemento,categoria,texto) value(4,"subtitulo","");
insert into textos_diploma(id_elemento,categoria,texto,id_diploma) value (9,'titulo', 'Facultad de EducaasifciÃ³n, Artes y Humanidades',1);
insert into textos_diploma(id_elemento,categoria,texto,id_diploma) value (10,'subtitulo','Programa de Arquitectuigjsra',1);


insert into firmas_diploma (id_elemento,cargo,imagen_firma_digital,nombre,x_cargo,x_nombre,y_cargo,y_nombre,id_diploma) value (5,'Dir. Programa de Sistemas','/uploads/educacion-continua/5/plantilla-diploma/lbexmznvprsuvzxckpev.png','PhD. Judith del Pilar Rodriguez Tenjo',280,280,627,615,1);
insert into firmas_diploma (id_elemento,cargo,imagen_firma_digital,nombre,x_cargo,x_nombre,y_cargo,y_nombre,id_diploma) value (6,'Docente Departamento Sistemas e InformÃ¡tica','/uploads/educacion-continua/5/plantilla-diploma/xxhcsflkjclmycsivzlt.png','PhD. Marco Antonio Adarme Jaimes',665,665,627,615,1);

update educacion_continua set id_diploma='1' where id='5';
