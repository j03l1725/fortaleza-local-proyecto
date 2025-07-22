-- fortaleza-local-proyecto/db/init.sql

-- Crea la tabla para almacenar el contador de visitas.
CREATE TABLE IF NOT EXISTS visits (
    id INT PRIMARY KEY,
    count BIGINT NOT NULL
);

-- Inserta el registro inicial si no existe.
INSERT INTO visits (id, count) VALUES (1, 0) ON CONFLICT (id) DO NOTHING;
