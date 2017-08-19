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