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

insert into scb.tipo_usuario (rol, email) values ('ROLE_APPLICANT', 'SolicitanteApproved2@artsoft.com');
insert into scb.tipo_usuario (rol, email) values ('ROLE_APPLICANT', 'SolicitanteApproved3@artsoft.com');
insert into scb.tipo_usuario (rol, email) values ('ROLE_APPLICANT', 'SolicitanteApproved4@artsoft.com');
insert into scb.tipo_usuario (rol, email) values ('ROLE_APPLICANT', 'SolicitanteApproved5@artsoft.com');

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
insert into scb.estado_solicitante_por_fase (nombre) values ('PendienteRegistroDatos');

---- INSERTING CONVOCATORY----


insert into scb.convocatoria (descripcion, nombre, numero_beneficiarios, fecha_publicacion_resultados, id_estado, id_tipo, mail_oferente)
values ('Becas para estudios en españa 1', 'Estudios en España 1', 1, '2017-09-01', 1, 1, 'OffererApproved@artsoft.com');

insert into scb.convocatoria (descripcion, nombre, numero_beneficiarios, fecha_publicacion_resultados, id_estado, id_tipo, mail_oferente)
values ('Becas para estudios en españa 2', 'Estudios en España 2', 2, '2017-09-01', 1, 2, 'OffererApproved@artsoft.com');

insert into scb.convocatoria (descripcion, nombre, numero_beneficiarios, fecha_publicacion_resultados, id_estado, id_tipo, mail_oferente)
values ('Becas para estudios en españa 3', 'Estudios en España 3', 3, '2017-09-01', 1, 3, 'OffererApproved@artsoft.com');

insert into scb.convocatoria (descripcion, nombre, numero_beneficiarios, fecha_publicacion_resultados, id_estado, id_tipo, mail_oferente)
values ('Becas para estudios en españa 4', 'Estudios en España 3', 4, '2017-09-01', 1, 4, 'OffererApproved@artsoft.com');
insert into scb.convocatoria (descripcion, nombre, numero_beneficiarios, fecha_publicacion_resultados, id_estado, id_tipo, mail_oferente)
values ('Becas para estudios en españa 5', 'Estudios en España 5', 4, '2017-09-01', 1, 4, 'OffererApproved@artsoft.com');
insert into scb.convocatoria (descripcion, nombre, numero_beneficiarios, fecha_publicacion_resultados, id_estado, id_tipo, mail_oferente)
values ('Becas para estudios en españa 6', 'Estudios en España 6', 4, '2017-09-01', 1, 4, 'OffererApproved@artsoft.com');

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
values ('solicitanteApproved@artsoft.com', 1,  8);
insert into scb.solicitante_por_fase (mail_solicitante, id_estado, id_fase)
values ('solicitanteRejected@artsoft.com', 2,  8);
insert into scb.solicitante_por_fase (mail_solicitante, id_estado, id_fase)
values ('solicitanteApproved4@artsoft.com', 2,  1);
/*insert into scb.solicitante_por_fase (mail_solicitante, id_estado, id_fase)
values ('solicitanteApproved3@artsoft.com', 4,  1);*/
-------------Field types-----------------------
insert into scb.tipo_campo (nombre) --1
values ('Texto corto');
insert into scb.tipo_campo (nombre) --2
values ('Texto Largo');
insert into scb.tipo_campo (nombre) --3
values ('Archivo');
insert into scb.tipo_campo (nombre) --4
values ('Fecha / Calendario');
insert into scb.tipo_campo (nombre) --5
values ('Numérico');
insert into scb.tipo_campo (nombre)  --6
values ('Correo');
insert into scb.tipo_campo (nombre) --7
values ('Url');

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
values (1,1);
insert into scb.validacion_tipo_campo (id_tipo_campo, id_tipo_validacion)
values (1,2);
insert into scb.validacion_tipo_campo (id_tipo_campo, id_tipo_validacion)
values (2,1);
insert into scb.validacion_tipo_campo (id_tipo_campo, id_tipo_validacion)
values (2,2);
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
values (5,5);
insert into scb.validacion_tipo_campo (id_tipo_campo, id_tipo_validacion)
values (6, 6);
insert into scb.validacion_tipo_campo (id_tipo_campo, id_tipo_validacion)
values (7, 6);

