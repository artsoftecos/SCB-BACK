----------------INSERTING Document type DATA ----------------------------
INSERT INTO scb.tipo_documento (nombre)
SELECT * FROM (SELECT 'Cedula') AS tmp
WHERE NOT EXISTS (
    SELECT nombre FROM scb.tipo_documento WHERE nombre = 'Cedula'
) LIMIT 1;
INSERT INTO scb.tipo_documento (nombre)
SELECT * FROM (SELECT 'Cedula extranjería') AS tmp
WHERE NOT EXISTS (
    SELECT nombre FROM scb.tipo_documento WHERE nombre = 'Cedula extranjería'
) LIMIT 1;
----------------INSERTING SOLICITANTE--------------------------------------
--- Enabled
INSERT INTO scb.usuario (email, enabled, password, token)
SELECT * FROM (SELECT 'solicitanteApproved@artsoft.com', 1, '$2a$10$2tkTPQBzX5eyud2uAwDPtOws3KV.IGR/Adka/MkN4Mbo/u6GP5fwa', null) AS tmp
WHERE NOT EXISTS (
    SELECT email FROM scb.usuario WHERE email = 'solicitanteApproved@artsoft.com'
) LIMIT 1;

INSERT INTO scb.tipo_usuario (rol, email)
SELECT * FROM (SELECT 'ROLE_APPLICANT', 'solicitanteApproved@artsoft.com') AS tmp
WHERE NOT EXISTS (
    SELECT email FROM scb.tipo_usuario WHERE email = 'solicitanteApproved@artsoft.com'
) LIMIT 1;

INSERT INTO scb.solicitante (correo, direccion, celular, fecha_registro, numero_documento, primer_apellido, primer_nombre, segundo_apellido, segunda_nombre, telefono, id_tipo_documento, id_usuario)
SELECT * FROM (SELECT 'solicitanteApproved@artsoft.com' as correo, null as direccion, null as celular, '2017-09-02 12:31:51', '1014207335', 'Alonso', 'Walter', 'Roa', 'Javier', null as telefono, '1', 'solicitanteApproved@artsoft.com' as id_usuario) AS tmp
WHERE NOT EXISTS (
    SELECT correo FROM scb.solicitante WHERE correo = 'solicitanteApproved@artsoft.com'
) LIMIT 1;
-- Rejected or pending
INSERT INTO scb.usuario (email, enabled, password, token)
SELECT * FROM (SELECT 'solicitanteRejected@artsoft.com', 0, '$2a$10$2tkTPQBzX5eyud2uAwDPtOws3KV.IGR/Adka/MkN4Mbo/u6GP5fwa', null) AS tmp
WHERE NOT EXISTS (
    SELECT email FROM scb.usuario WHERE email = 'solicitanteRejected@artsoft.com'
) LIMIT 1;

INSERT INTO scb.tipo_usuario (rol, email)
SELECT * FROM (SELECT 'ROLE_APPLICANT', 'solicitanteRejected@artsoft.com') AS tmp
WHERE NOT EXISTS (
    SELECT email FROM scb.tipo_usuario WHERE email = 'solicitanteRejected@artsoft.com'
) LIMIT 1;

INSERT INTO scb.solicitante (correo, direccion, celular, fecha_registro, numero_documento, primer_apellido, primer_nombre, segundo_apellido, segunda_nombre, telefono, id_tipo_documento, id_usuario)
SELECT * FROM (SELECT 'solicitanteRejected@artsoft.com' as correo, null as direccion, null as celular, '2017-09-02 12:31:51', '1014207335', 'Alonso', 'Walter', 'Roa', 'Javier', null as telefono, '1', 'solicitanteRejected@artsoft.com' as id_usuario) AS tmp
WHERE NOT EXISTS (
    SELECT correo FROM scb.solicitante WHERE correo = 'solicitanteRejected@artsoft.com'
) LIMIT 1;

insert into scb.usuario (email, enabled, password) values ('SolicitanteApproved2@artsoft.com', 1, '$2a$10$2tkTPQBzX5eyud2uAwDPtOws3KV.IGR/Adka/MkN4Mbo/u6GP5fwa');
insert into scb.usuario (email, enabled, password) values ('SolicitanteApproved3@artsoft.com', 1, '$2a$10$2tkTPQBzX5eyud2uAwDPtOws3KV.IGR/Adka/MkN4Mbo/u6GP5fwa');
insert into scb.usuario (email, enabled, password) values ('SolicitanteApproved4@artsoft.com', 1, '$2a$10$2tkTPQBzX5eyud2uAwDPtOws3KV.IGR/Adka/MkN4Mbo/u6GP5fwa');
insert into scb.usuario (email, enabled, password) values ('SolicitanteApproved5@artsoft.com', 1, '$2a$10$2tkTPQBzX5eyud2uAwDPtOws3KV.IGR/Adka/MkN4Mbo/u6GP5fwa');


