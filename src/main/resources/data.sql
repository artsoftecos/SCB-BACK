----------------INSERTING Document type DATA ----------------------------
INSERT INTO scb_db.Tipo_Documento (nombre)
SELECT * FROM (SELECT 'Cedula') AS tmp
WHERE NOT EXISTS (
    SELECT nombre FROM scb_db.Tipo_Documento WHERE nombre = 'Cedula'
) LIMIT 1;
INSERT INTO scb_db.Tipo_Documento (nombre)
SELECT * FROM (SELECT 'Cedula extranjería') AS tmp
WHERE NOT EXISTS (
    SELECT nombre FROM scb_db.Tipo_Documento WHERE nombre = 'Cedula extranjería'
) LIMIT 1;