-------------INSERTING PLACES --------

insert into scb.plaza (mail_solicitante, id_convocatoria, id_estado)
values ('solicitanteApproved3@artsoft.com', 3, 1);
insert into scb.plaza (mail_solicitante, id_convocatoria, id_estado)
values ('solicitanteApproved3@artsoft.com', 5, 2);
insert into scb.plaza (mail_solicitante, id_convocatoria, id_estado)
values ('solicitanteApproved3@artsoft.com', 6, 3);

------------INSERTING POSTULATIONS-----------

insert into scb.postulacion (fecha_postulacion, mail_solicitante, id_convocatoria)
values ('2017-11-11', 'solicitanteApproved3@artsoft.com', 1);

insert into scb.postulacion (fecha_postulacion, mail_solicitante, id_convocatoria)
values ('2017-11-11', 'solicitanteApproved2@artsoft.com', 3);

insert into scb.postulacion (fecha_postulacion, mail_solicitante, id_convocatoria)
values ('2017-11-11', 'solicitanteApproved2@artsoft.com', 1);

insert into scb.postulacion (fecha_postulacion, mail_solicitante, id_convocatoria)
values ('2017-11-11', 'solicitanteApproved3@artsoft.com', 2);


------ Test case aplicar -------------------
-- insertando usuario de aplicar 
INSERT INTO scb.usuario (email, enabled, password, token)
SELECT * FROM (SELECT 'solicitanteAplicarTest@artsoft.com', 1, '$2a$10$2tkTPQBzX5eyud2uAwDPtOws3KV.IGR/Adka/MkN4Mbo/u6GP5fwa', null) AS tmp
WHERE NOT EXISTS (
    SELECT email FROM scb.usuario WHERE email = 'solicitanteAplicarTest@artsoft.com'
) LIMIT 1;

INSERT INTO scb.tipo_usuario (rol, email)
SELECT * FROM (SELECT 'ROLE_APPLICANT', 'solicitanteAplicarTest@artsoft.com') AS tmp
WHERE NOT EXISTS (
    SELECT email FROM scb.tipo_usuario WHERE email = 'solicitanteAplicarTest@artsoft.com'
) LIMIT 1;

insert into scb.solicitante (correo, fecha_registro, numero_documento, primer_apellido, primer_nombre, segundo_apellido, segunda_nombre, id_tipo_documento, id_usuario)
values ('solicitanteAplicarTest@artsoft.com', '2017-11-15 12:31:51', '1014207345', 'Alonso', 'Roa', 'Walter', 'Javier', 1, 'solicitanteApproved2@artsoft.com');

-- crear convocatoria simlunado para aplicar:
insert into scb.convocatoria (descripcion, nombre, numero_beneficiarios, fecha_publicacion_resultados, id_estado, id_tipo, mail_oferente)
values ('Becas para estudios en India a aplicar', 'Test 1-1. Conv. a aplicar', 1, '2017-09-01', 4, 1, 'OffererApproved@artsoft.com');

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase Test 1-1. Insc India','2017-10-01', '2018-01-01', '2018-01-02', '2018-10-25', 'Fase Insc India', 7);

---- ya aplico a su primera convocatoria: (esta pedneinte de aprobacion)
insert into scb.convocatoria (descripcion, nombre, numero_beneficiarios, fecha_publicacion_resultados, id_estado, id_tipo, mail_oferente)
values ('Becas para estudios en India pendiente', 'Test 1-2. Conv. esta pendiente', 1, '2017-09-01', 4, 1, 'OffererApproved@artsoft.com');

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase Test 1.2 - Insc India pendiente','2017-10-01', '2017-10-03', '2017-10-04', '2018-10-25', 'Fase Insc India', 8);

