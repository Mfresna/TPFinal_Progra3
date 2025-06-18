-- === ROLES ===

-- === CREDENCIALES ===

-- === USUARIOS ===
INSERT INTO usuarios (id, email, nombre, apellido, fecha_nacimiento, descripcion, is_activo, credencial_id)
VALUES
    (1, 'admin@email.com', 'Ana', 'Admin', '1980-01-01', 'Administrador general', true, 1),
    (2, 'arqui@email.com', 'Carlos', 'Arquitecto', '1990-02-15', 'Arquitecto de ejemplo', true, 2),
    (3, 'user@email.com', 'Lucía', 'Usuario', '1995-05-20', 'Usuario visitante', true, 3),
    (4, 'matias@email.com', 'Lucía', 'Usuario', '1995-05-20', 'Usuario visitante', true, 4);

-- === RELACIÓN CREDENCIAL - ROL ===


-- ==== ESTUDIOS ====
INSERT INTO estudios_arq (nombre)
VALUES
    ('Estudio Moderno'),
    ('Estudio Patrimonial');

-- ==== OBRAS ====
INSERT INTO obras (
    nombre, latitud, longitud, estado, anio_estado, categoria, descripcion, estudioarq_id
) VALUES
      ('Casa Lago', -34.6100, -58.3900, 'FINALIZADA', 2015, 'ARQ_RESIDENCIAL', 'Vivienda unifamiliar frente al lago.', 1),
      ('Hospital Central', -34.6150, -58.3880, 'FINALIZADA', 2020, 'ARQ_SALUD', 'Hospital público de alta complejidad.', 1),
      ('Museo de Arte', -34.6200, -58.3700, 'PROYECTO', 2024, 'ARQ_CULTURAL', 'Proyecto de nuevo museo contemporáneo.', 2),
      ('Templo San Jorge', -34.6125, -58.3750, 'CONSTRUCCION', 2023, 'ARQ_RELIGIOSA', 'Templo religioso en obra.', 2),
      ('Fábrica Textil', -34.6170, -58.3650, 'DEMOLICION', 2022, 'ARQ_INDUSTRIAL', 'Demolición de planta industrial abandonada.', 1);
