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
INSERT INTO scb.usuario (email, enabled, password, token)
SELECT * FROM (SELECT 'walteralonso20@yahoo.com', 1, '$2a$10$2tkTPQBzX5eyud2uAwDPtOws3KV.IGR/Adka/MkN4Mbo/u6GP5fwa', null) AS tmp
WHERE NOT EXISTS (
    SELECT email FROM scb.usuario WHERE email = 'walteralonso20@yahoo.com'
) LIMIT 1;

INSERT INTO scb.tipo_usuario (rol, email)
SELECT * FROM (SELECT 'ROLE_APPLICANT', 'walteralonso20@yahoo.com') AS tmp
WHERE NOT EXISTS (
    SELECT email FROM scb.tipo_usuario WHERE email = 'walteralonso20@yahoo.com'
) LIMIT 1;

INSERT INTO scb.solicitante (correo, direccion, celular, fecha_registro, numero_documento, primer_apellido, primer_nombre, segundo_apellido, segunda_nombre, telefono, id_tipo_documento, id_usuario)
SELECT * FROM (SELECT 'walteralonso20@yahoo.com' as correo, null as direccion, null as celular, '2017-09-02 12:31:51', '1014207335', 'Alonso', 'Walter', 'Roa', 'Javier', null as telefono, '1', 'walteralonso20@yahoo.com' as id_usuario) AS tmp
WHERE NOT EXISTS (
    SELECT correo FROM scb.solicitante WHERE correo = 'walteralonso20@yahoo.com'
) LIMIT 1;
----------------INSERTING ADMIN--------------------------------------
INSERT INTO scb.usuario (email, enabled, password, token)
SELECT * FROM (SELECT 'walteralonso21@yahoo.com', 1, '$2a$10$2tkTPQBzX5eyud2uAwDPtOws3KV.IGR/Adka/MkN4Mbo/u6GP5fwa', null) AS tmp
WHERE NOT EXISTS (
    SELECT email FROM scb.usuario WHERE email = 'walteralonso21@yahoo.com'
) LIMIT 1;

INSERT INTO scb.tipo_usuario (rol, email)
SELECT * FROM (SELECT 'ROLE_ADMINISTRATOR', 'walteralonso21@yahoo.com') AS tmp
WHERE NOT EXISTS (
    SELECT email FROM scb.tipo_usuario WHERE email = 'walteralonso21@yahoo.com'
) LIMIT 1;

INSERT INTO scb.solicitante (correo, direccion, celular, fecha_registro, numero_documento, primer_apellido, primer_nombre, segundo_apellido, segunda_nombre, telefono, id_tipo_documento, id_usuario)
SELECT * FROM (SELECT 'walteralonso21@yahoo.com' as correo, null as direccion, null as celular, '2017-09-02 12:31:51', '1014207335', 'Alonso', 'Walter', 'Roa', 'Javier', null as telefono, '1', 'walteralonso21@yahoo.com' as id_usuario) AS tmp
WHERE NOT EXISTS (
    SELECT correo FROM scb.solicitante WHERE correo = 'walteralonso21@yahoo.com'
) LIMIT 1;
----------------INSERTING Offerer--------------------------------------
INSERT INTO scb.usuario (email, enabled, password, token)
SELECT * FROM (SELECT 'walteralonso22@yahoo.com', 1, '$2a$10$2tkTPQBzX5eyud2uAwDPtOws3KV.IGR/Adka/MkN4Mbo/u6GP5fwa', null) AS tmp
WHERE NOT EXISTS (
    SELECT email FROM scb.usuario WHERE email = 'walteralonso22@yahoo.com'
) LIMIT 1;

INSERT INTO scb.tipo_usuario (rol, email)
SELECT * FROM (SELECT 'ROLE_OFFERER', 'walteralonso22@yahoo.com') AS tmp
WHERE NOT EXISTS (
    SELECT email FROM scb.tipo_usuario WHERE email = 'walteralonso22@yahoo.com'
) LIMIT 1;

INSERT INTO scb.solicitante (correo, direccion, celular, fecha_registro, numero_documento, primer_apellido, primer_nombre, segundo_apellido, segunda_nombre, telefono, id_tipo_documento, id_usuario)
SELECT * FROM (SELECT 'walteralonso22@yahoo.com' as correo, null as direccion, null as celular, '2017-09-02 12:31:51', '1014207335', 'Alonso', 'Walter', 'Roa', 'Javier', null as telefono, '1', 'walteralonso22@yahoo.com' as id_usuario) AS tmp
WHERE NOT EXISTS (
    SELECT correo FROM scb.solicitante WHERE correo = 'walteralonso22@yahoo.com'
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