insert into scb.postulacion (fecha_postulacion, mail_solicitante, id_convocatoria)
values ('2017-11-15', 'solicitanteAplicarTest@artsoft.com', 8);

insert into scb.solicitante_por_fase (mail_solicitante, id_estado, id_fase)
values ('solicitanteAplicarTest@artsoft.com', 2,  18);

---- ya aplico a su primera convocatoria: (esta aprobado y ya esta pendiente datos parta la siguiente)
insert into scb.convocatoria (descripcion, nombre, numero_beneficiarios, fecha_publicacion_resultados, id_estado, id_tipo, mail_oferente)
values ('Becas para estudios en India aprobado', 'Test 1-3. Conv. esta aprobada', 1, '2017-09-01', 4, 1, 'OffererApproved@artsoft.com');

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase Test 1.3 - Insc India aprobada-ya aprobada','2017-10-01', '2017-12-31', '2018-01-01', '2018-01-02', 'Fase Insc India-ya aprob', 9);

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase Test 1.3 - Insc India aprobada- pedniente reg datos.','2018-03-01', '2018-03-03', '2018-03-04', '2018-10-25', 'Fase Insc India-pend reg datos.', 9);

insert into scb.postulacion (fecha_postulacion, mail_solicitante, id_convocatoria)
values ('2017-11-15', 'solicitanteAplicarTest@artsoft.com', 9);

insert into scb.solicitante_por_fase (mail_solicitante, id_estado, id_fase)
values ('solicitanteAplicarTest@artsoft.com', 1,  19);

insert into scb.solicitante_por_fase (mail_solicitante, id_estado, id_fase)
values ('solicitanteAplicarTest@artsoft.com', 4,  20);

---- ya aplico a su primera convocatoria: (esta rechazado)
insert into scb.convocatoria (descripcion, nombre, numero_beneficiarios, fecha_publicacion_resultados, id_estado, id_tipo, mail_oferente)
values ('Becas para estudios en India rechazado', 'Test 1-4. Conv. esta rechazada', 1, '2017-09-01', 4, 1, 'OffererApproved@artsoft.com');

insert into scb.postulacion (fecha_postulacion, mail_solicitante, id_convocatoria)
values ('2017-11-15', 'solicitanteAplicarTest@artsoft.com', 10);

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase Test 1.4 - Insc India rechazada','2017-10-01', '2017-10-03', '2017-10-04', '2018-10-25', 'Fase Insc India', 10);

insert into scb.solicitante_por_fase (mail_solicitante, id_estado, id_fase)
values ('solicitanteAplicarTest@artsoft.com', 3,  21);

---- habia aplicado con anterioridad, y lo habian rechazado.
insert into scb.convocatoria (descripcion, nombre, numero_beneficiarios, fecha_publicacion_resultados, id_estado, id_tipo, mail_oferente)
values ('Becas para estudios en India rechazado antes', 'Test 1-5. Conv. esta rechazado antes', 1, '2017-09-01', 4, 1, 'OffererApproved@artsoft.com');

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase Test 1.5 - Insc India rechazada antes','2017-10-01', '2017-10-03', '2017-10-04', '2017-10-05', 'Fase Insc India', 11);

insert into scb.postulacion (fecha_postulacion, mail_solicitante, id_convocatoria)
values ('2017-11-15', 'solicitanteAplicarTest@artsoft.com', 11);

insert into scb.solicitante_por_fase (mail_solicitante, id_estado, id_fase)
values ('solicitanteAplicarTest@artsoft.com', 1,  22);

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase Test 1.5 - Insc India rechazada antes','2017-10-06', '2017-10-08', '2017-10-10', '2017-10-12', 'Fase 2 India', 11);

insert into scb.solicitante_por_fase (mail_solicitante, id_estado, id_fase)
values ('solicitanteAplicarTest@artsoft.com', 3,  23);

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase Test 1.5 - Insc India rechazada antes','2017-10-13', '2017-10-20', '2017-10-21', '2018-10-12', 'Fase 3 India', 11);

