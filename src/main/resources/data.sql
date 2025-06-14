
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