insert into scb.solicitante (correo, fecha_registro, numero_documento, primer_apellido, primer_nombre, segundo_apellido, segunda_nombre, id_tipo_documento, id_usuario)
values ('solicitanteApproved2@artsoft.com', '2017-09-02 12:31:51', '1014207335', 'Quintero', 'Pineros', 'Miguel', 'Angel', 1, 'solicitanteApproved2@artsoft.com');

insert into scb.solicitante (correo, fecha_registro, numero_documento, primer_apellido, primer_nombre, segundo_apellido, segunda_nombre, id_tipo_documento, id_usuario)
values ('solicitanteApproved3@artsoft.com', '2017-09-02 12:31:51', '1014207335', 'Quintero', 'Pineros', 'Luisa', 'Fernanda', 1, 'solicitanteApproved3@artsoft.com');

insert into scb.solicitante (correo, fecha_registro, numero_documento, primer_apellido, primer_nombre, segundo_apellido, segunda_nombre, id_tipo_documento, id_usuario)
values ('solicitanteApproved4@artsoft.com', '2017-09-02 12:31:51', '1014207335', 'Quintero', 'Diaz', 'Rafael', '', 1, 'solicitanteApproved4@artsoft.com');

insert into scb.solicitante (correo, fecha_registro, numero_documento, primer_apellido, primer_nombre, segundo_apellido, segunda_nombre, id_tipo_documento, id_usuario)
values ('solicitanteApproved5@artsoft.com', '2017-09-02 12:31:51', '1014207335', 'Gomez', 'Jimenez', 'Carlos', 'Alberto', 1, 'solicitanteApproved5@artsoft.com');
----------------INSERTING ADMIN (no necesita otra tabla como lo es oferente y solicitante?)--------------------------------------
INSERT INTO scb.usuario (email, enabled, password, token)
SELECT * FROM (SELECT 'admin@artsoft.com', 1, '$2a$10$2tkTPQBzX5eyud2uAwDPtOws3KV.IGR/Adka/MkN4Mbo/u6GP5fwa', null) AS tmp
WHERE NOT EXISTS (
    SELECT email FROM scb.usuario WHERE email = 'admin@artsoft.com'
) LIMIT 1;

INSERT INTO scb.tipo_usuario (rol, email)
SELECT * FROM (SELECT 'ROLE_ADMINISTRATOR', 'admin@artsoft.com') AS tmp
WHERE NOT EXISTS (
    SELECT email FROM scb.tipo_usuario WHERE email = 'admin@artsoft.com'
) LIMIT 1;
------------------INSERTING OFFERENT STATES-------------------
INSERT INTO scb.estado_oferente (descripcion)
SELECT * FROM (SELECT 'PENDIENTE') AS tmp
WHERE NOT EXISTS (
    SELECT descripcion FROM scb.estado_oferente WHERE descripcion = 'PENDIENTE'
) LIMIT 1;
INSERT INTO scb.estado_oferente (descripcion)
SELECT * FROM (SELECT 'APROBADO') AS tmp
WHERE NOT EXISTS (
    SELECT descripcion FROM scb.estado_oferente WHERE descripcion = 'APROBADO'
) LIMIT 1;
INSERT INTO scb.estado_oferente (descripcion)
SELECT * FROM (SELECT 'RECHAZADO') AS tmp
WHERE NOT EXISTS (
    SELECT descripcion FROM scb.estado_oferente WHERE descripcion = 'RECHAZADO'
) LIMIT 1;

--------------INSERTING Offerer approved--------------------------------------
INSERT INTO scb.usuario (email, enabled, password, token)
SELECT * FROM (SELECT 'OffererApproved@artsoft.com', 1, '$2a$10$2tkTPQBzX5eyud2uAwDPtOws3KV.IGR/Adka/MkN4Mbo/u6GP5fwa', null) AS tmp
WHERE NOT EXISTS (
    SELECT email FROM scb.usuario WHERE email = 'OffererApproved@artsoft.com'
) LIMIT 1;

