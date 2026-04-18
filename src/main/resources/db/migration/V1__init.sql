-- =============================================
-- V1__init.sql - CarbonTrack - Schema MySQL
-- =============================================

CREATE TABLE IF NOT EXISTS residuos (
    id_residuo  BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_residuo VARCHAR(50) NOT NULL,
    descricao    VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS ponto_coleta (
    id_ponto          BIGINT AUTO_INCREMENT PRIMARY KEY,
    localizacao       VARCHAR(255),
    capacidade_maxima INT,
    nivel_atual       INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS coleta (
    id_coleta         BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_coleta       DATE NOT NULL,
    peso_coletado     DECIMAL(10,2),
    id_ponto_coleta   BIGINT,
    id_residuo        BIGINT,
    status            VARCHAR(30),
    CONSTRAINT fk_coleta_ponto   FOREIGN KEY (id_ponto_coleta) REFERENCES ponto_coleta(id_ponto),
    CONSTRAINT fk_coleta_residuo FOREIGN KEY (id_residuo)      REFERENCES residuos(id_residuo)
);

CREATE TABLE IF NOT EXISTS alerta (
    id_alerta           BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensagem            VARCHAR(255) NOT NULL,
    data_alerta         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ponto_coleta_id_ponto BIGINT,
    status              VARCHAR(30),
    CONSTRAINT fk_alerta_ponto FOREIGN KEY (ponto_coleta_id_ponto) REFERENCES ponto_coleta(id_ponto)
);

-- Dados iniciais
INSERT INTO residuos (tipo_residuo, descricao) VALUES
    ('Plástico',  'Garrafas PET e embalagens plásticas'),
    ('Vidro',     'Garrafas e copos de vidro'),
    ('Papel',     'Papéis, jornais e papelão'),
    ('Metal',     'Latas de alumínio e ferro'),
    ('Orgânico',  'Restos de alimentos e folhas');

INSERT INTO ponto_coleta (localizacao, capacidade_maxima, nivel_atual) VALUES
    ('Centro',            100, 80),
    ('Zona Norte',         80, 60),
    ('Zona Sul',          120, 110),
    ('Escola Municipal',   90, 30),
    ('Parque Central',    100, 50);