--- habia aplicado, pero ya no esta activa la conv. (puede ser en estado pendiente) -> ya cerro la conv.
insert into scb.convocatoria (descripcion, nombre, numero_beneficiarios, fecha_publicacion_resultados, id_estado, id_tipo, mail_oferente)
values ('Becas para estudios en India conv. expiro', 'Test 1-6. Conv. expiro', 1, '2017-09-01', 4, 1, 'OffererApproved@artsoft.com');

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase Test 1.6 - Insc India conv. expiro','2017-10-13', '2017-10-20', '2017-10-21', '2017-10-22', 'Fase 1 India', 12);

insert into scb.postulacion (fecha_postulacion, mail_solicitante, id_convocatoria)
values ('2017-11-15', 'solicitanteAplicarTest@artsoft.com', 12);

insert into scb.solicitante_por_fase (mail_solicitante, id_estado, id_fase)
values ('solicitanteAplicarTest@artsoft.com', 3,  25);


-- paso la anterior fase, en esta pendiente registrar datos:
insert into scb.convocatoria (descripcion, nombre, numero_beneficiarios, fecha_publicacion_resultados, id_estado, id_tipo, mail_oferente)
values ('Becas para estudios en India pendiente-2da fase', 'Test 1-7. Conv. esta pend 2da fase', 1, '2017-09-01', 4, 1, 'OffererApproved@artsoft.com');

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase Test 1.7 - Insc India pend. 2da fase-1','2017-10-01', '2017-10-02', '2017-10-03', '2017-10-04', 'Fase Insc India-pend. 2da fase', 13);

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Fase Test 1.7 - Insc India pend. 2da fase-2','2017-10-05', '2017-11-15', '2017-11-30', '2017-12-01', 'Fase Insc India-pend. 2da fase-2.', 13);

insert into scb.postulacion (fecha_postulacion, mail_solicitante, id_convocatoria)
values ('2017-11-15', 'solicitanteAplicarTest@artsoft.com', 13);

insert into scb.solicitante_por_fase (mail_solicitante, id_estado, id_fase)
values ('solicitanteAplicarTest@artsoft.com', 1,  26);

insert into scb.solicitante_por_fase (mail_solicitante, id_estado, id_fase)
values ('solicitanteAplicarTest@artsoft.com', 4,  27);

---- puede ser que quedo pendiente subir cosas.. aplicar.. quedo en estado PendienteRegistroDatos, pues nada, quedo afuera.. rechazado.
insert into scb.convocatoria (descripcion, nombre, numero_beneficiarios, fecha_publicacion_resultados, id_estado, id_tipo, mail_oferente)
values ('Becas para estudios en India pendiente-2da fase', 'Test 1-8. Conv. quedo pend 2da fase-rechazado', 1, '2017-09-01', 4, 1, 'OffererApproved@artsoft.com');

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Test 1-8. Conv. quedo pend 2da fase-rechazado','2017-10-01', '2017-10-02', '2017-10-03', '2017-10-04', 'Test 1-8. Conv. quedo pend 2da fase-rechazado', 14);

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
values ('Test 1-8. Conv. quedo pend 2da fase-rechazado','2017-10-05', '2017-11-08', '2017-11-09', '2017-12-31', 'Test 1-8. Conv. quedo pend 2da fase-rechazado', 14);

insert into scb.postulacion (fecha_postulacion, mail_solicitante, id_convocatoria)
values ('2017-11-15', 'solicitanteAplicarTest@artsoft.com', 14);

insert into scb.solicitante_por_fase (mail_solicitante, id_estado, id_fase)
values ('solicitanteAplicarTest@artsoft.com', 1,  28);

insert into scb.solicitante_por_fase (mail_solicitante, id_estado, id_fase)
values ('solicitanteAplicarTest@artsoft.com', 4,  29);

---Datos completos
insert into scb.convocatoria (descripcion, nombre, numero_beneficiarios, fecha_publicacion_resultados, id_estado, id_tipo, mail_oferente)
VALUES ('Convocatoria con todos los campos', 'Convocatoria con Campos', '10', '2017-12-26', '1', '2', 'OffererApproved@artsoft.com');

insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
VALUES ('Fase de Prueba', '2017-12-01', '2017-12-05', '2017-12-06', '2017-12-10', 'Fase Prueba 1', 15);
insert into scb.fase (descripcion, fecha_inicio, fecha_finalizacion, fecha_inicio_aprobacion, fecha_cierre_aprobacion, nombre,  id_convocatoria)
VALUES ('Fase de Prueba', '2017-12-11', '2017-12-20', '2017-12-15', '2017-12-19', 'Fase Prueba 2', 15);

insert into scb.campo(id,id_fase,seleccion_multiple,nombre,obligatorio,orden,valor,id_field_type) 
VALUES(34, 31, b'0', 'TC - Ma', b'0', 0, '', 1),
    (35, 31, b'0', 'TC Me ', b'0', 1, '', 1),
    (36, 31, b'0', 'TL Ma 5', b'0', 2, '', 2),
    (37, 31, b'0', 'TL Me 10', b'0', 3, '', 2),
    (38, 31, b'0', 'Arc PDF', b'0', 4, '', 3),
    (39, 31, b'0', 'Fecha May', b'0', 5, '', 4),
    (40, 31, b'0', 'Fec Men', b'0', 6, '', 4),
    (41, 31, b'0', 'Fecha Entre', b'0', 7, '', 4),
    (42, 31, b'0', 'Num Ma', b'0', 8, '', 5),
    (43, 31, b'0', 'Me 10', b'0', 9, '', 5),
    (44, 31, b'0', 'Num ENtre', b'0', 10, '', 5),
    (45, 31, b'0', 'Celular', b'0', 11, '', 5),
    (46, 31, b'0', 'Correo', b'0', 12, '', 6);


/*INSERT INTO `campo` (`id`, `id_fase`, `seleccion_multiple`, `nombre`, `obligatorio`, `orden`, `valor`, `id_field_type`) VALUES
    (34, 31, b'0', 'TC - Ma', b'0', 0, '', 1),
    (35, 31, b'0', 'TC Me ', b'0', 1, '', 1),
    (36, 31, b'0', 'TL Ma 5', b'0', 2, '', 2),
    (37, 31, b'0', 'TL Me 10', b'0', 3, '', 2),
    (38, 31, b'0', 'Arc PDF', b'0', 4, '', 3),
    (39, 31, b'0', 'Fecha May', b'0', 5, '', 4),
    (40, 31, b'0', 'Fec Men', b'0', 6, '', 4),
    (41, 31, b'0', 'Fecha Entre', b'0', 7, '', 4),
    (42, 31, b'0', 'Num Ma', b'0', 8, '', 5),
    (43, 31, b'0', 'Me 10', b'0', 9, '', 5),
    (44, 31, b'0', 'Num ENtre', b'0', 10, '', 5),
    (45, 31, b'0', 'Celular', b'0', 11, '', 5),
    (46, 31, b'0', 'Correo', b'0', 12, '', 6);*/
insert into scb.validacion(id,mensaje_error,valor,id_field,id_tipo_validacion_campo) 
VALUES
    (34, 'TC Ma 5', '5', 34, 1),
    (35, 'TC Me 10', '10', 35, 2),
    (36, 'TL Ma 5 ', '5', 36, 3),
    (37, 'TL Me 10', '10', 37, 4),
    (38, 'Arc PDf', 'pdf', 38, 5),
    (39, 'Fecha Ma 26-10', '2017-11-26', 39, 6),
    (40, 'Fecha Me 26-10', '2017-11-26', 40, 7),
    (41, 'Entre 26 y 30', '2017-11-26|2017-11-30', 41, 8),
    (42, 'Ma 5', '5', 42, 9),
    (43, 'Men 10', '10', 43, 10),
    (44, 'Entre 5 y 10', '5|10', 44, 11),
    (45, 'Celular', NULL, 45, 12),
    (46, 'Debe ser correo', NULL, 46, 13);
-- Sincronizaciones de Jobs:
-- 1. cuando cierra la convocatoria.