INSERT INTO scb.tipo_usuario (rol, email)
SELECT * FROM (SELECT 'ROLE_OFFERER', 'OffererApproved@artsoft.com') AS tmp
WHERE NOT EXISTS (
    SELECT email FROM scb.tipo_usuario WHERE email = 'OffererApproved@artsoft.com'
) LIMIT 1;

INSERT INTO scb.oferente (correo, direccion, fecha_registro, representante_legal, nombre, nit, telefono, id_estado, id_usuario)
SELECT * FROM (SELECT 'OffererApproved@artsoft.com' as correo, null as direccion, NOW(), 'representante offerer approved', 'Offerer 1','1010101010', '4323232' as telefono, 2, 'OffererApproved@artsoft.com' as id_usuario) AS tmp
WHERE NOT EXISTS (
    SELECT correo FROM scb.oferente WHERE correo = 'OffererApproved@artsoft.com'
) LIMIT 1;
------------------INSERTING Offerer rejected--------------------------------------
INSERT INTO scb.usuario (email, enabled, password, token)
SELECT * FROM (SELECT 'offererRejected@artsoft.com', 1, '$2a$10$2tkTPQBzX5eyud2uAwDPtOws3KV.IGR/Adka/MkN4Mbo/u6GP5fwa', null) AS tmp
WHERE NOT EXISTS (
    SELECT email FROM scb.usuario WHERE email = 'offererRejected@artsoft.com'
) LIMIT 1;

INSERT INTO scb.tipo_usuario (rol, email)
SELECT * FROM (SELECT 'ROLE_OFFERER', 'offererRejected@artsoft.com') AS tmp
WHERE NOT EXISTS (
    SELECT email FROM scb.tipo_usuario WHERE email = 'offererRejected@artsoft.com'
) LIMIT 1;

INSERT INTO scb.oferente (correo, direccion, fecha_registro, representante_legal, nombre, nit, telefono, id_estado, id_usuario)
SELECT * FROM (SELECT 'offererRejected@artsoft.com' as correo, null as direccion, NOW(), 'representante offerer rejected', 'Offerer 2', '1010101011', '4323233' as telefono, 3, 'offererRejected@artsoft.com' as id_usuario) AS tmp
WHERE NOT EXISTS (
    SELECT correo FROM scb.oferente WHERE correo = 'offererRejected@artsoft.com'
) LIMIT 1;
------------------INSERTING Offerer pending--------------------------------------
INSERT INTO scb.usuario (email, enabled, password, token)
SELECT * FROM (SELECT 'offererPending@artsoft.com', 1, '$2a$10$2tkTPQBzX5eyud2uAwDPtOws3KV.IGR/Adka/MkN4Mbo/u6GP5fwa', null) AS tmp
WHERE NOT EXISTS (
    SELECT email FROM scb.usuario WHERE email = 'offererPending@artsoft.com'
) LIMIT 1;

INSERT INTO scb.tipo_usuario (rol, email)
SELECT * FROM (SELECT 'ROLE_OFFERER', 'offererPending@artsoft.com') AS tmp
WHERE NOT EXISTS (
    SELECT email FROM scb.tipo_usuario WHERE email = 'offererPending@artsoft.com'
) LIMIT 1;

INSERT INTO scb.oferente (correo, direccion, fecha_registro, representante_legal, nombre, nit, telefono, id_estado, id_usuario)
SELECT * FROM (SELECT 'offererPending@artsoft.com' as correo, null as direccion, NOW(), 'representante offerer pending', 'Offerer 3', '1010101012', '4323234' as telefono, 1, 'offererPending@artsoft.com' as id_usuario) AS tmp
WHERE NOT EXISTS (
    SELECT correo FROM scb.oferente WHERE correo = 'offererPending@artsoft.com'
) LIMIT 1;
------------------INSERTING KEY DATA-------------------
insert into credencial (id,llave,valor)
 values (1, 'aws_access_key_id', '');
insert into credencial (id,llave,valor)
 values (2, 'aws_secret_access_key', '');
------------------INSERTING CONVOCATORY TYPE-------------------------------------
INSERT INTO scb.tipo_convocatoria (nombre)
SELECT * FROM (SELECT 'Beca Completa' as nombre) AS tmp
WHERE NOT EXISTS (
    SELECT correo FROM scb.oferente WHERE correo = 'Beca Completa'
) LIMIT 1;

INSERT INTO scb.tipo_convocatoria (nombre)
SELECT * FROM (SELECT 'Beca Parcial' as nombre) AS tmp
WHERE NOT EXISTS (
    SELECT correo FROM scb.oferente WHERE correo = 'Beca Parcial'
) LIMIT 1;

