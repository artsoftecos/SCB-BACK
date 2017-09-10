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



