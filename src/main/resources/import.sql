insert into generos(id,genero,descripcion) values (1,'Masculino','Hombre');
insert into generos(id,genero,descripcion) values (2,'Femenino','Mujer');
insert into estados_civiles(id,estado_civil) values (1,'Soltero(a)');
insert into estados_civiles(id,estado_civil) values (2,'Casado(a)');
insert into estados_civiles(id,estado_civil) values (3,'Divorciado(a)');
insert into estados_civiles(id,estado_civil) values (4,'Viudo(a)');
insert into estados_civiles(id,estado_civil) values (5,'Unión Libre');
insert into estados_civiles(id,estado_civil) values (6,'Religioso(a)');
insert into estados_civiles(id,estado_civil) values (8,'Separado(a)');
insert into facultades(id,facultad) values (1,'Ingeniería');
insert into departamentos(id,departamento,id_facultad) values (1,'Sistemas e Informática',1);

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


insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (111111,'atalaya','jhocelduvanst@ufps.edu.co','10905015','Jhocel','Duvan','Suescun','Torres','32184602',0,0,1,0,0,1,'170','54','54001','1997-01-03','2015-01-06',1,1,'3','1608851065549');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (222222,'limonar','javiereduardocv@ufps.edu.co','1515436','Javier','Eduardo','Calderon','Villamizar','32318485',0,1,0,0,0,1,'170','54','54239','1948-10-22','1966-07-04',1,1,'2','1608851099799');
insert into personas (id,direccion,email,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,is_estudiante,is_docente,is_administrativo,is_graduado,is_externo,id_tipo_documento,id_pais_nacimiento,id_departamento_nacimiento,id_municipio_nacimiento,fecha_nacimiento,fecha_expedicion_documento,id_estado_civil,id_genero,ids_tipo_persona,id_acceso) values (333333,'ceci','jecalderonn1@gmail.com','1051869','Janes',null,'Duran','Sierra','31482102',1,0,0,0,0,1,'170','54','54001','1997-01-03','2015-01-06',2,1,'1','1608851124501');


insert into docentes (id_persona,codigo,id_departamento,estado) values (222222,'05848',1,1);

insert into programas(id,codigo,programa,id_facultad,id_director) values(1,'115','Ingeniería de Sistemas',1,222222);



insert into estudiantes (codigo,id_persona,id_programa,estado) values ('1442777',333333,1,1);

insert into administrativos (id_persona,cargo,estado) values (111111,'vicerector de extension',1);


insert into roles (id, authority) values (1,'ROLE_SUPERADMIN');
insert into roles (id, authority) values (2,'ROLE_MANAECCU');
insert into roles (id, authority) values (3,'ROLE_MANPEOPLE');
insert into roles (id, authority) values (4,'ROLE_ATTENDANCE');
insert into roles (id, authority) values (5,'ROLE_USER');
insert into roles (id, authority) values (6,'ROLE_SNIES');

##roles superadmin
insert into personas_x_roles (id_persona, id_rol) values (111111,1);
insert into personas_x_roles (id_persona, id_rol) values (111111,5);
insert into personas_x_roles (id_persona, id_rol) values (333333,1);
insert into personas_x_roles (id_persona, id_rol) values (333333,5);

##rol gallardo-civil
insert into personas_x_roles (id_persona, id_rol) values (222222,2);
insert into personas_x_roles (id_persona, id_rol) values (222222,3);
insert into personas_x_roles (id_persona, id_rol) values (222222,5);
insert into personas_x_roles (id_persona, id_rol) values (222222,6);

insert into roles_personas_programas_ec (id_persona, id_rol, id_programa) values (222222,2,1);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (222222,3,1);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (222222,3,3);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (222222,3,4);
insert into rol_persona_tip_pers (id_persona, id_rol, id_tipo_persona) values (222222,3,5);
insert into rol_persona_programa_per (id_persona, id_rol, id_tipo_persona,id_programa) values (222222,3,1,1);
insert into rol_persona_programa_per (id_persona, id_rol, id_tipo_persona,id_programa) values (222222,3,4,1);


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
insert into clasificacion_cine(id,clasificacion_cine) values(613,'Desarrollo y anÁlisis de software y aplicaciones');
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