INSERT INTO scb.tipo_convocatoria (nombre)
SELECT * FROM (SELECT 'Subsidio investigación' as nombre) AS tmp
WHERE NOT EXISTS (
    SELECT correo FROM scb.oferente WHERE correo = 'Subsidio investigación'
) LIMIT 1;

INSERT INTO scb.tipo_convocatoria (nombre)
SELECT * FROM (SELECT 'Intercambio' as nombre) AS tmp
WHERE NOT EXISTS (
    SELECT correo FROM scb.oferente WHERE correo = 'Intercambio'
) LIMIT 1;

INSERT INTO scb.tipo_convocatoria (nombre)
SELECT * FROM (SELECT 'Pasantía' as nombre) AS tmp
WHERE NOT EXISTS (
    SELECT correo FROM scb.oferente WHERE correo = 'Pasantía'
) LIMIT 1;

INSERT INTO scb.tipo_convocatoria (nombre)
SELECT * FROM (SELECT 'Crédito' as nombre) AS tmp
WHERE NOT EXISTS (
    SELECT correo FROM scb.oferente WHERE correo = 'Crédito'
) LIMIT 1;

----------INSERTING CONVOCATORY STATE----------------

INSERT INTO scb.estado_convocatoria (nombre) values ('Creada');
INSERT INTO scb.estado_convocatoria (nombre) values ('Cerrada');
INSERT INTO scb.estado_convocatoria (nombre) values ('Abierta');
INSERT INTO scb.estado_convocatoria (nombre) values ('Publicada');


--------INSERTING PLACE STATE------------------------------
insert into scb.estado_plaza (nombre) values ('Pendiente');
insert into scb.estado_plaza (nombre) values ('Aceptada');
insert into scb.estado_plaza (nombre) values ('Rechazada');


------INSERTING ----------------------------
insert into scb.estado_solicitante_por_fase (nombre) values ('Aprobado');
insert into scb.estado_solicitante_por_fase (nombre) values ('Pendiente');
insert into scb.estado_solicitante_por_fase (nombre) values ('Rechazado');

---- INSERTING CONVOCATORY----


insert into scb.convocatoria (descripcion, nombre, numero_beneficiarios, fecha_publicacion_resultados, id_estado, id_tipo, mail_oferente)
values ('Becas para estudios en españa 1', 'Estudios en España 1', 1, '2017-09-01', 1, 1, 'OffererApproved@artsoft.com');

insert into scb.convocatoria (descripcion, nombre, numero_beneficiarios, fecha_publicacion_resultados, id_estado, id_tipo, mail_oferente)
values ('Becas para estudios en españa 2', 'Estudios en España 2', 2, '2017-09-01', 1, 2, 'OffererApproved@artsoft.com');

insert into scb.convocatoria (descripcion, nombre, numero_beneficiarios, fecha_publicacion_resultados, id_estado, id_tipo, mail_oferente)
values ('Becas para estudios en españa 3', 'Estudios en España 3', 3, '2017-09-01', 1, 3, 'OffererApproved@artsoft.com');

insert into scb.convocatoria (descripcion, nombre, numero_beneficiarios, fecha_publicacion_resultados, id_estado, id_tipo, mail_oferente)
values ('Becas para estudios en españa 4', 'Estudios en España 3', 4, '2017-09-01', 1, 4, 'OffererApproved@artsoft.com');

-----INSERTING PHASE-----------------

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase 1 Conv 1','2017-10-01', '2017-10-03', '2017-10-15', '2017-10-25', 'Fase 1 Conv 1', 1);

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase 2 Conv 1','2017-10-26', '2017-10-30', '2017-11-01', '2017-11-05', 'Fase 2 Conv 1', 1);

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase 3 Conv 1','2017-11-06', '2017-11-09', '2017-11-10', '2017-11-20', 'Fase 3 Conv 1', 1);

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase 4 Conv 1','2017-11-21', '2017-11-23', '2017-11-24', '2017-11-30', 'Fase 4 Conv 1', 1);

----

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase 1 Conv 2','2017-09-01', '2017-09-03', '2017-09-15', '2017-09-25', 'Fase 1 Conv 2', 2);

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase 2 Conv 2','2017-10-01', '2017-10-03', '2017-10-15', '2017-10-25', 'Fase 2 Conv 2', 2);

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase 3 Conv 2','2017-11-06', '2017-11-09', '2017-11-10', '2017-11-20', 'Fase 3 Conv 2', 2);

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase 4 Conv 2','2017-11-21', '2017-11-23', '2017-11-24', '2017-11-30', 'Fase 4 Conv 2', 2);


-----

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase 1 Conv 3','2017-09-01', '2017-09-03', '2017-09-10', '2017-09-11', 'Fase 1 Conv 3', 3);

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase 2 Conv 3','2017-09-11', '2017-09-13', '2017-09-15', '2017-09-30', 'Fase 2 Conv 3', 3);

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase 3 Conv 3','2017-10-01', '2017-10-03', '2017-10-15', '2017-10-25', 'Fase 3 Conv 3', 3);

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase 4 Conv 3','2017-11-21', '2017-11-23', '2017-11-24', '2017-11-30', 'Fase 4 Conv 3', 3);

----------

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase 1 Conv 4','2017-09-01', '2017-09-03', '2017-09-10', '2017-09-11', 'Fase 1 Conv 4', 4);

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase 2 Conv 4','2017-09-11', '2017-09-13', '2017-09-15', '2017-09-20', 'Fase 2 Conv 4', 4);

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase 3 Conv 4','2017-09-21', '2017-09-23', '2017-09-26', '2017-09-30', 'Fase 3 Conv 4', 4);

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase 4 Conv 4','2017-10-01', '2017-10-03', '2017-10-15', '2017-10-25', 'Fase 4 Conv 4', 4);


----------INSERTING APPLICANTS PER PHASE------
insert into scb.solicitante_por_fase (mail_solicitante, id_estado, id_fase)
values ('solicitanteApproved@artsoft.com', 1,  2);
insert into scb.solicitante_por_fase (mail_solicitante, id_estado, id_fase)
values ('solicitanteRejected@artsoft.com', 2,  6);
insert into scb.solicitante_por_fase (mail_solicitante, id_estado, id_fase)
values ('solicitanteApproved4@artsoft.com', 2,  1);
-------------Field types-----------------------
insert into scb.tipo_campo (nombre, orden) --1
values ('Texto corto', 1);
insert into scb.tipo_campo (nombre, orden) --2
values ('Texto Largo', 2);
insert into scb.tipo_campo (nombre, orden) --3
values ('Archivo', 3);
insert into scb.tipo_campo (nombre, orden) --4
values ('Fecha / Calendario', 4);
insert into scb.tipo_campo (nombre, orden) --5
values ('Numérico', 5);
insert into scb.tipo_campo (nombre, orden)  --6
values ('Correo', 6);
insert into scb.tipo_campo (nombre, orden) --7
values ('Url', 7);

------------- type validations ----------------
insert into scb.tipo_validacion (expresion, nombre) --1
values ('>', 'Mayor');
insert into scb.tipo_validacion (expresion, nombre) --2
values ('<', 'Menor');
insert into scb.tipo_validacion (expresion, nombre) --3
values ('<', 'Entre');
insert into scb.tipo_validacion (expresion, nombre) --4
values ('pdf,jpg', 'TipoArchivo');
insert into scb.tipo_validacion (expresion, nombre) --5
values ('', 'Celular');
insert into scb.tipo_validacion (expresion, nombre) --6
values ('', 'Expresion regular');

------ Field type validations -----------
insert into scb.validacion_tipo_campo (id_tipo_campo, id_tipo_validacion)
values (1,3);
insert into scb.validacion_tipo_campo (id_tipo_campo, id_tipo_validacion)
values (1,5);
insert into scb.validacion_tipo_campo (id_tipo_campo, id_tipo_validacion)
values (2,3);
insert into scb.validacion_tipo_campo (id_tipo_campo, id_tipo_validacion)
values (3,4);
insert into scb.validacion_tipo_campo (id_tipo_campo, id_tipo_validacion)
values (4, 1);
insert into scb.validacion_tipo_campo (id_tipo_campo, id_tipo_validacion)
values (4,2);
insert into scb.validacion_tipo_campo (id_tipo_campo, id_tipo_validacion)
values (4,3);
insert into scb.validacion_tipo_campo (id_tipo_campo, id_tipo_validacion)
values (5, 1);
insert into scb.validacion_tipo_campo (id_tipo_campo, id_tipo_validacion)
values (5, 2);
insert into scb.validacion_tipo_campo (id_tipo_campo, id_tipo_validacion)
values (5, 3);
insert into scb.validacion_tipo_campo (id_tipo_campo, id_tipo_validacion)
values (6, 6);
insert into scb.validacion_tipo_campo (id_tipo_campo, id_tipo_validacion)
values (7, 6);